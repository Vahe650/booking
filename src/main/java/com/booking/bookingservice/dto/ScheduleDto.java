package com.booking.bookingservice.dto;

import com.booking.bookingservice.model.Schedule;

public record ScheduleDto(
  Long id,
  String name,
  int members,
  Double price,
  Schedule.ScheduleType scheduleType,
  Long propertyId,
  String startDate,
  String endDate,
  int cost
) {}
