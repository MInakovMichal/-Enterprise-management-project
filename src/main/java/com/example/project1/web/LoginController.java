package com.example.project1.web;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
//@PreAuthorize("isAnonymous()")
public class LoginController {

    @GetMapping
    public ModelAndView displayLoginPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login");
        if (request.getParameterMap().containsKey("error")) {
            mav.addObject("msg", "Złe dane logowania");
        }
        return mav;
    }

}