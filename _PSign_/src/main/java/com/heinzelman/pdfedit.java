package com.heinzelman;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class pdfedit {

    public static void main(String[] args) {
        if (args.length != 3) { System.out.println("USAGE:  java -jar pdfedit.jar C:\\temp\\_PDF_data_\\out.pdf  C:\\temp\\_PDF_data_\\out_signed.pdf informacja "); return; }

        String FilenameIn = args[0];
        String FilenameOut = args[1];

       try{
        Random rd = new Random();
        String password = "" + rd.nextInt( 199999999 )  + "" + (char) (33+rd.nextInt(2)) + "" + rd.nextInt(8888888);

        PdfReader pdfReader = new PdfReader( FilenameIn ) ;
        PdfStamper pdfStamper = new PdfStamper(pdfReader,  new FileOutputStream( FilenameOut )) ;

                   byte[] userPass =  null;
                   byte[] ownerPass = password.getBytes();

                   pdfStamper.setEncryption( userPass, ownerPass, 4, PdfEncryption.STANDARD_ENCRYPTION_128);
                   // 0 - zablokowany druk
                   // only printing allowed


                   BaseFont baseFont = BaseFont.createFont(
                                   BaseFont.TIMES_ROMAN,
                                   BaseFont.CP1252, BaseFont.NOT_EMBEDDED
                                    );


                   int pages = pdfReader.getNumberOfPages();
                   for(int i=1; i<=pages; i++) {

                       //Contain the pdf data.
                       PdfContentByte pageContentByte = pdfStamper.getOverContent(i);

                       pageContentByte.beginText();
                       pageContentByte.setFontAndSize(baseFont, 22);
                       pageContentByte.setCMYKColorFill(255,0,255,0);

                       Rectangle pageSize = ( pdfReader.getPageSize(i) );
                       Phrase p = new Phrase(  args[2]  );

                       pageContentByte.saveState();

                       PdfGState gs1 = new PdfGState();
                       gs1.setFillOpacity(0.6f);
                       pageContentByte.setGState(gs1);




                       double w = pageSize.getWidth();
                       double h = pageSize.getHeight();
                       double r = Math.sqrt( h*h + w*w );
                       int arc = (int)Math.toDegrees( Math.asin( h/r ));


                       //ColumnText.showTextAligned( pageContentByte, Element.ALIGN_CENTER, p, (pageSize.getWidth()/2), (pageSize.getHeight()/2), arc );

                       //pageContentByte.showText(  args[2] ) ;
                       ColumnText.showTextAligned( pageContentByte, Element.ALIGN_CENTER, p, (pageSize.getWidth()/2), (pageSize.getHeight()/2), arc );


                       pageContentByte.endText();

                   }
                   pdfStamper.close();
            }
            catch (RuntimeException ex) {   System.out.println( ex ); }
            catch ( FileNotFoundException ex ) { System.out.println( ex ); }
            catch ( IOException ex ) { System.out.println( ex ); }
            catch ( NoClassDefFoundError ex ) { System.out.println( ex ); }
            catch ( DocumentException ex ) { System.out.println( ex ); }
        }
}


/*



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


 */