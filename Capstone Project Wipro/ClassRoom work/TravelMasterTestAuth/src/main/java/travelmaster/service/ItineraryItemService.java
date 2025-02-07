package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelmaster.exception.ItineraryItemNotFoundException;
import travelmaster.model.ItineraryItem;
import travelmaster.repository.ItineraryItemRepository;

import java.util.List;

@Service
public class ItineraryItemService {

    @Autowired
    private ItineraryItemRepository itineraryItemRepository;

    // Get all itinerary items
    public List<ItineraryItem> getAllItineraryItems() {
        return itineraryItemRepository.findAll();
    }

    // Get itinerary item by ID
    public ItineraryItem getItineraryItemById(Long id) {
        return itineraryItemRepository.findById(id)
            .orElseThrow(() -> new ItineraryItemNotFoundException("Itinerary item not found with ID: " + id));
    }

    // Create a new itinerary item
    public ItineraryItem createItineraryItem(ItineraryItem itineraryItem) {
        if (itineraryItem.getItinerary() == null || itineraryItem.getBooking() == null) {
            throw new IllegalArgumentException("Itinerary and Booking must not be null");
        }
        
        return itineraryItemRepository.save(itineraryItem);
    }



    // Update an existing itinerary item
    public ItineraryItem updateItineraryItem(Long id, ItineraryItem itineraryItemDetails) {
        return itineraryItemRepository.findById(id)
            .map(itineraryItem -> {
                itineraryItem.setBooking(itineraryItemDetails.getBooking());
                itineraryItem.setItemType(itineraryItemDetails.getItemType());
                itineraryItem.setItemDate(itineraryItemDetails.getItemDate());
                itineraryItem.setCost(itineraryItemDetails.getCost());
                return itineraryItemRepository.save(itineraryItem);
            })
            .orElseThrow(() -> new ItineraryItemNotFoundException("Itinerary item not found with ID: " + id));
    }

    // Delete an itinerary item
    public boolean deleteItineraryItem(Long id) {
        return itineraryItemRepository.findById(id).map(itineraryItem -> {
            itineraryItemRepository.delete(itineraryItem);
            return true;
        }).orElseThrow(() -> new ItineraryItemNotFoundException("Itinerary item not found with ID: " + id));
    }
}
