package com.example.project1.web;

//import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
//@PreAuthorize("permitAll()")
public class MainPageController {
//    @GetMapping("/")
//    public String displayMainPage(final ModelMap modelMap) {
//        modelMap.addAttribute("currentDate", LocalDate.now());
//        return "main";
//    }

    @GetMapping("/")
    public ModelAndView displayMainPage() {
        ModelAndView mav = new ModelAndView("main");
//        mav.addObject("currentDate", LocalDate.now());
        return mav;
    }

}
