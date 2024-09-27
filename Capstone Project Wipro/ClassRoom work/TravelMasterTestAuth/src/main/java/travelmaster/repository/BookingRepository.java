package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import travelmaster.model.Booking;


@Repository
public interface BookingRepository  extends JpaRepository<Booking, Long> {

}
