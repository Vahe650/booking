package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.service.ScheduleService;
import com.booking.bookingservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleRestController {

  private final ScheduleService scheduleService;

  @GetMapping
  public ResponseEntity<List<ScheduleDto>> allSchedules(@RequestParam(required = false) Long propertyId) {
    if (propertyId != null) {
      return ResponseEntity.ok(scheduleService.findAllByPropertyId(propertyId));
    }
    return ResponseEntity.ok(scheduleService.findAll());
  }

  @PostMapping("/add")
  public void addSchedule(@ModelAttribute ScheduleDto scheduleDto) {
    scheduleService.save(scheduleDto);
  }

  @GetMapping("/date")
  public ResponseEntity<ScheduleDto> getByStartDate(@RequestParam String localDateTime, @RequestParam Long propertyId) {
    return ResponseEntity.ok(scheduleService.getByStartDateAndPropertyId(
      LocalDateTime.parse(localDateTime, DateUtil.dateTimeFormatterWithDateAndTime()),
      propertyId
    ));
  }

}