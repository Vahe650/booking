package com.booking.bookingservice.reposirory;

import com.booking.bookingservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //query to find schedule by start date and property id or (start date less than :localDate or end date greater than :localDate)
    @Query("""
            select s from Schedule s  
            where (s.startDate = :localDate and s.property.id = :propertyId) 
            or (s.startDate < :localDate and s.endDate > :localDate)
            """)
    Schedule findAllByStartDateAndPropertyIdOrStartDateGreaterThanAndStartDate(
            @Param("localDate") LocalDate localDate, @Param("propertyId") Long propertyId);

    List<Schedule> findAllByPropertyId(Long propertyId);

    @Query("""
            select s from Schedule s  
             where s.startDate <= :end  
             and s.endDate >= :start and s.property.id = :propertyId
             """)
    List<Schedule> findAllActiveBetween(@Param("start") LocalDate startDate,
                                        @Param("end") LocalDate endDate,
                                        @Param("propertyId") Long propertyId);

}
