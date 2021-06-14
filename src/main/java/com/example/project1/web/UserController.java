package com.example.project1.web;


import com.example.project1.api.model.NewUser;
import com.example.project1.servise.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
//@PreAuthorize("isAnonymous()")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ModelAndView displayRegistrationPage() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("newUser", new NewUser());
        return mav;
    }

    @PostMapping()
    public String handleUserRegistration(@ModelAttribute NewUser newUser) {
        userService.registerUser(newUser);

        return "redirect:/";
    }

}
