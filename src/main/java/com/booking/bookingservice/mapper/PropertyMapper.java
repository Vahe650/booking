package com.booking.bookingservice.mapper;

import com.booking.bookingservice.dto.PropertyDto;
import com.booking.bookingservice.model.Property;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyMapper extends EntityMapper<PropertyDto, Property> {

}
