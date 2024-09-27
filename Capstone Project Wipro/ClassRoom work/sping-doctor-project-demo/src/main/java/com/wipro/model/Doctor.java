package com.wipro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Doctor")
public class Doctor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;  
    private String name;   
    private String specialization;
    private int experience;
    
    public Doctor() {
        super();
    }

    public Doctor(int id, String name, String specialization, int experience) {
        super();
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "specialization", nullable = false)
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Column(name = "experience", nullable = false)
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
