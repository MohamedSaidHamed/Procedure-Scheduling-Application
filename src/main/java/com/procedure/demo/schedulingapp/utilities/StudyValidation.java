package com.procedure.demo.schedulingapp.utilities;

import com.procedure.demo.schedulingapp.entity.Study;
import com.procedure.demo.schedulingapp.guiViews.ExceptionHandler;

public class StudyValidation {
    public static void validateStudyObject(Study study) {
        StringBuilder errorFields = new StringBuilder();

        errorFields.append(ValidatorUtil.notNullPatient
                .test(study.getPatient())
                .getFieldNameIfInvalid("Patient is required ")
                .orElse("")).append("\n");

        errorFields.append(ValidatorUtil.notNullDoctor
                .test(study.getDoctor())
                .getFieldNameIfInvalid("Doctor is required ")
                .orElse("")).append("\n");

        errorFields.append(ValidatorUtil.notNullRoom
                .test(study.getRoom())
                .getFieldNameIfInvalid("Room is required ")
                .orElse("")).append("\n");

        errorFields.append(ValidatorUtil.notNullStatus
                .test(study.getStatus())
                .getFieldNameIfInvalid("Status is required ")
                .orElse("")).append("\n");

        errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
                .test(study.getDescription())
                .getFieldNameIfInvalid("Description is required ")
                .orElse("")).append("\n");

        if (!errorFields.toString().replace("\n","").isEmpty()) {
            throw new ExceptionHandler(errorFields.toString().trim());
        }
    }
}
