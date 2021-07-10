package com.example.project1.web;


import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
//@PreAuthorize("isAnonymous()")
public class UserController {

    private final UserService userService;

    @GetMapping("/addUser")
    public ModelAndView handleUserCheck() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("newUser", new NewUser());
        return mav;
    }

    @PostMapping("/addUser")
    public String handleUserCheck(@ModelAttribute User addUser) {
        boolean isPeselExistInDatabase = userService.checkUser(addUser);
        boolean isUserActivated = userService.isUserActivated(addUser);
        if (isPeselExistInDatabase && !isUserActivated) {
            return "redirect:/user/registration/" + addUser.getUserPesel();
        } else {
            return "redirect:/user/addUser";
        }
    }

    @GetMapping("/registration/{userPesel}")
    public ModelAndView displayRegistrationPage(@PathVariable String userPesel) {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("user", userService.getUserByPesel(userPesel));
        return mav;
    }


    @PostMapping
    public String handleUserRegistration(@ModelAttribute @Valid User addUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){

            return "redirect:/user/registration/" + addUser.getUserPesel();
        }

        userService.registerUser(addUser);

        return "redirect:/worker";
    }


}
