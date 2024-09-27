package travelmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import travelmaster.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// This method is automatically implemented by Spring Data JPA
	Optional<User> findByUsername(String username);

}
