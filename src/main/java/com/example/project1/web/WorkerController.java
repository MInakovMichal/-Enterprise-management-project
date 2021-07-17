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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/worker")
@RequiredArgsConstructor
//@PreAuthorize("isAnonymous()")
public class WorkerController {

    private final UserService userService;

    @GetMapping
    public ModelAndView displayWorkersPage() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", userService.getAllUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Object principal = auth.getPrincipal();
        boolean hasUserRole = false;
        if (principal instanceof UserDetails) {
            String principalToString = ((UserDetails) principal).getAuthorities().toString();
            if (principalToString.equals("[ROLE_CONSTRUCTION_MANAGER]")
                    || principalToString.equals("[ROLE_DIRECTOR]")
                    || principalToString.equals("[ROLE_HOLDER]")
            ) {
                hasUserRole = true;
            }
        }
        mav.addObject("whatRole", hasUserRole);
        mav.addObject("logged", login);
        return mav;
    }

    @PostMapping
    public String handleUserChange(@ModelAttribute User User) {
        userService.updateUser(User);

        return "redirect:/worker";
    }

    @GetMapping("/addWorker")
    public ModelAndView displayWorkerRegistrationPage() {
        ModelAndView mav = new ModelAndView("addWorker");
        mav.addObject("newUser", new NewUser());
        return mav;
    }

    @PostMapping("/addWorker")
    public String displayWorkerRegistrationPage(@Valid @ModelAttribute("newUser") NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
//            for (Object object : bindingResult.getAllErrors()) {
//                if(object instanceof FieldError) {
//                    FieldError fieldError = (FieldError) object;
//
//                    System.out.println(fieldError.getCode());
//                }
//
//                if(object instanceof ObjectError) {
//                    ObjectError objectError = (ObjectError) object;
//
//                    System.out.println(objectError.getCode());
//                }
//            }
            return "addWorker";

        }
        userService.registerWorker(newUser);

        return "redirect:/worker";
    }

    @GetMapping("/update/{userId}")
    public ModelAndView displayUpdateWorkerPage(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("updateUser");
        mav.addObject("user", userService.getUserById(userId));
        return mav;
    }

    @GetMapping("/delete/{userId}")
    public RedirectView deletePatient(@PathVariable Long userId) {
        userService.deletePatient(userId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/worker");

        return redirectView;
    }
}
