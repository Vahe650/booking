package com.booking.bookingservice.service.impl;

import com.booking.bookingservice.dto.AccommodationRequest;
import com.booking.bookingservice.dto.AccommodationResponse;
import com.booking.bookingservice.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

  private final RestTemplate restTemplate;

  private static final String API_URL = "https://demandapi.booking.com/3.1/accommodations/search";
  private static final String API_KEY = "your_actual_api_key";

  public BookingServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostConstruct
  public void init() {
    searchAccommodations(new AccommodationRequest("New York", "2022-01-01", "2022-01-02"));
  }

  @Override
  public List<AccommodationResponse> searchAccommodations(AccommodationRequest accommodationRequest) {
    var httpClient = HttpClient.newBuilder().build();

    var payload = String.join("\n"
      , "{"
      , " \"booker\": {"
      , "  \"country\": \"nl\","
      , "  \"platform\": \"desktop\""
      , " },"
      , " \"checkin\": \"2025-02-28T00:00\","
      , " \"checkout\": \"2025-03-02T00:00\","
      , " \"city\": -2140479,"
      , " \"extras\": ["
      , "  \"extra_charges\","
      , "  \"products\""
      , " ],"
      , " \"guests\": {"
      , "  \"number_of_adults\": 2,"
      , "  \"number_of_rooms\": 1"
      , " }"
      , "}"
    );

    var host = "https://demandapi.booking.com";
    var pathname = "/3.1/accommodations/search";
    var request = HttpRequest.newBuilder()
      .POST(HttpRequest.BodyPublishers.ofString(payload))
      .uri(URI.create(host + pathname ))
      .header("Content-Type", "application/json")
      .header("X-Affiliate-Id", "0")
      .header("Authorization", "Bearer <YOUR_string_HERE>")
      .build();

    HttpResponse<String> response = null;
    try {
      response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      log.error("Error fetching accommodations: {}", e.getMessage());
      return Collections.emptyList();
    }

    log.info(response.body());

    try {
      String url = UriComponentsBuilder.fromHttpUrl(API_URL)
        .queryParam("city", accommodationRequest.city())
        .queryParam("check_in", accommodationRequest.checkIn())
        .queryParam("check_out", accommodationRequest.checkOut())
        .toUriString();

      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Bearer " + API_KEY);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

      HttpEntity<String> entity = new HttpEntity<>(headers);

      ResponseEntity<AccommodationResponse[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AccommodationResponse[].class);

      AccommodationResponse[] accommodations = responseEntity.getBody();
      return accommodations != null ? Arrays.asList(accommodations) : Collections.emptyList();

    } catch (Exception e) {
      log.error("Error fetching accommodations: {}", e.getMessage());
      return Collections.emptyList();
    }
  }
}