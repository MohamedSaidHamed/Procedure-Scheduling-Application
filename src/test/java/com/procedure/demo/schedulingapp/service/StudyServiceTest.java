package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.*;
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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StudyServiceTest extends ApplicationTest {

    @Autowired
    StudyService studyService;
    @Autowired
    RoomService roomService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    @BeforeAll
    static void beforeAll() throws Exception {
        ApplicationTest.launch(SchedulingappUI.class);
    }

    @Test
    void updateValidStudy() {
        Study study = new Study();
        Room room = new Room();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        room.setName("Room A");
        doctor.setName("Doctor A");
        patient.setName("Test Patient");
        study.setPatient(patientService.updatePatient(patient));
        study.setDoctor(doctorService.findAllDoctors().get(0));
        study.setRoom(roomService.getAllRooms().get(0));
        study.setStatus(StudyStatus.Planned);
        study.setPlannedStartTime(new Date());
        study.setPlannedStartTime(new Date());
        study.setDescription("Test description");
        Study result = studyService.updateStudy(study);
        assertEquals(result,study);
    }
    @Test
    void updateInValidStudy() {
        assertThrows(RuntimeException.class, () -> studyService.updateStudy(new Study()));
    }

    @Test
    void getAllStudiesCountWithDataPersisted() {
        Study study = new Study();
        Room room = new Room();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        room.setName("Room A");
        doctor.setName("Doctor A");
        patient.setName("Test Patient");
        study.setPatient(patientService.updatePatient(patient));
        study.setDoctor(doctorService.findAllDoctors().get(0));
        study.setRoom(roomService.getAllRooms().get(0));
        study.setStatus(StudyStatus.Planned);
        study.setPlannedStartTime(new Date());
        study.setPlannedStartTime(new Date());
        study.setDescription("Test description");
        studyService.updateStudy(study);
        int result = studyService.getAllStudiesCount();
        assertEquals(1,result);
    }
    @Test
    void getAllStudiesCountWithoutDataPersisted() {
        int result = studyService.getAllStudiesCount();
        assertEquals(0,result);
    }

    @Test
    void getEmptyLazyStudyList() {
        List<Study> result = studyService.getLazyStudyList(0, 1);
        assertEquals(Collections.emptyList(),result);
    }

    @Test
    void getLazyStudyList() {
        Study study = new Study();
        Room room = new Room();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        room.setName("Room A");
        doctor.setName("Doctor A");
        patient.setName("Test Patient");
        study.setPatient(patientService.updatePatient(patient));
        study.setDoctor(doctorService.findAllDoctors().get(0));
        study.setRoom(roomService.getAllRooms().get(0));
        study.setStatus(StudyStatus.Planned);
        study.setPlannedStartTime(new Date());
        study.setPlannedStartTime(new Date());
        study.setDescription("Test description");
        study = studyService.updateStudy(study);
        List<Study> result = studyService.getLazyStudyList(0, 1);
        assertEquals(result.get(0).getId(), study.getId());
    }
}