package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import travelmaster.model.Flight;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}
