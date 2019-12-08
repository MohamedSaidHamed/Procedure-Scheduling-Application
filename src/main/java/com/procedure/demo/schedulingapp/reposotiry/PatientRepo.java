package com.procedure.demo.schedulingapp.reposotiry;

import com.procedure.demo.schedulingapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
