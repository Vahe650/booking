package com.booking.bookingservice.service.impl;

import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.mapper.ScheduleMapper;
import com.booking.bookingservice.model.Schedule;
import com.booking.bookingservice.reposirory.ScheduleRepository;
import com.booking.bookingservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.booking.bookingservice.util.DateUtil.dateTimeFormatterWithDateAndTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDto save(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDto);
        Schedule save = scheduleRepository.save(schedule);
        String updateOrSave = scheduleDto.getId() != null ? "updated" : "saved";
        log.info("schedule is {}", updateOrSave);
        return scheduleMapper.toDto(save);
    }

    @Override
    public List<ScheduleDto> findAll() {
        return scheduleMapper.toDto(scheduleRepository.findAll());
    }

    @Override
    public List<ScheduleDto> findByStartAndEndDate(String startDate, String endDate) {
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterWithDateAndTime();
        LocalDateTime start = LocalDateTime.parse(startDate, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endDate, dateTimeFormatter);

        return scheduleMapper.toDto(scheduleRepository.findAllByStartDateAndEndDate(start, end));
    }

    @Override
    public List<ScheduleDto> findByMonth(String month) {
        return null;
    }

    @Override
    public List<ScheduleDto> findByWeek(String week) {
        return null;
    }

    @Override
    public List<ScheduleDto> findByDay(String day) {
        return null;
    }

    @Override
    public ScheduleDto getByStartDate(LocalDateTime localDateTime) {
        return scheduleMapper.toDto(scheduleRepository.findAllByStartDate(localDateTime));

    }
}
