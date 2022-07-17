package com.booking.bookingservice.dto;

import lombok.Data;

@Data
public class ScheduleDto {

    private Long id;
    private String name;
    private int members;
    private String startDate;
    private String endDate;

}
