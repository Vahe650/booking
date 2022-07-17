package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    @GetMapping("/")
    public String schedulePage(@ModelAttribute("startDate") String startDate,
                               @ModelAttribute("endDate") String endDate,
                               ModelMap modelMap) {
        List<ScheduleDto> byStartAndEndDate = scheduleService.findAll();
//        modelMap.addAttribute("schedules", byStartAndEndDate);
        return "mySchedule";
    }

    }
