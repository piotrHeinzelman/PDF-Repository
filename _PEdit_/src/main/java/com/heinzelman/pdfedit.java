package com.heinzelman;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class pdfedit {

    public static void main(String[] args) {
        //System.out.println(args.length + " , " + args.toString());
        if (args.length != 3) { System.out.println("USAGE:  java -jar ../../../target/pdfedit.jar C:\\temp\\_PDF_data_\\inOlkadka.pdf C:\\temp\\_PDF_data_\\inOlkadka.pdf C:\\temp\\_PDF_data_\\inSrodek.pdf C:\\temp\\_PDF_data_\\out.pdf\n "); return; }


        System.out.println("0: " + args[0]+ "\n1: " + args[1] + "\n2: " + args[2] );

       try{
        File fileIn1 = new File(args[0]);
        File fileIn2 = new File(args[1]);
        if ( !fileIn1.exists()  || !fileIn1.exists() ) { return; }


           PDFMergerUtility PDFmerger = new PDFMergerUtility();
           PDFmerger.setDestinationFileName( args[2] ); // ( folder + "tmp.pdf");

           //adding the source files
           PDFmerger.addSource(fileIn1);
           PDFmerger.addSource(fileIn2);

           //Merging the two documents
           PDFmerger.mergeDocuments(  MemoryUsageSetting.setupMainMemoryOnly() ); //   mergeDocuments();
           //System.out.println("Documents merged:" + PDFmerger.getDestinationFileName() );

           //PDFmerger.



            }
            catch (RuntimeException ex) {   System.out.println( ex ); }
            catch ( FileNotFoundException ex ) { System.out.println( ex ); }
            catch ( IOException ex ) { System.out.println( ex ); }
            catch ( NoClassDefFoundError ex ) { System.out.println( ex ); }
        }


/*

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






*/



}