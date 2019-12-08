package com.procedure.demo.schedulingapp.utilities;


import com.procedure.demo.schedulingapp.entity.Doctor;
import com.procedure.demo.schedulingapp.entity.Patient;
import com.procedure.demo.schedulingapp.entity.Room;
import com.procedure.demo.schedulingapp.entity.StudyStatus;

import java.util.Date;

public class ValidatorUtil {
    public static final Validation <String> notNullString = GenericValidation.from(s -> s != null);
    public static final Validation <String> notEmptyString = GenericValidation.from(s -> !s.isEmpty());
    public static final Validation <Patient> notNullPatient = GenericValidation.from(s -> s !=null);
    public static final Validation <Doctor> notNullDoctor = GenericValidation.from(s ->s !=null);
    public static final Validation <Room> notNullRoom = GenericValidation.from(s ->s !=null);
    public static final Validation <StudyStatus> notNullStatus = GenericValidation.from(s ->s !=null);
}
