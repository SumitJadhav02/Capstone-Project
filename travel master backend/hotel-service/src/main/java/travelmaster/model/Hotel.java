package travelmaster.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    // Ensure the hotelName is not null
    @NotNull(message = "Hotel name cannot be null")
    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    // Ensure the location is not null
    @NotNull(message = "Location cannot be null")
    @Column(name = "location", nullable = false)
    private String location;

    // Ensure the roomType is not null
    @NotNull(message = "Room type cannot be null")
    @Column(name = "room_type", nullable = false)
    private String roomType;

    // Ensure the pricePerNight is not null
    @NotNull(message = "Price per night cannot be null")
    @Column(name = "price_per_night", nullable = false)
    private Double pricePerNight;

    // This field can be null, hence no @NotNull annotation
    @Column(name = "amenities")
    private String amenities;

    // Ensure the createdAt is set and not null
    @NotNull(message = "Created date cannot be null")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Ensure the updatedAt is set and not null
    @NotNull(message = "Updated date cannot be null")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Default constructor
    public Hotel() {}

    // Parameterized constructor
    public Hotel(String hotelName, String location, String roomType, Double pricePerNight, String amenities) {
        this.hotelName = hotelName;
        this.location = location;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.amenities = amenities;
    }

    // Getters and Setters
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Auto-set timestamps before inserting or updating
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
