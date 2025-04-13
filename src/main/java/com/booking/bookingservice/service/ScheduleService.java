package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.model.Schedule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

  ScheduleDto save(ScheduleDto scheduleDto);

  Map<Schedule.ScheduleType, BigDecimal> calculateCostByType(LocalDateTime startDate, LocalDateTime endDate);

  List<ScheduleDto> findAll();

  List<ScheduleDto> findAllByPropertyId(Long propertyId);

  ScheduleDto getByStartDateAndPropertyId(LocalDateTime localDate, Long propertyId);
}
