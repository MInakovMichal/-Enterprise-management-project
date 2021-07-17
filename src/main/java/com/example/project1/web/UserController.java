package com.example.project1.web;


import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.repository.UserRepository;
import com.example.project1.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

    @Autowired
    private final UserService userService;

    @GetMapping("/addUser")
    public ModelAndView handleUserCheck() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("user", new NewUser());
        return mav;
    }

    @PostMapping("/addUser") //zapytać jak wyswietlić że pesel już aktywowany lub pesela nie istneje w bazie danych
    public String handleUserCheck(@Valid @ModelAttribute("user") User addUser, BindingResult bindingResult) {
        boolean isPeselExistInDatabase = userService.checkUser(addUser);
        boolean isUserActivated = userService.isUserActivated(addUser);
        if (isPeselExistInDatabase && !isUserActivated ) {
            User userByPesel = userService.getUserByPesel(addUser.getUserPesel());
            return "redirect:/user/registration/" + userByPesel.getUserId();
        } else {
            return "addUser";
        }
    }

    @GetMapping("/registration/{userId}")
    public ModelAndView displayRegistrationPage(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("user", userService.getUserById(userId));
        return mav;
    }


    @PostMapping("/registration")
    public String handleUserRegistration(@Valid @ModelAttribute("user") User addUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "registration";
        }

        userService.registerUser(addUser);

        return "redirect:/worker";
    }


}
