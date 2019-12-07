package com.procedure.demo.schedulingapp.uiController;

import com.procedure.demo.schedulingapp.uiController.SchedulingappUI.StageReadyEvent;
import com.procedure.demo.schedulingapp.utilities.ExceptionHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    public final Double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() / 1.5;
    public final Double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() / 1.5;

    @Value("classpath:/ui/fxml/mainView.fxml")
    public Resource viewResource;

    private String applicationTitle;
    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::showError);
            FXMLLoader fxmlLoader = new FXMLLoader(viewResource.getURL());
            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    return applicationContext.getBean(param);
                }
            });
            Parent parent = fxmlLoader.load();
            Stage primaryStage = stageReadyEvent.getStage();
            primaryStage.setScene(new Scene(parent, WIDTH, HEIGHT));
            primaryStage.setTitle(applicationTitle);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
