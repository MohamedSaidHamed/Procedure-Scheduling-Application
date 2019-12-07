package com.procedure.demo.schedulingapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) &&
                Objects.equals(name, doctor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
