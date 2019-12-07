package com.procedure.demo.schedulingapp.uiController;

import com.procedure.demo.schedulingapp.controller.DoctorController;
import com.procedure.demo.schedulingapp.controller.PatientController;
import com.procedure.demo.schedulingapp.controller.RoomController;
import com.procedure.demo.schedulingapp.controller.StudyController;
import com.procedure.demo.schedulingapp.entity.*;
import com.procedure.demo.schedulingapp.utilities.DateUtil;
import com.procedure.demo.schedulingapp.utilities.NewSceneHandler;
import com.procedure.demo.schedulingapp.utilities.TimeSpinner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class ScheduleProcedureViewController {
    private ApplicationContext applicationContext;
    @Autowired
    PatientController patientController;
    @Autowired
    RoomController roomController;
    @Autowired
    DoctorController doctorController;
    @Autowired
    StudyController studyController;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<Patient> patientField;
    @FXML
    private ComboBox<Room> roomField;
    @FXML
    private ComboBox<Doctor> doctorField;
    @FXML
    private ComboBox<StudyStatus> statusCombo;
    @FXML
    private TimeSpinner startTime;
    @FXML
    private DatePicker startDate;
    @FXML
    private TimeSpinner endTime;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextArea desc;
    @Value("classpath:/ui/fxml/mainView.fxml")
    private Resource mainViewResource;


    public ScheduleProcedureViewController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() {
        List<Room> rooms = roomController.getAllRooms();
        List<Patient> patients = patientController.getAllPatients();
        List<Doctor> doctors = doctorController.getAllDoctors();
        roomField.getItems().addAll(rooms);
        patientField.getItems().addAll(patients);
        doctorField.getItems().addAll(doctors);
        statusCombo.getItems().setAll(StudyStatus.values());
    }

    @FXML
    private void saveProcedure() throws Exception{
        Study study = new Study();
        study.setDescription(desc.getText());
        study.setPatient(patientField.getSelectionModel().getSelectedItem());
        study.setDoctor(doctorField.getSelectionModel().getSelectedItem());
        study.setRoom(roomField.getSelectionModel().getSelectedItem());
        study.setStatus(statusCombo.getSelectionModel().getSelectedItem());
        study.setPlannedStartTime(DateUtil.convertToDateViaSqlTimestamp(LocalDateTime.of(startDate.getValue(), startTime.getValue())));
        if (endDate.getValue() != null && endTime.getValue() != null) {
            study.setPlannedEndTime(DateUtil.convertToDateViaSqlTimestamp(LocalDateTime.of(endDate.getValue(), endTime.getValue())));
        }
        studyController.updateStudy(study);
    }

    @FXML
    private void backNavigator() throws Exception {
        Stage scene = (Stage) backButton.getScene().getWindow();
        new NewSceneHandler().sceneHandler(mainViewResource, scene, applicationContext);
    }

}
