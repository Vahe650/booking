package com.booking.bookingservice.service.impl;

import com.booking.bookingservice.dto.PropertyDto;
import com.booking.bookingservice.mapper.PropertyMapper;
import com.booking.bookingservice.reposirory.PropertyRepository;
import com.booking.bookingservice.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

  private final PropertyRepository propertyRepository;
  private final PropertyMapper propertyMapper;

  @Override
  public PropertyDto save(PropertyDto propertyDto) {
    log.info("Property is saved");
    return propertyMapper.toDto(propertyRepository.save(propertyMapper.toEntity(propertyDto)));
  }

  @Override
  public List<PropertyDto> findAll() {
    log.info("All properties are fetched");
    return propertyMapper.toDto(propertyRepository.findAll());
  }
}
