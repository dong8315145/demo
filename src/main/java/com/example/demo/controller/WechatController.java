package com.example.demo.controller;

import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.ResultException;
import com.example.demo.common.untis.CookieUnits;
import com.example.demo.entity.SellerInfo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-05 10:28
 **/
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //1.配置
        //2.调用方法
        String tempURL = "" + "/wechat/authorize";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(
                returnUrl, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl, "UTF-8"));
        return "redirect:" + redirectUrl;
    }

    public void userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            throw new ResultException(ResultEnum.FAIL);
        }
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, Map<String, Object> map) {

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId("1");
        redisTemplate.opsForValue().set(String.format("toke_", "toke"), openid, 7200, TimeUnit.SECONDS);


        return new ModelAndView("index");
    }

    @PostMapping("/logout")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        Cookie cookie = CookieUnits.get(request, "toke");
        if (cookie != null) {

            //删除cookie
            redisTemplate.opsForValue().getOperations().delete("toke");
            //清除cookie
            redisTemplate.opsForValue().set(String.format("toke_", "toke"), null, 0, TimeUnit.SECONDS);
        }
        return new ModelAndView("index");
    }

}