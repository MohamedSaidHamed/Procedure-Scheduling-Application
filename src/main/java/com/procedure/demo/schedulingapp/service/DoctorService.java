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

    public List<Doctor> findAllDoctors() {
        return doctorRepo.findAll();
    }
}
