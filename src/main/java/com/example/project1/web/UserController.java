package com.example.project1.web;


import com.example.project1.api.model.NewUser;
import com.example.project1.api.model.User;
import com.example.project1.servise.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
//@PreAuthorize("isAnonymous()")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ModelAndView displayUserPage() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", userService.getAllUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Object principal = auth.getPrincipal();
        String principalToString = ((UserDetails) auth.getPrincipal()).getAuthorities().toString();

        boolean hasUserRole = false;
        if (principal instanceof UserDetails) {
            if (principalToString.equals("[ROLE_CONSTRUCTION_MANAGER]")
                    || principalToString.equals("[ROLE_DIRECTOR]")
                    || principalToString.equals("[ROLE_HOLDER]")
            ){
                hasUserRole = true;
            }
        }
        mav.addObject("whatRole", hasUserRole);
        mav.addObject("logged", login);
        return mav;
    }

    @PostMapping
    public RedirectView handleFacilityChange(@ModelAttribute User User) {
        userService.updateUser(User);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/user");

        return redirectView;
    }

    @GetMapping("/addUser")
    public ModelAndView displayRegistrationPage() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("newUser", new NewUser());
        return mav;
    }

    @PostMapping("/addUser")
    public String handleUserRegistration(@ModelAttribute NewUser newUser) {
        userService.registerUser(newUser);

        return "redirect:/";
    }

    @GetMapping("/update/{userId}")
    public ModelAndView displayUpdateUserPage(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("updateUser");
        mav.addObject("user", userService.getUser(userId));
        return mav;
    }
}
