package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Doctor;
import com.procedure.demo.schedulingapp.guiController.SchedulingappUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testfx.framework.junit.ApplicationTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class DoctorServiceTest extends ApplicationTest{

    @Autowired
    DoctorService doctorService;

    @BeforeAll
    static void beforeAll() throws Exception {
        ApplicationTest.launch(SchedulingappUI.class);
    }


    @Test
    void findInitializedDoctors() {
        Doctor doctor = new Doctor();
        doctor.setName("Doctor A");
        List<Doctor> result = doctorService.findAllDoctors();
        assertEquals(result.get(0).getName(),doctor.getName());
    }
    @Test
    void findNonInitializedDoctors() {
        Doctor doctor = new Doctor();
        doctor.setName("Doctor House");
        List<Doctor> result = doctorService.findAllDoctors();
        assertNotEquals(result.get(0).getName(),doctor.getName());
    }
}