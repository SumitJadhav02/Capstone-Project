package travelmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import travelmaster.client.ExpenseClient;
import travelmaster.client.HotelClient;
import travelmaster.dto.UserDTO;
import travelmaster.model.User;
import travelmaster.pojo.Expense;
import travelmaster.pojo.Hotel;
import travelmaster.service.UserService;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private HotelClient hotelClient; 
    
    @Autowired
    private ExpenseClient expenseClient; 

	/*
	 * @PostMapping("/register") public ResponseEntity<String> register(@RequestBody
	 * UserRegistrationDTO registrationDTO) {
	 * userService.registerUser(registrationDTO); return
	 * ResponseEntity.ok("User registered successfully!"); }
	 * 
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody
	 * UserLoginDTO loginDTO) { String token =
	 * userService.authenticateUser(loginDTO); return ResponseEntity.ok(token); }
	 */
    
    
    //http://localhost:8001/api/auth
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            User createdUser = userService.createUser(userDTO); // Updated to use createUser
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
    
    
    
    // New endpoint to fetch all hotels
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelClient.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
    
    

    @GetMapping("/hotels/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelClient.getHotelById(id);
        return hotel;
    }
    
    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<Expense>> getUserExpenses(@PathVariable Long id) {
        try {
            List<Expense> expenses = expenseClient.getExpensesByUserId(id);
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            // Log the exception and return a meaningful error response
            System.err.println("Error fetching expenses: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    
}