package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelmaster.exception.BookingNotFoundException;
import travelmaster.exception.UserNotFoundException;  // Import the UserNotFoundException
import travelmaster.model.Booking;
import travelmaster.model.User;  // Import the User model
import travelmaster.repository.BookingRepository;
import travelmaster.repository.UserRepository;  // Import the UserRepository

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository

    // Get all bookings
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    // Get a booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
    }

    // Create a new booking
    public Booking createBooking(Booking booking) {
        User user = userRepository.findById(booking.getUser().getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + booking.getUser().getUserId()));
        booking.setUser(user); // Set the fetched user
        return bookingRepository.save(booking);
    }


    // Update an existing booking
    public Booking updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id)
            .map(booking -> {
                booking.setUser(bookingDetails.getUser());
                booking.setFlight(bookingDetails.getFlight());
                booking.setHotel(bookingDetails.getHotel());
                booking.setItinerary(bookingDetails.getItinerary());
                booking.setBookingDate(bookingDetails.getBookingDate());
                booking.setStatus(bookingDetails.getStatus());
                booking.setUpdatedAt(bookingDetails.getUpdatedAt());
                return bookingRepository.save(booking);
            })
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
    }

    // Delete a booking by ID
    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id)
            .map(booking -> {
                bookingRepository.delete(booking);
                return true;
            })
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
    }
}
