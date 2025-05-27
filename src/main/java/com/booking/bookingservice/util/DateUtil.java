package com.booking.bookingservice.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DateUtil {

    private final String FORMAT_WITH_DATE_AND_TIME = "yyyy-MM-dd";
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FORMAT_WITH_DATE_AND_TIME);

    public LocalDate dateTimeFormatterWithDateAndTime(String date) {
        return LocalDate.parse(date, dtf);
    }

}