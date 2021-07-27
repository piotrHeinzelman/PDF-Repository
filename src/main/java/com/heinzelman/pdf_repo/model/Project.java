package com.heinzelman.pdf_repo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_id", referencedColumnName = "id")
    private PDFName A;  // Arch

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ao_id", referencedColumnName = "id")
    private PDFName AO; // Arch cOver

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_id", referencedColumnName = "id")
    private PDFName T;  // Tech

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private PDFName TO; // Tech cOver

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id", referencedColumnName = "id")
    private PDFName C;  // Calculation

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_id", referencedColumnName = "id")
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
