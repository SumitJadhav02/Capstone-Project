package travelmaster.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import travelmaster.pojo.Hotel;

@FeignClient(name = "hotel-service", url = "${hotel-service:http://localhost:8002}")

public interface HotelClient {

	    @GetMapping("/api/hotels")     //   http://localhost:8001/api/auth/hotels
	    List<Hotel> getAllHotels();
	
	
    @GetMapping("/api/hotels/{id}")
    Hotel getHotelById(@PathVariable("id") Long id);
    

    
    
}
