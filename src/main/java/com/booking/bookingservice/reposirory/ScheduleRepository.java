package com.booking.bookingservice.reposirory;

import com.booking.bookingservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);

//    List<Schedule> findAllByStartDate_DayOfMonth(int month);

//    List<Schedule> findAllByStartDate_DayOfWeek(DayOfWeek dayOfWeek);

    Schedule findAllByStartDate(LocalDateTime localDateTime);

}
