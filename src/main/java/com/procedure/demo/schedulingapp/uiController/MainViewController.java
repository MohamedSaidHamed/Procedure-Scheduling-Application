package com.procedure.demo.schedulingapp.uiController;

import com.procedure.demo.schedulingapp.utilities.NewSceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class MainViewController {
    @FXML
    public Button addPatientBtn;
    @FXML
    public Button schProBtn;
    @FXML
    public Button viewProBtn;

    @Value("classpath:/ui/fxml/newPatientView.fxml")
    private Resource newPatientResource;
    @Value("classpath:/ui/fxml/scheduleProcedureView.fxml")
    private Resource scheduleProcedureResource;
    @Value("classpath:/ui/fxml/proceduresListView.fxml")
    private Resource proceduresListResource;

    private ApplicationContext applicationContext;

    public MainViewController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @FXML
    private void changeToPatient() throws Exception {
        Stage scene = (Stage) addPatientBtn.getScene().getWindow();
        new NewSceneHandler().sceneHandler(newPatientResource, scene, applicationContext);
    }


    @FXML
    private void changeToProcedure() throws Exception {
        Stage scene = (Stage) schProBtn.getScene().getWindow();
        new NewSceneHandler().sceneHandler(scheduleProcedureResource, scene, applicationContext);
    }

    @FXML
    private void viewProcedures() throws Exception {
        Stage scene = (Stage) viewProBtn.getScene().getWindow();
        new NewSceneHandler().sceneHandler(proceduresListResource, scene, applicationContext);
    }
}
