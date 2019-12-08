package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Study;
import com.procedure.demo.schedulingapp.guiController.ExceptionHandler;
import com.procedure.demo.schedulingapp.reposotiry.StudyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyService {
    @Autowired
    StudyRepo studyRepo;

    /**
     * A method to save new study into the database.
     * It validates the entity required fields before persisting the object into database.
     * In case of validation violation, an exception will be thrown
     *
     * @param study
     */
    public Study updateStudy(Study study) {
        if (study.getPatient() == null || study.getDoctor() == null || study.getRoom() == null ||
                study.getPlannedStartTime() == null || study.getStatus() == null ||
                study.getDescription().equals(null) || study.getDescription().trim().isEmpty()) {
            throw new ExceptionHandler("Required fields are missing");
        }
        return studyRepo.save(study);
    }

    /**
     * A method that returns a count of all available studies
     *
     * @return
     */
    public int getAllStudiesCount() {
        return (int) studyRepo.count();
    }

    /**
     * A method that returns a lazy list list of available studies based on first item index and max return result.
     *
     * @param from
     * @param limit
     * @return
     */
    public List<Study> getLazyStudyList(int from, int limit) {
        Pageable pageable = PageRequest.of(from, limit);
        return studyRepo.findAll(pageable).getContent();
    }
}
