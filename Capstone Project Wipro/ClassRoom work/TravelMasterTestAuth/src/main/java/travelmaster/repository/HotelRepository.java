package travelmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import travelmaster.model.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
