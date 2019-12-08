package com.procedure.demo.schedulingapp.guiController;

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
    private Button addPatientBtn;
    @FXML
    private Button scheduleProcedureBtn;
    @FXML
    private Button viewProceduresBtn;

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


    /**
     * A method to navigate to newPatientView.fxml view
     *
     * @throws Exception
     */
    @FXML
    private void changeToPatient() throws Exception {
        Stage scene = (Stage) addPatientBtn.getScene().getWindow();
        new NewSceneHandler().sceneHandler(newPatientResource, scene, applicationContext);
    }

    /**
     * A method to navigate to scheduleProcedureView.fxml view
     *
     * @throws Exception
     */
    @FXML
    private void changeToProcedure() throws Exception {
        Stage scene = (Stage) scheduleProcedureBtn.getScene().getWindow();
        new NewSceneHandler().sceneHandler(scheduleProcedureResource, scene, applicationContext);
    }

    /**
     * A method to navigate to proceduresListView.fxml view
     *
     * @throws Exception
     */
    @FXML
    private void viewProcedures() throws Exception {
        Stage scene = (Stage) viewProceduresBtn.getScene().getWindow();
        new NewSceneHandler().sceneHandler(proceduresListResource, scene, applicationContext);
    }
}
