package travelmaster.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import travelmaster.dto.Booking;

@FeignClient(name = "booking-service", url = "http://localhost:8002")
public interface BookingClient {
    @GetMapping("/bookings/{id}")
    Booking getBookingById(@PathVariable("id") Long id);
    
    @GetMapping("/bookings/user/{userId}")
    List<Booking> getBookingsByUser(@PathVariable("userId") Long userId);
   
}

