package com.booking.bookingservice.dto;

public record AccommodationRequest(
  String city,
  String checkIn,
  String checkOut
) {
}
