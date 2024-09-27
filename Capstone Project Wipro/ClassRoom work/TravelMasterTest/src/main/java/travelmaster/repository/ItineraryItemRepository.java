package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import travelmaster.model.ItineraryItem;


public interface ItineraryItemRepository extends JpaRepository<ItineraryItem, Long> {

}
