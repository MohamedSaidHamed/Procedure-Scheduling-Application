package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Doctor;
import com.procedure.demo.schedulingapp.reposotiry.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;

    /**
     * A method that returns a list of all available doctors
     *
     * @return
     */
    public List<Doctor> findAllDoctors() {
        return doctorRepo.findAll();
    }
}
