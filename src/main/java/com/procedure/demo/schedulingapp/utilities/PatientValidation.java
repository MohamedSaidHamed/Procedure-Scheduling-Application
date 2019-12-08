package com.procedure.demo.schedulingapp.utilities;

import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.guiViews.ExceptionHandler;

public class PatientValidation {
    public static void validatePatientObject(Patient patient){
        StringBuilder errorFields = new StringBuilder();
        errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
                .test(patient.getName())
                .getFieldNameIfInvalid("Patient name is required").orElse(""));

        if(!errorFields.toString().isEmpty()){
            throw new ExceptionHandler(errorFields.toString());
        }
    }
}
