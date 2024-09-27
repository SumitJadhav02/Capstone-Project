package com.wipro.controller;

import com.wipro.model.Doctor;
import com.wipro.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getDoctorsFromDatabase();
    }

    @GetMapping("/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor newDoctor) {
        return doctorService.createDoctor(newDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor newDoctor) {
        return doctorService.updateDoctor(id, newDoctor);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteDoctor(@PathVariable int id) {
        return doctorService.deleteDoctor(id);
    }
}
