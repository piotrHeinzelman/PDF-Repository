package com.heinzelman;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class pdfedit {

    public static void main(String[] args) {
        //System.out.println(args.length + " , " + args.toString());
        if (args.length != 3) { System.out.println("USAGE:  java -jar pdfedit.jar C:\\temp\\_PDF_data_\\out.pdf  C:\\temp\\_PDF_data_\\out_signed.pdf informacja "); return; }


        System.out.println("0: " + args[0]+ "\n1: " + args[1] + "\ninfo: " + args[2] );

        String FilenameIn = args[0];
        String FilenameOut = args[1];

       try{
        // ***********
        //public void stamp( String path, String text , String password ) {

        Random rd = new Random();
        String password = "" + rd.nextInt( 199999999 )  + "" + (char) (33+rd.nextInt(2)) + "" + rd.nextInt(8888888);

        PdfReader pdfReader = new PdfReader( FilenameIn ) ;
        PdfStamper pdfStamper = new PdfStamper(pdfReader,  new FileOutputStream( FilenameOut )) ;

                   byte[] userPass =  null;
                   byte[] ownerPass = password.getBytes();

                   pdfStamper.setEncryption( userPass, ownerPass, 4, PdfEncryption.STANDARD_ENCRYPTION_128);
                   // 0 - zablokowany druk
                   // only printing allowed



/*
           p = new Phrase(
                   "This TRANSPARENT watermark is added ON TOP OF the existing content", f);
           over.saveState();
           PdfGState gs1 = new PdfGState();
           gs1.setFillOpacity(0.5f);
           over.setGState(gs1);
           ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 297, 450, 0);
           over.restoreState();
           stamper.close();
           reader.close();

*/




           BaseFont baseFont = BaseFont.createFont(
                           BaseFont.TIMES_ROMAN,
                           BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

                   //Get the number of pages in pdf.
                   int pages = pdfReader.getNumberOfPages();

                   //Iterate the pdf through pages.
                   for(int i=1; i<=pages; i++) {









                       //Contain the pdf data.
                       PdfContentByte pageContentByte = pdfStamper.getOverContent(i);

                       pageContentByte.beginText();
                       //Set text font and size.
                       pageContentByte.setFontAndSize(baseFont, 14);
                       pageContentByte.setCMYKColorFill(25,25,25,25);


                       pageContentByte.setTextMatrix(5, 5);


                       Rectangle pageSize = ( pdfReader.getPageSize(i) );

                       pageContentByte.setTextMatrix( 2 , 1 , -1 , 2 , 15 , 15 ); // pageSize.getWidth()-10
                       // b+c rotate
                       // a+d skala
                       // x+y poczatek

                       //Write text
                       pageContentByte.showText( args[2] ) ;
                       pageContentByte.endText();





                       // **********
                       BaseFont f = BaseFont.createFont(
                               BaseFont.TIMES_ROMAN,
                               BaseFont.CP1252, BaseFont.NOT_EMBEDDED);


                       Phrase p = new Phrase( "This TRANSPARENT watermark is added ON TOP OF the existing content"  );

                       pageContentByte.saveState();
                       PdfGState gs1 = new PdfGState();
                       gs1.setFillOpacity(0.3f);
                       pageContentByte.setGState(gs1);
                       ColumnText.showTextAligned(pageContentByte, Element.ALIGN_CENTER, p, 297, 450, 0);
                       pageContentByte.restoreState();

                       // ************





                   }




                   //Close the pdfStamper.
                   pdfStamper.close();


        // ***********


            }
            catch (RuntimeException ex) {   System.out.println( ex ); }
            catch ( FileNotFoundException ex ) { System.out.println( ex ); }
            catch ( IOException ex ) { System.out.println( ex ); }
            catch ( NoClassDefFoundError ex ) { System.out.println( ex ); }
            catch ( DocumentException ex ) { System.out.println( ex ); }

        }
}