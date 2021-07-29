package com.heinzelman.pdf_repo.controller;

import com.heinzelman.pdf_repo.model.*;
import com.itextpdf.text.pdf.PdfName;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PDFNameService pdfNameService;

    @Autowired
    private ProjectController projectController;


    @Test
    void listProject_POST(){


        Project one = new Project("one");
        one = projectService.save(one);
        Long id = one.getId();


        Optional<Project> reply = projectService.findById( id );
        Project project = reply.get();

        PDFName pdfName = new PDFName();
        pdfName.setOldFileName("oldName.pdf");
        pdfName = pdfNameService.save( pdfName );

        System.out.println( project +"\n" +pdfName );

        Map<PdfType, PDFName> pdfs = one.getPdfs();
        pdfs.put( PdfType.A , pdfName );

        projectService.save( one );

        System.out.println( pdfs );


    }





    @Test
    void getFileNameById_Test() {

        System.out.println( projectController.getFileNameById( 1L , PdfType.A  ));
        System.out.println( projectController.getFileNameById( 9L , PdfType.AO ));
        System.out.println( projectController.getFileNameById( 11L , PdfType.T ));
        System.out.println( projectController.getFileNameById( 10L , PdfType.C ));
    }

    @Test
    void joinArrayOfPDF_test() {
        String folder = "C:\\temp\\";
        String reply = projectController.joinArrayOfPDF( new String[]{ "C:\\Users\\pheinzelman\\Desktop\\ok.pdf" , "C:\\Users\\pheinzelman\\Desktop\\sro.pdf"} , false );
            System.out.println( reply );
        reply = projectController.joinArrayOfPDF( new String[]{ "C:\\Users\\admin\\Desktop\\ao.pdf" , "C:\\Users\\admin\\Desktop\\a.pdf"} , false );
        System.out.println( reply );
    }



}