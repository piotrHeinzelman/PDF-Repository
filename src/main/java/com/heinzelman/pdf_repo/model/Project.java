package com.heinzelman.pdf_repo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private PDFName A;  // Arch
    private PDFName AO; // Arch cOver
    private PDFName T;  // Tech
    private PDFName TO; // Tech cOver
    private PDFName C;  // Calculation
    private PDFName CO; // Calculation cOver

    public Project() {}

    public Project( String name ) {
        setName( name );
    }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", A=" + A +
                ", AO=" + AO +
                ", T=" + T +
                ", TO=" + TO +
                ", C=" + C +
                ", CO=" + CO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
