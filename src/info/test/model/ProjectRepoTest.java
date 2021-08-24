package com.heinzelman.pdfrepoappjava.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepoTest {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private PDFNameRepo pdfNameRepo;


    @Test
    void save() {
        Project p170 = new Project("p170");
        assertNotNull( p170 );
        assertNull( p170.getId() );
        assertEquals( "P170" , p170.getName() );
        projectRepo.save( p170 );
        assertNotNull( p170.getId() );
    }
}