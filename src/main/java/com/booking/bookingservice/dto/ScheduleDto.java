package com.booking.bookingservice.dto;

import com.booking.bookingservice.model.Schedule;

import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ScheduleDto(
        Long id,

        @NotNull(message = "Name cannot be null")
        String name,

        @Min(value = 0, message = "Members must be zero or positive")
        int members,

        @NotNull(message = "Price cannot be null")
        @PositiveOrZero(message = "Price cannot be negative")
        BigDecimal price,

        @NotNull(message = "Schedule type cannot be null")
        Schedule.ScheduleType scheduleType,

        Long propertyId,
        String startDate,
        String endDate,

        @NotNull(message = "Cost cannot be null")
        @PositiveOrZero(message = "Cost cannot be negative")
        BigDecimal cost
) {}
