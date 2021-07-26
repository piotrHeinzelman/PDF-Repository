package com.heinzelman.pdf_repo.service;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JoinPDFs {

    public String join( String one, String two ) throws FileNotFoundException, IOException {

            File file1 = new File( one );
            File file2 = new File( two );

            //Instantiating PDFMergerUtility class
            PDFMergerUtility PDFmerger = new PDFMergerUtility();

            //Setting the destination file
            PDFmerger.setDestinationFileName( "c:\\temp\\out.pdf");

            //adding the source files
            PDFmerger.addSource(file1);
            PDFmerger.addSource(file2);

            //Merging the two documents
            PDFmerger.mergeDocuments(  MemoryUsageSetting.setupMainMemoryOnly() ); //   mergeDocuments();
            System.out.println("Documents merged");



            return PDFmerger.getDestinationFileName();
        }

}
