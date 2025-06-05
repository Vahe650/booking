package com.booking.bookingservice.dto;

import com.booking.bookingservice.model.Schedule;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BudgetDetail {

    private BigDecimal expense;
    private BigDecimal priceWithTax;
    private BigDecimal priceWithoutTax;
    private BigDecimal total;
    private Schedule.ScheduleType scheduleType;
    private LocalDate startDate;
    private LocalDate endDate;

}
