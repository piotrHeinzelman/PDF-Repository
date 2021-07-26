package com.heinzelman.pdf_repo.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;


    @Test
    void save() {


        Project project = new Project( " P172 ");
        //System.out.println( project );
        projectService.save( project );
        //System.out.println( project );
    }
}