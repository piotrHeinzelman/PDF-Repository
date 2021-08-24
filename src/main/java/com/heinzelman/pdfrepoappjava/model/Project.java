package com.heinzelman.pdfrepoappjava.model;
/*
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "projects")
*/
public class Project {
 /*

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "pdfs")
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Map<PdfType, PDFName> pdfs = new HashMap<>();

    public Project() {}

    public Project( String name ) {
        setName( name );
    }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Map<PdfType, PDFName> getPdfs() {
        return pdfs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPdfs(Map<PdfType, PDFName> pdfs) {
        this.pdfs = pdfs;
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


*/

}


