package com.booking.bookingservice.reposirory;

import com.booking.bookingservice.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
