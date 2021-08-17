package com.example.project1.web;


import com.example.project1.api.model.WorkPlace;
import com.example.project1.servise.WorkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/workPlace")
@RequiredArgsConstructor
public class WorkPlaceController {

    public WorkPlaceService workPlaceService;

    @Autowired
    public WorkPlaceController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }

    @GetMapping
    public ModelAndView displayWorkPlacePage() {
        ModelAndView mav = new ModelAndView("workPlace");
        mav.addObject("workPlaces", workPlaceService.getAllWorkPlace());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String login = auth.getName();
//        Object principal = auth.getPrincipal();
//        boolean hasUserRole = false;
//        if (principal instanceof UserDetails) {
//            String principalToString = ((UserDetails) principal).getAuthorities().toString();
//            if (principalToString.equals("[ROLE_CONSTRUCTION_MANAGER]")
//                    || principalToString.equals("[ROLE_DIRECTOR]")
//                    || principalToString.equals("[ROLE_HOLDER]")
//            ) {
//                hasUserRole = true;
//            }
//        }
//        mav.addObject("whatRole", hasUserRole);
//        mav.addObject("logged", login);
        return mav;
    }

    @GetMapping("/addWorkPlace")
    public ModelAndView displayAddWorkPlacePage() {
        ModelAndView mav = new ModelAndView("addWorkPlace");

        mav.addObject("workPlace", new WorkPlace());
        return mav;
    }

    @PostMapping("/addWorkPlace")
    public String displayAddWorkPlacePage(@Valid @ModelAttribute("workPlace") WorkPlace newWorkPlace, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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
            return "addWorkPlace";

        }
        workPlaceService.registerWorkPlace(newWorkPlace);

        return "redirect:/workPlace";

    }

    @GetMapping("/deleteWorkPlace/{workPlaceId}")
    public String deleteWorkPlace(@PathVariable Long workPlaceId) {
        workPlaceService.deleteWorkPlace(workPlaceId);

        return "redirect:/workPlace";
    }

    @GetMapping("/updateWorkPlace/{workPlaceId}")
    public ModelAndView displayUpdateWorkerPlacePage(@PathVariable Long workPlaceId) {
        ModelAndView mav = new ModelAndView("updateWorkPlace");
        mav.addObject("workPlace", workPlaceService.getWorkPlaceById(workPlaceId));
        return mav;
    }
    @PostMapping
    public String handleWorkPlaceChange(@ModelAttribute WorkPlace workPlace) {
        workPlaceService.updateWorkPlace(workPlace);

        return "redirect:/workPlace";
    }


}
