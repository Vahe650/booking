package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.AccommodationRequest;
import com.booking.bookingservice.dto.AccommodationResponse;

import java.util.List;

public interface BookingService {

  List<AccommodationResponse> searchAccommodations(AccommodationRequest accommodationRequest);

}
