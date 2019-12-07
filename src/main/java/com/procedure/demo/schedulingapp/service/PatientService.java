package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.reposotiry.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientService {
    @Autowired
    PatientRepo patientRepo;


    public Patient updatePatient(Patient patient) {
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
}
