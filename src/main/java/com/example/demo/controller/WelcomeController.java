package com.example.demo.controller;

import com.example.demo.common.constant.CommonConstants;
import com.example.demo.common.exception.FrameException;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/welcome")
public class WelcomeController {



    @Autowired
    MemberService memberService;

    @GetMapping("/index")
    public String welcome(){   return "semantic_index";};

    @GetMapping("/login.html")
    public String login(HttpSession session,MemberDTO memberDTO, ModelMap modelMap)
            throws Exception {
        try {
            //国际化
            Locale locale = Locale.SIMPLIFIED_CHINESE;
            session.setAttribute(
                    SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
                    locale);
            session.setAttribute(CommonConstants.LOGIN_USER,    memberService.login(memberDTO,
                    locale));
            //国际化
            return "redirect:login";
        } catch (FrameException e) {
            modelMap.addAttribute(CommonConstants.PAGE_DATE_ATTRIBUTE, memberDTO);
            modelMap.addAttribute("error", e.getMessage());
        }
        return "index";
    }


//    @GetMapping("/workspace.html")
//    public void workspace(HttpServletRequest request, ModelMap modelMap)
//            throws Exception {
//        String id = (String)WebUtils.getSessionAttribute(request,CommonConstants.LOGIN_USER);
//        modelMap.addAttribute(CommonConstants.PAGE_DATE_ATTRIBUTE, super.om
//                .writeValueAsString(service.queryMenu(id, super
//                        .getLocale(request))));
//        modelMap.addAttribute("mail", memberService.queryMail(id));
//    }
}
