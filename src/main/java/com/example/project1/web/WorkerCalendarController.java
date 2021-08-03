package com.example.project1.web;


import com.example.project1.api.model.User;
import com.example.project1.api.model.WorkerCalendarDetails;
import com.example.project1.repository.UserEntity;
import com.example.project1.servise.UserService;
import com.example.project1.servise.WorkerCalendarDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Optional;

@Controller
@RequestMapping("/worker/workerCalendar")
@RequiredArgsConstructor
//@PreAuthorize("isAnonymous()")
public class WorkerCalendarController {

    private final UserService userService;
    private final WorkerCalendarDetailsService workerCalendarDetailsService;


    @GetMapping("/{userId}")
    public ModelAndView displayWorkerCalendarPage(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("workerCalendar");
        mav.addObject("user", userService.getUserById(userId));
        return mav;
    }

    @GetMapping("/{userId}_{date}/editWorkerCalendarDetails")
    public ModelAndView displayWorkerCalendarDetailsPage(@PathVariable Long userId, @PathVariable String date) throws ParseException {
        ModelAndView mav = new ModelAndView("updateWorkerCalendarDetails");
        WorkerCalendarDetails dateInformation = workerCalendarDetailsService.getDateInformation(date, userId);
        if (dateInformation == null) {
            dateInformation = new WorkerCalendarDetails();
            dateInformation.setUserId(userId);
            dateInformation.setAddDate(date);

        }

        mav.addObject("workerCalendarDetails", dateInformation);
        return mav;
    }


    @GetMapping("/{userId}_{date}/workerCalendarDetails")
    public ModelAndView displayUpdateWorkerPage(@PathVariable Long userId, @PathVariable String date) throws ParseException {
        ModelAndView mav = new ModelAndView("workerCalendarDetails");
        WorkerCalendarDetails dateInformation = workerCalendarDetailsService.getDateInformation(date, userId);
        if (dateInformation == null) {
            dateInformation = new WorkerCalendarDetails();
            dateInformation.setUserId(userId);
            dateInformation.setAddDate(date);

        }

        mav.addObject("workerCalendarDetails", dateInformation);
        return mav;
    }

    @PostMapping("/editWorkerCalendarDetails")
    public String handleWorkerCalendarChange(@ModelAttribute WorkerCalendarDetails workerCalendarDetails) throws ParseException {

        workerCalendarDetailsService.addCalendar(workerCalendarDetails);

        return "redirect:/worker";
    }

    @PostMapping("/workerCalendarDetails")
    public String handleWorkerCalendar(@ModelAttribute WorkerCalendarDetails workerCalendarDetails) throws ParseException {
        long userId = workerCalendarDetails.getUserId();
        String addDate = workerCalendarDetails.getAddDate();
        return "redirect:/worker/workerCalendar/" + userId + "_" + addDate + "/editWorkerCalendarDetails";
    }
}
