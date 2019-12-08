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

    /**
     * A method that returns a lazy list list of available studies based on first item index and max return result
     *
     * @param from
     * @param limit
     * @return
     */
    public List<Study> getLazyStudyList(int from, int limit) {
        return studyService.getLazyStudyList(from, limit);
    }

    /**
     * A method that returns a count of all available studies
     *
     * @return
     */
    public int getAllStudiesCount() {
        return studyService.getAllStudiesCount();
    }

    /**
     * A method to save new study
     *
     * @param study
     */
    public void updateStudy(Study study) {
        studyService.updateStudy(study);
    }
}
