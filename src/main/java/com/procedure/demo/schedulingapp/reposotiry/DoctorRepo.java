package com.procedure.demo.schedulingapp.reposotiry;

import com.procedure.demo.schedulingapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    @Query("select d from Doctor d where lower(d.name) like lower(concat('%', :nameToFind,'%'))")
    List<Doctor> findAllDoctorsByCustomSearch(@Param("nameToFind") String text);
}
