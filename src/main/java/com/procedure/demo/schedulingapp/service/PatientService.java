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

    /**
     * A method to save new patient into the database.
     *
     * @param patient
     * @return
     */
    public Patient updatePatient(Patient patient) {
        return patientRepo.save(patient);
    }

    /**
     * A method that returns a list of all available patients
     *
     * @return
     */
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
}
