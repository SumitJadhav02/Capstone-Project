package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelmaster.exception.ResourceNotFoundException;
import travelmaster.exception.UserNotFoundException;
import travelmaster.model.Itinerary;
import travelmaster.model.User;
import travelmaster.repository.ItineraryRepository;
import travelmaster.repository.UserRepository;

import java.util.List;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all itineraries
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    // Get itinerary by ID
    public Itinerary getItineraryById(Long id) {
        return itineraryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with ID: " + id));
    }

    // Create a new itinerary
    public Itinerary createItinerary(Itinerary itinerary) {
        User user = userRepository.findById(itinerary.getUser().getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + itinerary.getUser().getUserId()));
        itinerary.setUser(user);
        return itineraryRepository.save(itinerary);
    }

    // Update an existing itinerary
    public Itinerary updateItinerary(Long id, Itinerary itineraryDetails) {
        return itineraryRepository.findById(id).map(itinerary -> {
            User user = userRepository.findById(itineraryDetails.getUser().getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + itineraryDetails.getUser().getUserId()));
            itinerary.setUser(user);
            itinerary.setStartDate(itineraryDetails.getStartDate());
            itinerary.setEndDate(itineraryDetails.getEndDate());
            itinerary.setTotalCost(itineraryDetails.getTotalCost());
            return itineraryRepository.save(itinerary);
        }).orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with ID: " + id));
    }

    // Delete an itinerary
    public boolean deleteItinerary(Long id) {
        return itineraryRepository.findById(id).map(itinerary -> {
            itineraryRepository.delete(itinerary);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with ID: " + id));
    }
}
