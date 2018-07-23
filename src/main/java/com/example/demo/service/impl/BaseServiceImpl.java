package com.example.demo.service.impl;

import com.example.demo.common.config.UploadFileConfig;
import com.example.demo.common.constant.CommonConstants;
import com.example.demo.common.exception.FrameException;
import com.example.demo.common.units.Message;
import com.example.demo.common.units.model.FileModel;
import com.example.demo.dao.UploadFile;
import com.example.demo.repository.UploadFileRepository;
import com.example.demo.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: demo
 * @description: BaseSerivce
 * @author: Terdy.Zheng
 * @create: 2018-07-23 16:27
 **/
@Slf4j
public class BaseServiceImpl  implements BaseService {


    @Autowired
    private UploadFileRepository uploadFileRepository;

    protected ApplicationContext ac;

    public DecimalFormat intDf = new DecimalFormat("#");

//    @Resource(name = "com.sky.common.BaseDao")
//    private BaseDao dao;

    @Resource
    protected Message message;

    public static final String ROWS = "rows";

    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        this.ac = arg0;
    }









    protected String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    protected String saveImage(MultipartFile file, String dir, Locale locale) throws Exception {
        if (file.getSize() > 0) {
            Image img = null;
            try {
                img = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                    throw new FrameException(this.message.getMessage("upload.not.image", locale));
                }
            } catch (IllegalArgumentException e) {
            }
            return this.saveFile(file, dir, null);
        }
        return null;
    }

    protected String saveImageWx(MultipartFile file, String dir, Locale locale) throws Exception {
        if (file.getSize() > 0) {
            return this.saveImageWx(file.getBytes(), dir, locale);
        }
        return null;
    }

    protected String saveImageWx(byte[] data, String dir, Locale locale) throws Exception {
        Image img = null;
        boolean flag = false;
        double ws = 0;
        int wt = 1024;
        try {
            img = ImageIO.read(new ByteArrayInputStream(data));
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                throw new FrameException(this.message.getMessage("upload.not.image", locale));
            }
            ws = img.getWidth(null);
            if (ws > wt) {
                flag = true;
            }
        } catch (IllegalArgumentException e) {
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (flag) {
            int h = (int) (img.getHeight(null) / (ws / wt));
            BufferedImage bi = new BufferedImage(wt, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img.getScaledInstance(wt, h, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();
            ImageIO.write(bi, "jpg", baos);
            data = baos.toByteArray();
        }
        baos.close();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String image = dir + new SimpleDateFormat("yyyy-MM-dd").format(ts) + "/img_" + this.uuid();
        FileUtils.writeByteArrayToFile(new File(new UploadFileConfig().getUploadfilepath() + image), data);
        UploadFile model = new UploadFile();
        model.setPath(image);
        model.setFlag(CommonConstants.VALID);
        model.setCreateTime(ts);
        return (String) uploadFileRepository.save(model).getId();
    }

    protected String saveFile(MultipartFile file, String dir, String type) throws Exception {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String image = dir + new SimpleDateFormat("yyyy-MM-dd").format(ts) + "/img_" + this.uuid();
        FileUtils.writeByteArrayToFile(new File(new UploadFileConfig().getUploadfilepath()+ image), file.getBytes());
        UploadFile model = new UploadFile();
        model.setPath(image);
        model.setName(file.getOriginalFilename());
        model.setType(type);
        model.setCreateTime(ts);
        model.setFlag(CommonConstants.VALID);
        return (String) uploadFileRepository.save(model).getId();
    }

    @SuppressWarnings("unchecked")
    public String file(String id) {
        List<Object[]> objectList = dao
                .queryAll(DetachedCriteria.forClass(UploadFile.class).add(Restrictions.idEq(id)).setProjection(
                        Projections.projectionList().add(Property.forName("path")).add(Property.forName("fileName"))));
        if (CollectionUtils.isNotEmpty(objectList)) {
            Object[] object = objectList.get(0);
            return new UploadFileConfig().getUploadfilepath() + object[0] + (object[1] == null ? "" : object[1]);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public FileModel fileEx(String id) {
        FileModel fm = new FileModel();
        List<Object[]> objectList = dao.queryAll(DetachedCriteria.forClass(UploadFile.class).add(Restrictions.idEq(id))
                .setProjection(Projections.projectionList().add(Property.forName("path"))
                        .add(Property.forName("fileName")).add(Property.forName("name"))));
        List<Object[]> objectList=
                uploadFileRepository.findAll()
        if (CollectionUtils.isNotEmpty(objectList)) {
            Object[] object = objectList.get(0);
            fm.setSource(new UploadFileConfig().getUploadfilepath() + object[0] + (object[1] == null ? "" : object[1]));
            fm.setFileName((String) object[2]);
        }
        return fm;
    }






    /**
     * 验证是否通过正则表达式
     *
     * @param
     * @param matcher
     * @return
     */
    public Boolean getMatches(String regularUtil, String matcher) {
        Pattern telPattern = Pattern.compile(regularUtil);
        // 通过Pattern获得Matcher
        Matcher idNumMatcher = telPattern.matcher(matcher);
        return idNumMatcher.matches();
    }


}
