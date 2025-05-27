package com.booking.bookingservice.dto;

import com.booking.bookingservice.model.Schedule;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetDetail {

    private BigDecimal expense;
    private BigDecimal price;
    private BigDecimal total;
    private Schedule.ScheduleType scheduleType;

}
