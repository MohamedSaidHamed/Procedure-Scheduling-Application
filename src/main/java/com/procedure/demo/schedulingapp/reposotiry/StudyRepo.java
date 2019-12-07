package com.procedure.demo.schedulingapp.reposotiry;

import com.procedure.demo.schedulingapp.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepo  extends JpaRepository<Study, Integer> {
}
