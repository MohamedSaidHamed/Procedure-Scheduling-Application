package com.procedure.demo.schedulingapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Lob
    @Column(nullable = false)
    private String description;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyStatus status;
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedStartTime;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedEndTime;
    @NotNull
    @JoinColumn(name = "patient", referencedColumnName = "pid", nullable = false)
    @ManyToOne
    private Patient patient;
    @NotNull
    @JoinColumn(name = "doctor", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Doctor doctor;
    @NotNull
    @JoinColumn(name = "room", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Room room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public Date getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(Date plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public Date getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(Date plannedEndTime) {
        this.plannedEndTime = plannedEndTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return Objects.equals(id, study.id) &&
                Objects.equals(description, study.description) &&
                status == study.status &&
                Objects.equals(plannedStartTime, study.plannedStartTime) &&
                Objects.equals(plannedEndTime, study.plannedEndTime) &&
                Objects.equals(patient, study.patient) &&
                Objects.equals(doctor, study.doctor) &&
                Objects.equals(room, study.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, plannedStartTime, plannedEndTime, patient, doctor, room);
    }
}

