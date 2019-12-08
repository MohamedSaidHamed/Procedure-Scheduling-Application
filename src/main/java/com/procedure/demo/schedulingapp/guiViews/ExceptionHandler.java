package com.procedure.demo.schedulingapp.guiViews;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ExceptionHandler extends RuntimeException {
    public static String exceptionMessage;

    public ExceptionHandler(String message) {
        super(message);
        this.exceptionMessage = message;
    }

    /**
     * A method to console any thrown exception and shows a user friendly dialog to inform the user about the error.
     *
     * @param t
     * @param e
     */
    public static void showError(Thread t, Throwable e) {
        System.err.println("***Default exception handler***");
        if (Platform.isFxApplicationThread()) {
            NewSceneHandler.showAlert(Alert.AlertType.ERROR, "Error!",
                    exceptionMessage == null ? "Unexpected error occurred!" : exceptionMessage);
            e.printStackTrace();
        } else {
            System.err.println("An unexpected error occurred in " + t);
            NewSceneHandler.showAlert(Alert.AlertType.ERROR, "Error!",
                    "Unexpected error occurred!");

        }
    }
}
