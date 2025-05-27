package com.booking.bookingservice.dto;

import com.booking.bookingservice.model.Schedule;

import java.math.BigDecimal;

public record ScheduleDto(
  Long id,
  String name,
  int members,
  BigDecimal price,
  Schedule.ScheduleType scheduleType,
  Long propertyId,
  String startDate,
  String endDate,
  BigDecimal cost
) {}
