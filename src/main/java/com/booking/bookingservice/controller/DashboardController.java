package com.booking.bookingservice.controller;

import com.booking.bookingservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.booking.bookingservice.util.Constants.COST;
import static com.booking.bookingservice.util.Constants.DATE_PATTERN;
import static com.booking.bookingservice.util.Constants.DATE_TIME_PATTERN;
import static com.booking.bookingservice.util.Constants.END_DATE;
import static com.booking.bookingservice.util.Constants.START_DATE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

  private final ScheduleService scheduleService;

  @GetMapping
  public String dashboardPage(@RequestParam(required = false)
                              @DateTimeFormat(pattern = DATE_TIME_PATTERN)
                              LocalDateTime startDate,
                              @RequestParam(required = false)
                              @DateTimeFormat(pattern = DATE_TIME_PATTERN)
                              LocalDateTime endDate,
                              ModelMap modelMap
  ) {
    modelMap.addAttribute(COST, scheduleService.calculateCostByType(startDate, endDate));
    if (startDate != null && endDate != null) {
      modelMap.addAttribute(START_DATE, DateTimeFormatter.ofPattern(DATE_PATTERN)
        .format(startDate));
      modelMap.addAttribute(END_DATE, DateTimeFormatter.ofPattern(DATE_PATTERN)
        .format(endDate));
    }
    return "dashboard";
  }
}
