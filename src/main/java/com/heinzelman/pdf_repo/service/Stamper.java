package com.heinzelman.pdf_repo.service;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;

public class Stamper {

    public void stamp( String path, String text , String password ) {

        try {
            String FilenameIn = path;
            String FilenameOut = "c:\\temp\\out_stamp.pdf";



            //Create PdfReader instance.
            PdfReader pdfReader = new PdfReader( FilenameIn ) ; //"C:\\Users\\pheinzelman\\Desktop\\TestFile.pdf");

            //Create PdfStamper instance.
            PdfStamper pdfStamper = new PdfStamper(pdfReader,  new FileOutputStream( FilenameOut )) ; // "C:\\Users\\pheinzelman\\Desktop\\ModifiedTestFile.pdf"));

            byte[] userPass =  null;
            byte[] ownerPass = password.getBytes();

            pdfStamper.setEncryption( userPass, ownerPass, 4, PdfEncryption.STANDARD_ENCRYPTION_128);
            // 0 - zablokowany druk
            // only printing allowed



            //Create BaseFont instance.
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
                pageContentByte.setCMYKColorFill(100,0,100,0);

                pageContentByte.setTextMatrix(5, 5);

                Rectangle pageSize = ( pdfReader.getPageSize(i) );

                pageContentByte.setTextMatrix( 2 , 1 , -1 , 2 , 15 , 15 ); // pageSize.getWidth()-10
                // b+c rotate
                // a+d skala
                // x+y poczatek

                //Write text
                pageContentByte.showText( text ) ;
                pageContentByte.endText();

                //pageContentByte.
            }




            //Close the pdfStamper.
            pdfStamper.close();

            System.out.println("PDF modified successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
