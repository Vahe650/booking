package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.PropertyDto;
import com.booking.bookingservice.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
public class PropertyRestController {

  private final PropertyService propertyService;

  @GetMapping
  public ResponseEntity<List<PropertyDto>> getAll() {
    return ResponseEntity.ok(propertyService.findAll());
  }

  @PostMapping("/add")
  public void addProperty(@ModelAttribute PropertyDto propertyDto) {
    propertyService.save(propertyDto);
  }

}