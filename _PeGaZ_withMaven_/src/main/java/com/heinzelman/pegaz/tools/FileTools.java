package com.heinzelman.pegaz.tools;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

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
                //System.out.println( "try execute :" + fileName );
                Runtime.getRuntime().exec( "explorer " + fileName );
            } catch (IOException e) { e.printStackTrace(); }
        }
    }


    public void addFile ( String targetFileName )  {
        //System.out.println( "addFile, " + targetFileName );
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

}
