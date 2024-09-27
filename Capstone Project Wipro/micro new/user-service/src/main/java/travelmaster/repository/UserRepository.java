package travelmaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import travelmaster.dto.UserDTO;
import travelmaster.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDTO save(UserDTO userDto);
	//Optional<User> findByUsername(String username);
}
