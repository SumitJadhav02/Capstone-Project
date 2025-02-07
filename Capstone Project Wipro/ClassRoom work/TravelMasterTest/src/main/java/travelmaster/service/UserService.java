package travelmaster.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelmaster.exception.ResourceNotFoundException;
import travelmaster.model.User;
import travelmaster.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public User createUser(User user) {
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        
        
        
//        if (userRepository.existsById(user.getUserId())) { // Example check for existing user
//            throw new IllegalArgumentException("User already exists with ID: " + user.getUserId());
//        }
//        System.out.println("Creating user: " + user);
        
        
        
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    user.setEmail(userDetails.getEmail());
                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setRole(userDetails.getRole());
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }
}
