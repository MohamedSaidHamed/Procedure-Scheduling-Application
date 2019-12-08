package com.procedure.demo.schedulingapp.guiViews;

import com.procedure.demo.schedulingapp.controller.PatientController;
import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.utilities.DateUtil;
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
    private ComboBox<String> sexList;
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

    /**
     * Initialize method, filling out the sex comboBox with gender types
     */
    @FXML
    public void initialize() {
        sexList.getItems().setAll("Male", "Female", "Unknown");
    }

    /**
     * A method to save new patient.
     * Extracting the view components' values and create a new patient instance to be saved.
     */
    @FXML
    private void savePatient() {
        Patient patient = new Patient();
        patient.setSex(sexList.getSelectionModel().getSelectedItem());
        patient.setName(fullNameField.getText());
        patient.setDob(DateUtil.convertToDateViaInstant(dob.getValue()));
        patientController.updatePatient(patient);
        NewSceneHandler.showAlert(Alert.AlertType.INFORMATION, "Registration Successful!",
                "Patient " + patient.getName() + " saved successfully");
        fullNameField.clear();
        sexList.getSelectionModel().clearSelection();
        dob.setValue(null);
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
