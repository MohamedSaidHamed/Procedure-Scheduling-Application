package com.procedure.demo.schedulingapp.utilities;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ExceptionHandler extends RuntimeException {
    public static String exceptionMessage;

    public ExceptionHandler(String message) {
        super(message);
        this.exceptionMessage = message;
    }

    public static void showError(Thread t, Throwable e) {
        System.err.println("***Default exception handler***");
        if (Platform.isFxApplicationThread()) {
            DisplayUtil.showAlert(Alert.AlertType.ERROR, "Error!",
                    exceptionMessage== null ? "Unexpected error occurred!" : exceptionMessage);
            e.printStackTrace();
        } else {
            System.err.println("An unexpected error occurred in " + t);

        }
    }
}
