package travelmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import travelmaster.exception.HotelNotFoundException;
import travelmaster.model.Hotel;
import travelmaster.repository.HotelRepository;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Get all hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Get hotel by ID
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + id));
    }

    // Create a new hotel
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Update an existing hotel
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        return hotelRepository.findById(id)
            .map(hotel -> {
                hotel.setHotelName(hotelDetails.getHotelName());
                hotel.setLocation(hotelDetails.getLocation());
                hotel.setRoomType(hotelDetails.getRoomType());
                hotel.setPricePerNight(hotelDetails.getPricePerNight());
                hotel.setAmenities(hotelDetails.getAmenities());
                hotel.setUpdatedAt(hotelDetails.getUpdatedAt());
                return hotelRepository.save(hotel);
            })
            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + id));
    }

    // Delete a hotel
    @Transactional
    public boolean deleteHotel(Long id) {
        return hotelRepository.findById(id)
            .map(hotel -> {
                hotelRepository.delete(hotel);
                return true;
            })
            .orElse(false); 
    }

}
