package com.example.demo.controller;

import com.example.demo.common.constant.CommonConstants;
import com.example.demo.common.constant.PermissionConstants;
import com.example.demo.common.exception.FrameException;
import com.example.demo.common.permissionfilter.Permission;
import com.example.demo.common.units.Message;
import com.example.demo.common.units.UploadFileUtil;
import com.example.demo.common.units.model.FileModel;
import com.example.demo.common.units.model.JsonModel;
import com.example.demo.dao.UploadFile;
import com.example.demo.service.UploadFileService;
import com.example.demo.service.impl.BaseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
public class BaseController {

    protected ApplicationContext ac;

    @Resource
    protected Message message;

    protected ObjectMapper om = new ObjectMapper();

    @Resource
    private BaseServiceImpl service;

    @Resource
    private UploadFileService uploadFileService;


    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        this.ac = arg0;
    }

    @ExceptionHandler
    public ModelAndView error(HttpServletRequest request, Exception e)
            throws Exception {
     //   logger.error(e.getMessage(), e);
        e.printStackTrace();
        return new ModelAndView(CommonConstants.ERROR_VIEW_NAME,
                CommonConstants.PAGE_DATE_ATTRIBUTE, new JsonModel(
                e instanceof FrameException ? e.getMessage()
                        : this.message.getMessage("server.error", this
                        .getLocale(request))));
    }

    protected void setResponse(ModelMap modelMap, boolean success, Object root) {
        modelMap.clear();
        modelMap.addAttribute(new JsonModel(success, root));
    }

    protected void setResponse(ModelMap modelMap, boolean success) {
        this.setResponse(modelMap, success, null);
    }

    protected Locale getLocale(HttpServletRequest request) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request,
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        return locale == null ? Locale.SIMPLIFIED_CHINESE : locale;
    }

    @RequestMapping("/fileSizeError.plain")
    public JsonModel fileSizeError(HttpServletRequest request) {
        return new JsonModel(this.message.getMessage("file.size.error", this
                .getLocale(request)));
    }

    protected String getLoginUser(HttpServletRequest request) {
        return (String) WebUtils.getSessionAttribute(request,
                CommonConstants.LOGIN_USER);
    }

    @RequestMapping("/list.html")
    public void list(HttpServletRequest request, ModelMap modelMap)
            throws Exception {
    }

    @RequestMapping(value = "/prepareAdd.json", method = RequestMethod.POST)
    @Permission(PermissionConstants.DATA_ADD)
    public JsonModel prepareAdd(HttpServletRequest request) throws Exception {
        return new JsonModel(true);
    }

    protected String getPathPrefix() {
        if (this.getClass().getAnnotation(Controller.class) != null) {
            RequestMapping rm = this.getClass().getAnnotation(
                    RequestMapping.class);
            if (rm != null) {
                return rm.value()[0];
            }
        }
        return "";
    }

    protected Map<String, Object> setGridData(int total, Collection<?> rows) {
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("rows", rows);
        return data;
    }

    /**
     * 获取request中非空的参数
     *
     * @param params
     * @param paramKeys
     * @param request
     */
    public static void getNotEmptyParams(Map<String, String> params,
                                         List<String> paramKeys, HttpServletRequest request) {
        if (paramKeys != null && paramKeys.size() > 0) {
            for (String key : paramKeys) {
                String param = request.getParameter(key);
                if (StringUtils.isNotEmpty(param)) {
                    params.put(key, param);
                }
            }
        }
    }

    protected String getEditorLanguage(HttpServletRequest request) {
        return Locale.ENGLISH.equals(this.getLocale(request)) ? "en" : "zh-cn";
    }

    @RequestMapping("/file.dl")
    public FileModel file(String id) {
        return service.fileEx(id);
    }

    protected String getUrl(HttpServletRequest request, String uri) {
        return new StringBuilder().append(request.getScheme()).append("://")
                .append(request.getServerName()).append(
                        StringUtils.isBlank(uri) ? request.getRequestURI()
                                + (StringUtils
                                .isEmpty(request.getQueryString()) ? ""
                                : "?"
                                + request.getQueryString())
                                : request.getContextPath()
                                + (uri.startsWith("/") ? "" : "/")
                                + uri).toString();
    }

    protected <T> T request(String url, String params, Class<T> clz,
                            Locale locale) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url)
                .openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setConnectTimeout(60000);
        connection.setRequestMethod("POST");
        if (StringUtils.isNotBlank(params)) {
            DataOutputStream dos = new DataOutputStream(connection
                    .getOutputStream());
            dos.write(params.getBytes(CommonConstants.ENCODE));
            dos.flush();
        }
        if (connection.getResponseCode() == 200) {
            return new ObjectMapper().readValue(StringUtils.join(IOUtils
                            .readLines(connection.getInputStream(), CommonConstants.ENCODE),
                    ""), clz);
        }
        throw new FrameException(this.message.getMessage("request.error",
                locale));
    }





    /**
     * 显示图片
     * @param id
     * @return
     */
    @RequestMapping("/image.dl")
    public FileModel image(String id) {
        return new FileModel(service.file(id));
    }
    /**
     * 文件下载
     *
     * @param view
     * @param modelMap
     * @throws FrameException
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/downloadFile.dl")
    public String downloadFile(String fileId, ModelMap modelMap,HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        UploadFile uploadFile = uploadFileService.getFileById(fileId);
        response.setHeader("Content-Disposition", "attachment;fileName="
                + URLEncoder.encode(uploadFile.getName(), "UTF-8"));
        try {
            String path = UploadFileUtil.getRootDir() + uploadFile.getPath();// 获取文件所在文件夹
            File file = new File(path + File.separator
                    + uploadFile.getFileName());
            if (!file.exists()) {
                return null;
            }
            InputStream inputStream = new FileInputStream(new File(path
                    + File.separator + uploadFile.getFileName()));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
