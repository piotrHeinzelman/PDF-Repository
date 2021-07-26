package com.heinzelman.pdf_repo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    String name = " myName ";
    String expName = "MYNAME";
    @Test
    void constructor_withNoParams() {
        Project project = new Project();
        assertNotNull( project );
    }

    @Test
    void constructor_withName(){
        Project project = new Project( name );
        assertNotNull( project );
        assertEquals( expName , project.getName() );
    }

}