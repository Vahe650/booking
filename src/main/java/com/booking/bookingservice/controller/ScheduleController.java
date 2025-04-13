package com.booking.bookingservice.controller;

import com.booking.bookingservice.model.Schedule;
import com.booking.bookingservice.service.PropertyService;
import com.booking.bookingservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.booking.bookingservice.util.Constants.PROPERTIES;
import static com.booking.bookingservice.util.Constants.SCHEDULES;
import static com.booking.bookingservice.util.Constants.SCHEDULE_TYPES;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ScheduleController {

  private final ScheduleService scheduleService;
  private final PropertyService propertyService;

  @GetMapping
  public String schedulePage(@RequestParam(required = false) Long propertyId,
                             ModelMap modelMap) {
    if (propertyId != null) {
      modelMap.addAttribute(SCHEDULES, scheduleService.findAllByPropertyId(propertyId));
    } else {
      modelMap.addAttribute(SCHEDULES, scheduleService.findAll());
    }
    modelMap.addAttribute(PROPERTIES, propertyService.findAll());
    modelMap.addAttribute(SCHEDULE_TYPES, Schedule.ScheduleType.values());
    return "mySchedule";
  }

}
