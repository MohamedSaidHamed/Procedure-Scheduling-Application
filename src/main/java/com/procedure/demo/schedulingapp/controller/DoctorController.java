package com.procedure.demo.schedulingapp.controller;

import com.procedure.demo.schedulingapp.entity.Doctor;
import com.procedure.demo.schedulingapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorController {
    @Autowired
    DoctorService doctorService;


    /**
     * A method that returns a list of all available doctors
     *
     * @return
     */
    public List<Doctor> getAllDoctors() {
        return doctorService.findAllDoctors();
    }
}
