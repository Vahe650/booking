package com.booking.bookingservice.util;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {

    private final String FORMAT_WITH_DATE_AND_TIME = "yyyy-MM-dd'T'HH:mm:ss";

    public DateTimeFormatter dateTimeFormatterWithDateAndTime() {
        return DateTimeFormatter.ofPattern(FORMAT_WITH_DATE_AND_TIME);
    }

}