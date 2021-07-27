package com.heinzelman.pdf_repo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "pdfs")
public class PDFName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String oldFileName;
    private Long serialNumber;

    public PDFName() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PDFName pdfName = (PDFName) o;
        return Objects.equals(id, pdfName.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
