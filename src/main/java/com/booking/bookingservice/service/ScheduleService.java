package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.Budget;
import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.model.Schedule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

  ScheduleDto save(ScheduleDto scheduleDto);

  Budget calculateCostByType(LocalDate startDate, LocalDate endDate, Long propertyId);

  List<ScheduleDto> findAll();

  List<ScheduleDto> findAllByPropertyId(Long propertyId);

  ScheduleDto getByStartDateAndPropertyId(LocalDate localDate, Long propertyId);
}
