package com.travelmaster.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import travelmaster.client.ExpenseClient;
import travelmaster.client.HotelClient;
import travelmaster.dto.UserDTO;
import travelmaster.model.User;
import travelmaster.pojo.Hotel;
import travelmaster.repository.UserRepository;
import travelmaster.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private HotelClient hotelClient;

    @Mock
    private ExpenseClient expenseClient;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for getUserById
    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User mockUser = new User("username", "password", "email", "firstName", "lastName", "role");
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals("username", result.getUsername());
    }

    // Test for createUserDTO
    @Test
    void testCreateUserDTO() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        User userEntity = new User();
        when(modelMapper.map(userDTO, User.class)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(userDTO);

        // Act
        UserDTO result = userService.createUserDTO(userEntity);

        // Assert
        assertNotNull(result);
    }
    
    @Test
    void testGetAllHotels() {
        // Arrange
        when(hotelClient.getAllHotels()).thenReturn(List.of(new Hotel(), new Hotel()));

        // Act
        List<Hotel> result = userService.getAllHotels();

        // Assert
        assertEquals(2, result.size());
    }

   
}
