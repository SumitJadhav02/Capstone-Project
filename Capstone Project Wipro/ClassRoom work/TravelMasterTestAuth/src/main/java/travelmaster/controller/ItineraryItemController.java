package travelmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelmaster.model.ItineraryItem;
import travelmaster.service.ItineraryItemService;

import java.util.List;


@RestController
@RequestMapping("/api/itinerary-items")
public class ItineraryItemController {

    @Autowired
    private ItineraryItemService itineraryItemService;

    // Get all itinerary items
    @GetMapping
    public List<ItineraryItem> getAllItineraryItems() {
        return itineraryItemService.getAllItineraryItems();
    }

    // Get a single itinerary item by ID
    @GetMapping("/{id}")
    public ResponseEntity<ItineraryItem> getItineraryItemById(@PathVariable Long id) {
        ItineraryItem itineraryItem = itineraryItemService.getItineraryItemById(id);
        return ResponseEntity.ok(itineraryItem);
    }

    // Create a new itinerary item
    @PostMapping
    public ResponseEntity<ItineraryItem> createItineraryItem(@RequestBody ItineraryItem itineraryItem) {
        ItineraryItem createdItem = itineraryItemService.createItineraryItem(itineraryItem);
        return ResponseEntity.ok(createdItem);
    }


    // Update an existing itinerary item
    @PutMapping("/{id}")
    public ResponseEntity<ItineraryItem> updateItineraryItem(
            @PathVariable Long id,
            @RequestBody ItineraryItem itineraryItemDetails
    ) {
        ItineraryItem updatedItem = itineraryItemService.updateItineraryItem(id, itineraryItemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    // Delete an itinerary item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItineraryItem(@PathVariable Long id) {
        boolean deleted = itineraryItemService.deleteItineraryItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}