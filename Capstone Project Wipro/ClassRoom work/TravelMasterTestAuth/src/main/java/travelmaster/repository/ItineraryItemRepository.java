package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import travelmaster.model.ItineraryItem;

@Repository
public interface ItineraryItemRepository extends JpaRepository<ItineraryItem, Long> {

}
