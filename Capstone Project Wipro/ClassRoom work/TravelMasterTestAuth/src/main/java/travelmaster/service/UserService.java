package travelmaster.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import travelmaster.exception.ResourceNotFoundException;
import travelmaster.exception.UserNotFoundException;
import travelmaster.model.User;
import travelmaster.model.UserLoginDTO;
import travelmaster.model.UserRegistrationDTO;
import travelmaster.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    // Authenticate user and generate JWT token
    public String authenticateUser(UserLoginDTO loginDTO) {
        // Find user by username, throw exception if not found
        User user = userRepository.findByUsername(loginDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Check if the provided password matches the stored password
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            // If password matches, generate and return a JWT token
            return generateToken(user);
        }

        // If credentials are invalid, throw an exception
        throw new RuntimeException("Invalid credentials");
    }

    // Generate JWT token for the authenticated user
    private String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token valid for 1 day
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // Find user by username, throw exception if not found
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    // Register a new user
    public User registerUser(UserRegistrationDTO registrationDTO) {
        // Create a new user object and set its properties
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword())); // Encrypt password
        user.setEmail(registrationDTO.getEmail());
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setRole("Traveler"); // Set default role
        return userRepository.save(user); // Save the user to the database
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Return list of all users
    }

    // Get user by ID, throw exception if not found
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    // Create a new user
    public User createUser(User user) {
        // Perform additional validation, if necessary
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        return userRepository.save(user); // Save the user to the database
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    // Update user properties with the new details
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(passwordEncoder.encode(userDetails.getPassword())); // Encrypt password
                    user.setEmail(userDetails.getEmail());
                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setRole(userDetails.getRole());
                    user.setUpdatedAt(LocalDateTime.now()); // Set update time
                    return userRepository.save(user); // Save the updated user to the database
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id); // Delete the user
        return true;
    }
}
