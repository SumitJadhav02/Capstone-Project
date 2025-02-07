package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import travelmaster.model.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

}
