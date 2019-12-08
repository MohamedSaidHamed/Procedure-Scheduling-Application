package com.procedure.demo.schedulingapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    @NotNull
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String sex;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dob;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(pid, patient.pid) &&
                Objects.equals(name, patient.name) &&
                Objects.equals(sex, patient.sex) &&
                Objects.equals(dob, patient.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, name, sex, dob);
    }
}
