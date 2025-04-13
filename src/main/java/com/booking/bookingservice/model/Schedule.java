package com.booking.bookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int members;
  private Double price;
  @ManyToOne
  private Property property;
  private ScheduleType scheduleType;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private BigDecimal cost;

  @Getter
  public enum ScheduleType {

    BOOKING("booking"),
    AIRBNB("airbnb"),
    HOMEAWAY("homeaway"),
    TRIPADVISOR("tripadvisor"),
    OSTROVOK("ostrovok"),
    EXPEDIA("expedia"),
    HOTELS("hotels"),
    AGODA("agoda"),
    TRAVELGURU("travelguru"),
    BRONEVIK("bronevik"),
    HOTELBED("hotelbed"),
    NO_TAX("notax");

    private final String value;

    ScheduleType(String value) {
      this.value = value;
    }
  }

}
