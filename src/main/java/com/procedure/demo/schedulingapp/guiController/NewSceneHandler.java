package com.procedure.demo.schedulingapp.guiController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class NewSceneHandler {
    //public variables for the main screen width and height
    public final Double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() / 1.5;
    public final Double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() / 1.5;

    /**
     * A method to handle the navigation between new scenes and pass any exceptions to the ExceptionHandler class
     *
     * @param viewResource
     * @param stage
     * @param applicationContext
     * @throws Exception
     */
    public void sceneHandler(Resource viewResource, Stage stage, ApplicationContext applicationContext) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::showError);
        FXMLLoader fxmlLoader = new FXMLLoader(viewResource.getURL());
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return applicationContext.getBean(param);
            }
        });
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent, WIDTH, HEIGHT));
    }

    /**
     * A method to show alerts to users.
     *
     * @param alertType
     * @param title
     * @param message
     */
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        errorButton.setId("alertDialogBtn");
        alert.show();
    }
}
