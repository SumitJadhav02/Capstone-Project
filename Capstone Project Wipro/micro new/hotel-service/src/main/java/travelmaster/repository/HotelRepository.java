package travelmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import travelmaster.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
