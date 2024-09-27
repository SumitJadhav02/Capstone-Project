package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import travelmaster.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
