package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.AccommodationRequest;
import com.booking.bookingservice.dto.AccommodationResponse;
import com.booking.bookingservice.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @GetMapping
  public List<AccommodationResponse> getAccommodationsWithStays(@RequestBody AccommodationRequest request) {
    return bookingService.searchAccommodations(request);
  }
}
