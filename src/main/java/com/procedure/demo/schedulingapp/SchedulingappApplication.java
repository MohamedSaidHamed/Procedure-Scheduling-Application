package com.procedure.demo.schedulingapp;

import com.procedure.demo.schedulingapp.uiController.SchedulingappUI;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulingappApplication {

	public static void main(String[] args) {
		Application.launch(SchedulingappUI.class, args);
	}

}
