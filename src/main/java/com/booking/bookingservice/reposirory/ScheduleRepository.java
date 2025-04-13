package com.booking.bookingservice.reposirory;

import com.booking.bookingservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  Schedule findAllByStartDateAndPropertyId(LocalDateTime localDateTime, Long propertyId);

  List<Schedule> findAllByPropertyId(Long propertyId);

  @Query("select s from Schedule s " +
    "where s.startDate <= :end " +
    "and s.endDate >= :start")
  List<Schedule> findAllActiveBetween(@Param("start") LocalDateTime startDate,
                                      @Param("end") LocalDateTime endDate);

}
