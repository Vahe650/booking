package com.booking.bookingservice.dto;

public record PropertyDto(
  Long id,
  String name,
  String googleMapUrl,
  String yandexMapUrl
) {
}
