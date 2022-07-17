package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.service.ScheduleService;
import com.booking.bookingservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleRestController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleDto>> allSchedules() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @PostMapping("/add")
    public void addSchedule(@ModelAttribute ScheduleDto scheduleDto) {
        scheduleService.save(scheduleDto);
    }

    @GetMapping("/date")
    public ResponseEntity<ScheduleDto> getByStartDate(@RequestParam String localDateTime) {
        return ResponseEntity.ok(scheduleService.getByStartDate(
                LocalDateTime.parse(localDateTime, DateUtil.dateTimeFormatterWithDateAndTime())
        ));
    }

}