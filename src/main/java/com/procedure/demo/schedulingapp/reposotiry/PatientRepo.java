package com.procedure.demo.schedulingapp.reposotiry;

import com.procedure.demo.schedulingapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

    @Query("select p from Patient p where lower(p.name) like lower(concat('%', :nameToFind,'%'))")
    List<Patient> findAllPatientsByCustomSearch(@Param("nameToFind") String text);
}
