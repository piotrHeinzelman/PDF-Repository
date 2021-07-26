package com.heinzelman.pdf_repo.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PDFNameServiceTest {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private PDFNameRepo pdfNameRepo;

    @Test
    void save() {
        PDFName arch = new PDFName();
        arch.setOldFileName("c:\\temp\\some_File.pdf");
        assertNotNull( arch );
        assertNull( arch.getId() );
        pdfNameRepo.save( arch );
        assertNotNull( arch.getId() );
    }

}