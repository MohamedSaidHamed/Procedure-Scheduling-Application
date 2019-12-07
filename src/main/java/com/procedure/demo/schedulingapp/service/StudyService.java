package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Study;
import com.procedure.demo.schedulingapp.reposotiry.StudyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyService {
    @Autowired
    StudyRepo studyRepo;

    public List<Study> getAllStudies() {
        return studyRepo.findAll();
    }

    public void updateStudy(Study study){
        studyRepo.save(study);
    }
}
