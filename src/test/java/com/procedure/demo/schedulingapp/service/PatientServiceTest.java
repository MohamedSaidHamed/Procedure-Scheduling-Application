package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.guiViews.SchedulingappUI;
import org.hamcrest.Matchers;
import org.junit.Assert;
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

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PatientServiceTest extends ApplicationTest {

    @Autowired
    PatientService patientService;

    @BeforeAll
    static void beforeAll() throws Exception {
        ApplicationTest.launch(SchedulingappUI.class);
    }

    @Test
    void updateValidPatient() {
        Patient patient = new Patient();
        patient.setName("Test Patient");
        Patient result = patientService.updatePatient(patient);
        assertEquals(result,patient);
    }

    @Test
    void updateInvalidPatient(){
        assertThrows(RuntimeException.class, () -> patientService.updatePatient(new Patient()));
    }

    @Test
    void getAllPatients() {
        Patient patient = new Patient();
        patient.setName("Test Patient");
        patientService.updatePatient(patient);
        List<Patient> result = patientService.getAllPatients();
        Assert.assertThat(result, Matchers.contains(patient));
    }
}