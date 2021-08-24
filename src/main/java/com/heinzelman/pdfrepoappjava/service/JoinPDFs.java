package com.heinzelman.pdfrepoappjava.service;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JoinPDFs {

        //@Value("${targetfolder}")
        private String folder;

    public String join( File file1, File file2 ) throws FileNotFoundException, IOException {



        //    File file1 = new File( one );
        //    File file2 = new File( two );

            //Instantiating PDFMergerUtility class
            PDFMergerUtility PDFmerger = new PDFMergerUtility();

            //Setting the destination file
            PDFmerger.setDestinationFileName( folder + "tmp.pdf");

            //adding the source files
            PDFmerger.addSource(file1);
            PDFmerger.addSource(file2);

            //Merging the two documents
            PDFmerger.mergeDocuments(  MemoryUsageSetting.setupMainMemoryOnly() ); //   mergeDocuments();
            System.out.println("Documents merged");



            return PDFmerger.getDestinationFileName();
        }






        public String joinArray(  String[] files , File result ) throws FileNotFoundException, IOException {

                PDFMergerUtility PDFmerger = new PDFMergerUtility();

                String resultAsString = result.getPath().toString();
                //Setting the destination file
                PDFmerger.setDestinationFileName( resultAsString );

                //adding the source files
                for ( int i = 0 ; i < files.length ; i++ ) {
                        File f = new File ( files[i]);
                        if ( f.exists() ) PDFmerger.addSource( f ) ;
                }

                //Merging the two documents

                PDFmerger.mergeDocuments(  MemoryUsageSetting.setupMainMemoryOnly() );
                // PDFmerger.mergeDocuments( MemoryUsageSetting.setupTempFileOnly( 1000000000 ) ); //  MemoryUsageSetting.setupMainMemoryOnly() ); //   mergeDocuments();
                return PDFmerger.getDestinationFileName();
        }

}
