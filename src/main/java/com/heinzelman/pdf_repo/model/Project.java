package com.heinzelman.pdf_repo.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "pdfs")
    @ManyToMany
    private Map<PdfType, PDFName> pdfs = new HashMap<>();

    public Project() {}

    public Project( String name ) {
        setName( name );
    }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
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


