package com.booking.bookingservice.service.impl;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.mapper.ScheduleMapper;
import com.booking.bookingservice.model.Schedule;
import com.booking.bookingservice.reposirory.PropertyRepository;
import com.booking.bookingservice.reposirory.ScheduleRepository;
import com.booking.bookingservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final PropertyRepository propertyRepository;
  private final ScheduleMapper scheduleMapper;

  @Override
  public ScheduleDto save(ScheduleDto scheduleDto) {
    if (!scheduleRepository.findAllActiveBetween(LocalDateTime.parse(scheduleDto.startDate()), LocalDateTime.parse(scheduleDto.endDate()))
      .isEmpty()) {
      log.error("Schedule already exists in that range");
      return null;
    }
    Schedule schedule = scheduleMapper.toEntity(scheduleDto);
    propertyRepository.findById(scheduleDto.propertyId())
      .ifPresentOrElse(schedule::setProperty, () -> schedule.setProperty(null));

    Schedule savedSchedule = scheduleRepository.save(schedule);
    log.info("Schedule is {}", scheduleDto.id() != null ? "updated" : "saved");
    return scheduleMapper.toDto(savedSchedule);
  }

  @Override
  public Map<Schedule.ScheduleType, BigDecimal> calculateCostByType(LocalDateTime startDate, LocalDateTime endDate) {
    log.info("Calculating cost by schedule type");
    List<Schedule> schedules = scheduleRepository.findAllActiveBetween(startDate, endDate);

    return schedules.stream()
      .collect(Collectors.groupingBy(
        s -> s.getScheduleType() != null ? s.getScheduleType() : Schedule.ScheduleType.NO_TAX,
        Collectors.mapping(
          s -> s.getCost() == null ? BigDecimal.ZERO : s.getCost(),
          Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
        )
      ));
  }

  @Override
  public List<ScheduleDto> findAll() {
    log.info("Find All Schedules");
    return scheduleMapper.toDto(scheduleRepository.findAll());
  }

  @Override
  public List<ScheduleDto> findAllByPropertyId(Long propertyId) {
    log.info("Find All Schedules By Property Id: {}", propertyId);
    return scheduleMapper.toDto(scheduleRepository.findAllByPropertyId(propertyId));
  }

  @Override
  public ScheduleDto getByStartDateAndPropertyId(LocalDateTime localDateTime, Long propertyId) {
    log.info("Find Schedule By Start Date");
    return scheduleMapper.toDto(scheduleRepository.findAllByStartDateAndPropertyId(localDateTime, propertyId));
  }
}
