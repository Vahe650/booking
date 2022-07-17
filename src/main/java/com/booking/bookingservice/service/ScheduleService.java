package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.ScheduleDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {

    ScheduleDto save(ScheduleDto scheduleDto);

    List<ScheduleDto> findAll();

    List<ScheduleDto> findByStartAndEndDate(String startDate, String endDate);

    List<ScheduleDto> findByMonth(String month);

    List<ScheduleDto> findByWeek(String week);

    List<ScheduleDto> findByDay(String day);

    ScheduleDto getByStartDate(LocalDateTime localDate);
}
