package com.procedure.demo.schedulingapp.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class NewSceneHandler {

    public final Double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() / 1.5;
    public final Double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() / 1.5;

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
}
