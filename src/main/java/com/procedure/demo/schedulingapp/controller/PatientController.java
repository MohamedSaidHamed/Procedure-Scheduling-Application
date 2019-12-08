package com.procedure.demo.schedulingapp.controller;

import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientController {

    @Autowired
    PatientService patientService;

    /**
     * A method that returns a list of all available patients
     *
     * @return
     */
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * A method to save new patient into the database
     *
     * @param patient
     * @return
     */
    public Patient updatePatient(Patient patient) {
        return patientService.updatePatient(patient);
    }
}
