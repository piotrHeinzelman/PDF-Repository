package com.heinzelman.pdfrepoappjava.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PDFNameServiceTest {

    @Autowired
    private  PDFNameService pdfNameService;


    @Test
    void saveService(){
        PDFName tech = new PDFName();
        tech.setOldFileName("c:\\temp\\some_Tech.pdf");
        assertNotNull( tech );
        assertNull( tech.getId() );
        tech = pdfNameService.save( tech );
        System.out.println( tech );
        assertNotNull( tech.getId() );

    }


}