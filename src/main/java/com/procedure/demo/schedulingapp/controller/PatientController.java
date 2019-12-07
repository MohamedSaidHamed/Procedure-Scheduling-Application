package com.procedure.demo.schedulingapp.controller;

import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.service.PatientService;
import com.procedure.demo.schedulingapp.utilities.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientController {

    @Autowired
    PatientService patientService;

    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    public Patient updatePatient(Patient patient) {
        if (patient.getName().trim().isEmpty()) {
            throw new ExceptionHandler("Required fields are missing");
        }
        return patientService.updatePatient(patient);
    }
}
