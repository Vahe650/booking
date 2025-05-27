package com.booking.bookingservice.service.impl;

import com.booking.bookingservice.dto.Budget;
import com.booking.bookingservice.dto.BudgetDetail;
import com.booking.bookingservice.dto.ScheduleDto;
import com.booking.bookingservice.mapper.ScheduleMapper;
import com.booking.bookingservice.model.Schedule;
import com.booking.bookingservice.reposirory.PropertyRepository;
import com.booking.bookingservice.reposirory.ScheduleRepository;
import com.booking.bookingservice.service.ScheduleService;
import com.booking.bookingservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        Schedule schedule = scheduleMapper.toEntity(scheduleDto);
        propertyRepository.findById(scheduleDto.propertyId())
                .ifPresentOrElse(schedule::setProperty, () -> schedule.setProperty(null));

        Schedule savedSchedule = scheduleRepository.save(schedule);
        log.info("Schedule is {}", scheduleDto.id() != null ? "updated" : "saved");
        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public Budget calculateCostByType(LocalDate startDate, LocalDate endDate, Long propertyId) {
        log.info("Calculating cost by schedule type");
        List<Schedule> schedules = scheduleRepository.findAllActiveBetween(startDate, endDate, propertyId);
        Budget budget = new Budget();
        for (Schedule schedule : schedules) {
            BudgetDetail budgetDetail = new BudgetDetail();
            budgetDetail.setExpense(schedule.getCost());
            budgetDetail.setPrice(schedule.getPrice());
            if (schedule.getScheduleType() == Schedule.ScheduleType.NO_TAX) {
                budgetDetail.setTotal(schedule.getPrice().subtract(schedule.getCost()));
            } else {
                budgetDetail.setTotal(schedule.getPrice().multiply(BigDecimal.valueOf(0.85)).subtract(schedule.getCost()));

            }
            budgetDetail.setScheduleType(schedule.getScheduleType());
            budget.addBudgetDetail(budgetDetail);
        }
        return budget;
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
    public ScheduleDto getByStartDateAndPropertyId(LocalDate localDate, Long propertyId) {
        log.info("Find Schedule By Start Date");
        return scheduleMapper.toDto(scheduleRepository.findAllByStartDateAndPropertyIdOrStartDateGreaterThanAndStartDate(localDate, propertyId));
    }
}
