package com.procedure.demo.schedulingapp.uiController;

import com.procedure.demo.schedulingapp.controller.PatientController;
import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.utilities.DateUtil;
import com.procedure.demo.schedulingapp.utilities.DisplayUtil;
import com.procedure.demo.schedulingapp.utilities.ExceptionHandler;
import com.procedure.demo.schedulingapp.utilities.NewSceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class NewPatientViewController {
    private ApplicationContext applicationContext;

    @FXML
    public Button backButton;
    @FXML
    private ComboBox<String> sexCombo;
    @FXML
    private TextField fullNameField;
    @FXML
    private DatePicker dob;

    @Value("classpath:/ui/fxml/mainView.fxml")
    private Resource mainViewResource;

    @Autowired
    PatientController patientController;

    public NewPatientViewController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() {
        sexCombo.getItems().setAll("Male", "Female", "Unknown");
    }

    @FXML
    private void savePatient() {
        if (fullNameField.getText().trim().isEmpty()) {
            throw new ExceptionHandler("Patient name is required");
        }
        Patient patient = new Patient();
        patient.setSex(sexCombo.getSelectionModel().getSelectedItem());
        patient.setName(fullNameField.getText());
        patient.setDob(new DateUtil().convertToDateViaInstant(dob.getValue()));
        patientController.updatePatient(patient);
        DisplayUtil.showAlert(Alert.AlertType.INFORMATION, "Registration Successful!",
                "Patient saved successfully");
        fullNameField.clear();
        sexCombo.getSelectionModel().clearSelection();
        dob.setValue(null);
    }

    @FXML
    private void backNavigator() throws Exception {
        Stage scene = (Stage) backButton.getScene().getWindow();
        new NewSceneHandler().sceneHandler(mainViewResource, scene, applicationContext);
    }
}
