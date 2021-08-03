package com.example.project1.web;


import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.mailSender.MailMessagePrepare;
import com.example.project1.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
//@PreAuthorize("isAnonymous()")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public ModelAndView handleUserCheck() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("user", new NewUser());
        return mav;
    }
    @GetMapping("/addUserAccess")
    public ModelAndView handleUserCheck2() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("user", new NewUser());
        return mav;
    }

    @PostMapping("/addUser")
    public String handleUserCheck(@Valid @ModelAttribute("user") User addUser, BindingResult bindingResult) throws MessagingException {

        MailMessagePrepare emailSender = new MailMessagePrepare();

        boolean isEmailExistInDatabase = userService.checkUser(addUser);
        boolean isUserActivated = userService.isUserActivated(addUser);

        if (isEmailExistInDatabase && !isUserActivated ) {
            User userByEmail = userService.getUserByEmail(addUser.getUserEmail());
            emailSender.prepareMessageObject(userByEmail.getUserEmail(),
                    "http://localhost:8080/user/registration/" + userByEmail.getUserId());
            return "addUserAccess";
        } else {
            bindingResult.rejectValue("userEmail", "error.UserEmail", "Data error");
        }
        return "addUser";
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
