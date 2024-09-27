package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelmaster.exception.FlightNotFoundException;
import travelmaster.model.Flight;
import travelmaster.repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Get all flights
    public List<Flight> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found.");
        }
        return flights;
    }

    // Get flight by ID
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
            .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
    }

    // Create a new flight
    public Flight createFlight(Flight flight) {
        // Optionally, add validation for the flight object here
        return flightRepository.save(flight);
    }

    // Update an existing flight
    public Flight updateFlight(Long id, Flight flightDetails) {
        return flightRepository.findById(id)
            .map(flight -> {
                flight.setFlightNumber(flightDetails.getFlightNumber());
                flight.setDepartureAirport(flightDetails.getDepartureAirport());
                flight.setArrivalAirport(flightDetails.getArrivalAirport());
                flight.setDepartureTime(flightDetails.getDepartureTime());
                flight.setArrivalTime(flightDetails.getArrivalTime());
                flight.setAirline(flightDetails.getAirline());
                flight.setPrice(flightDetails.getPrice());
                flight.setUpdatedAt(flightDetails.getUpdatedAt());
                return flightRepository.save(flight);
            })
            .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
    }

    // Delete a flight
    public boolean deleteFlight(Long id) {
        return flightRepository.findById(id)
            .map(flight -> {
                flightRepository.delete(flight);
                return true;
            })
            .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
    }
}
