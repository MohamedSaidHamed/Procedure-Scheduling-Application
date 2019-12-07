package com.procedure.demo.schedulingapp.controller;

import com.procedure.demo.schedulingapp.entity.Study;
import com.procedure.demo.schedulingapp.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyController {
    @Autowired
    StudyService studyService;

    public List<Study> getAllStudies() {
        return studyService.getAllStudies();
    }

    public void updateStudy(Study study){
        studyService.updateStudy(study);
    }
}
