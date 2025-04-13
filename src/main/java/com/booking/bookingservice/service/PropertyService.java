package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.PropertyDto;

import java.util.List;

public interface PropertyService {

  PropertyDto save(PropertyDto propertyDto);

  List<PropertyDto> findAll();

}
