package com.booking.bookingservice.mapper;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper extends EntityMapper<ScheduleDto, Schedule> {
}
