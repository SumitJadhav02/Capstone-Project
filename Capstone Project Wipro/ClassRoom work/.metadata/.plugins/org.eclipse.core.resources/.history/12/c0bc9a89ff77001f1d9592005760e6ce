package com.wipro.service;

import com.wipro.model.Doctor;
import com.wipro.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepo;

    public List<Doctor> getDoctorsFromDatabase() {
        return doctorRepo.findAll();
    }

    public Optional<Doctor> getDoctorById(int id) {
        return doctorRepo.findById(id);
    }

    public Doctor createDoctor(Doctor newDoctor) {
        return doctorRepo.save(newDoctor);
    }

    public ResponseEntity<Doctor> updateDoctor(int id, Doctor newDoctor) {
        Optional<Doctor> updatedDoctor = doctorRepo.findById(id);
        if (updatedDoctor.isPresent()) {
            Doctor doctor = updatedDoctor.get();
            doctor.setName(newDoctor.getName());
            doctor.setSpecialization(newDoctor.getSpecialization());
            doctor.setExperience(newDoctor.getExperience());
            doctorRepo.save(doctor);
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Map<String, Boolean> deleteDoctor(int id) {
        Optional<Doctor> deleteDoctorObj = doctorRepo.findById(id);
        if (deleteDoctorObj.isPresent()) {
            doctorRepo.delete(deleteDoctorObj.get());
            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);
            return response;
        } else {
            Map<String, Boolean> response = new HashMap<>();
            response.put("Doctor not found", Boolean.FALSE);
            return response;
        }
    }
}
