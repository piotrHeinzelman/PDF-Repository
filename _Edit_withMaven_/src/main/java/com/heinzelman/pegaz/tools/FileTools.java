package com.heinzelman.pegaz.tools;


import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;

public class FileTools {

    private final String PATH;

    public FileTools( MyConfig myConfig ) {
        PATH = myConfig.getPath();
        if ( new File(PATH).exists() ) System.out.println( "[ OK ] set working folder: " + PATH );
    }

    public String idToFileName( Long idLong , int row ) {
        if ( idLong==null) return null;

        File tmpf = new File( PATH + "" + (0+idLong/100));
        if ( !tmpf.exists() ) tmpf.mkdirs();
        tmpf = new File( PATH + "" + (0+idLong/100) + "\\"  + (0+idLong));
        if ( !tmpf.exists() ) tmpf.mkdirs();
        return PATH + "" + (0+idLong/100) + "\\"  + (0+idLong) + "\\"  + (0+idLong) + (0+row) + ".pdf" ;
    }

    public void deleteFile (  String fileName )  {
        File f = new File ( fileName );
        if (f.exists()) {
            f.delete();
        }
     }


    public void executeFile (  String fileName )  {
        File f = new File ( fileName );
        if (f.exists()) {
            try {
                Runtime.getRuntime().exec( "explorer " + fileName );
            } catch (IOException e) { e.printStackTrace(); }
        }
    }


    public void addFile ( String targetFileName )  {
        JFileChooser j = new JFileChooser( );

        // Open the save dialog
        j.showSaveDialog(null);
        if (j==null)return;
        if (!j.getSelectedFile().getName().endsWith(".pdf")) return;

        System.out.println( targetFileName );
        try {
            Path source = Path.of(j.getSelectedFile().getAbsolutePath());
            Path destination = Path.of( targetFileName );
            Files.copy( source, destination, StandardCopyOption.REPLACE_EXISTING);

        } catch ( Throwable e ){ System.out.println( e ); }

    }




    public void save_OjoinS_File ( String cover, String means, String name, int type , Boolean StampIt ){

        // SIGN IT

        System.out.println( type );

        name = name.trim().toUpperCase();

        String regex = "[*!@#$%^&()]"; // the regular expression is not valid.
        //String s1="My na*!&me is Khan. My name is Bob. My name is Sonoo.";
        name = name.replaceAll(regex,"");//replaces all occurrences of "is" to "was"
        //System.out.println( name );

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {

                //System.out.println("You selected the directory: " + jfc.getSelectedFile());
                // JOIN PDF
                //adding the source files
                try {
                    File file1 = new File( cover );
                    File file2 = new File( means );

                    switch( type ) {
                        case 1: name = name+"_Arch"; break;
                        case 3: name = name+"_Tech"; break;
                        case 5: name = name+"_Koszt"; break;
                        case 7: name = name+"_Inne"; break;
                        case 9: name = name+"_Dodatkowe"; break;
                    }

                    String outFileName = jfc.getSelectedFile()+"\\"+ name+".pdf" ;

                    //Instantiating PDFMergerUtility class
                    PDFMergerUtility PDFmerger = new PDFMergerUtility();

                    PDFmerger.addSource(file1);
                    PDFmerger.addSource(file2);

                    //Setting the destination file
                    PDFmerger.setDestinationFileName( outFileName );

                    PDFmerger.mergeDocuments(  MemoryUsageSetting.setupMainMemoryOnly() ); //   mergeDocuments();

                    
                    if ( StampIt ) {
                        // STAMP ?
                        stamp( outFileName, "text" );
                    }

                } catch( Throwable tr ){ System.out.println( tr ); };
            }
        }
        return;
    }







    public void save_PDF_File (HashSet<String> setString, String name, int type ){

//System.out.println( type );
//System.out.println( setString );

        name = name.trim().toUpperCase();

        String regex = "[*!@#$%^&()]";
        name = name.replaceAll(regex,"");

        switch( type ) {
            case 0: name = name+"_Okladka"; break;
            case 1: name = name+"_Srodki"; break;
        }


        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {

                //System.out.println("You selected the directory: " + jfc.getSelectedFile());
                // JOIN PDF
                //adding the source files
                try {
                    String outFileName = jfc.getSelectedFile()+"\\"+ name+".pdf" ;

                    //Instantiating PDFMergerUtility class
                    PDFMergerUtility PDFmerger = new PDFMergerUtility();

                    for ( String fileName: setString ){
                        //System.out.println( "\n>>>" + fileName );
                        PDFmerger.addSource( new File ( fileName ) );
                    }

                    //Setting the destination file
                    PDFmerger.setDestinationFileName( outFileName );

                    PDFmerger.mergeDocuments(  MemoryUsageSetting.setupMainMemoryOnly() ); //   mergeDocuments();

                } catch( Throwable tr ){ System.out.println( tr ); };
            }
        }
        return;
    }










        public void stamp( String FilenameIn , String text ) {

            String FilenameOut = FilenameIn.replaceAll(".pdf","_Stamp.pdf");

            try ( FileOutputStream outStream = new FileOutputStream( FilenameOut );  )  {

                //Create PdfReader instance.
                PdfReader pdfReader = new PdfReader( FilenameIn ) ; //"C:\\Users\\pheinzelman\\Desktop\\TestFile.pdf");

                //Create PdfStamper instance.
                PdfStamper pdfStamper = new PdfStamper(pdfReader,  outStream  ) ; // "C:\\Users\\pheinzelman\\Desktop\\ModifiedTestFile.pdf"));

                byte[] userPass =  null;
                byte[] ownerPass = "aJKS(@Ms0w82x2298ydh2c2AX".getBytes();

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
