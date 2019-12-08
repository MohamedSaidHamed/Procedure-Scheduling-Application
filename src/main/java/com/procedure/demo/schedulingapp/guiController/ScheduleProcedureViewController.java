package com.procedure.demo.schedulingapp.guiController;

import com.procedure.demo.schedulingapp.controller.DoctorController;
import com.procedure.demo.schedulingapp.controller.PatientController;
import com.procedure.demo.schedulingapp.controller.RoomController;
import com.procedure.demo.schedulingapp.controller.StudyController;
import com.procedure.demo.schedulingapp.entity.*;
import com.procedure.demo.schedulingapp.utilities.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    /**
     * Initialize and retrieve available rooms, doctors and patients and assign them to the corresponding comboboxes
     *
     * @throws Exception
     */
    @FXML
    private void initialize() {
        List<Room> rooms = roomController.getAllRooms();
        List<Patient> patients = patientController.getAllPatients();
        List<Doctor> doctors = doctorController.getAllDoctors();
        roomField.getItems().addAll(rooms);
        patientField.getItems().addAll(patients);
        doctorField.getItems().addAll(doctors);
        statusCombo.getItems().setAll(StudyStatus.values());
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
    }

    /**
     * A method to save new study.
     * Extracting the view components' values and create a new study instance to be saved.
     */
    @FXML
    private void saveProcedure() {
        Study study = new Study();
        study.setDescription(desc.getText());
        study.setPatient(patientField.getSelectionModel().getSelectedItem());
        study.setDoctor(doctorField.getSelectionModel().getSelectedItem());
        study.setRoom(roomField.getSelectionModel().getSelectedItem());
        study.setStatus(statusCombo.getSelectionModel().getSelectedItem());
        if (startDate.getValue() != null && startDate.getValue() != null) {
            study.setPlannedStartTime(DateUtil.convertToDateViaSqlTimestamp(LocalDateTime.of(startDate.getValue(), startTime.getValue())));
        }
        if (endDate.getValue() != null && endTime.getValue() != null) {
            study.setPlannedEndTime(DateUtil.convertToDateViaSqlTimestamp(LocalDateTime.of(endDate.getValue(), endTime.getValue())));
        }
        studyController.updateStudy(study);
        NewSceneHandler.showAlert(Alert.AlertType.INFORMATION, "Procedure Planned!",
                "Procedure for " + study.getPatient().getName() + " saved successfully");
        patientField.getSelectionModel().clearSelection();
        doctorField.getSelectionModel().clearSelection();
        roomField.getSelectionModel().clearSelection();
        statusCombo.getSelectionModel().clearSelection();
        desc.setText("");
    }

    /**
     * A method to navigate to the main view
     *
     * @throws Exception
     */
    @FXML
    private void backNavigator() throws Exception {
        Stage scene = (Stage) backButton.getScene().getWindow();
        new NewSceneHandler().sceneHandler(mainViewResource, scene, applicationContext);
    }

}
