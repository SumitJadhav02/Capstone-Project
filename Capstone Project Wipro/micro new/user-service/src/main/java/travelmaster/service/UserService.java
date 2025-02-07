package travelmaster.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelmaster.client.BookingClient;
import travelmaster.client.ExpenseClient;
import travelmaster.client.HotelClient;
import travelmaster.dto.Booking;
import travelmaster.dto.UserDTO;
import travelmaster.exception.ResourceNotFoundException;
import travelmaster.model.User;
import travelmaster.pojo.Expense;
import travelmaster.pojo.Hotel;
import travelmaster.repository.UserRepository;
import org.modelmapper.ModelMapper;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HotelClient hotelClient;
	
	 @Autowired
	 private ExpenseClient expenseClient;
	
	public UserDTO createUserDTO(User user) {
		User savedUser = userRepository.save(user);
		UserDTO savedUserDto = modelMapper.map(savedUser, UserDTO.class);
		return savedUserDto;
	}
	
	
	
	
	// Add this method in the UserService class
	public User createUser(UserDTO userDTO) {
	    // Convert UserDTO to User entity using ModelMapper
	    User user = modelMapper.map(userDTO, User.class);
	    // Save the User entity to the repository
	    return userRepository.save(user);
	}
	
	
	

	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User updateUser(Long id, User userDetails) {
		return userRepository.findById(id).map(user -> {
			user.setUsername(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			user.setEmail(userDetails.getEmail());
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			user.setRole(userDetails.getRole());
			user.setUpdatedAt(LocalDateTime.now());
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
	}

	public boolean deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found with ID: " + id);
		}
		userRepository.deleteById(id);
		return true;
	}
	
	
	
	public List<Hotel> getAllHotels() {
	    return hotelClient.getAllHotels();  // Call the Feign Client method
	}
	
	
	public Hotel getHotelById(Long hotelId) {
	    return hotelClient.getHotelById(hotelId);  // Call the Feign Client method
	}
	
	  public List<Expense> getExpensesByUserId(Long userId) {
	        return expenseClient.getExpensesByUserId(userId);  // Call the Feign Client method
	    }
	
}
