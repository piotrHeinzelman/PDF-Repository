package com.heinzelman.pdf_repo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;



    @Test
    void getFileNameById_Test() {

        System.out.println( projectController.getFileNameById( 1L , "A") );
        System.out.println( projectController.getFileNameById( 9L , "A") );
        System.out.println( projectController.getFileNameById( 11L , "A") );
        System.out.println( projectController.getFileNameById( 10L , "A") );

    }
}