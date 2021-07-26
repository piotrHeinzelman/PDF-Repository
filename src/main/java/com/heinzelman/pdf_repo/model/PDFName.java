package com.heinzelman.pdf_repo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PDFName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String oldFileName;
    private Long serialNumber;
    private Boolean isValid;

    public PDFName() {}

}
