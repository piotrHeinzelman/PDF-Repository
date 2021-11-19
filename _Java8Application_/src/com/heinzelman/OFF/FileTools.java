package com.heinzelman;



import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileTools {


    public void saveFile ( String inFile_path , String code , int type  )  {


        File theDir = new File(  Main.PATH + "/" + code  );
            if (!theDir.exists()){ theDir.mkdirs(); }

            File inFile = new File ( inFile_path );
        if( !inFile.exists() ) return;
        if( !inFile.getName().endsWith( ".pdf" ) ) return;


        try {
            Path originalPath = inFile.toPath();
            Path target = Paths.get(  Main.PATH + "/" + code + "/" + code + "_" + type + ".pdf" );
            Files.copy(originalPath, target, StandardCopyOption.REPLACE_EXISTING);
        } catch ( IOException e ) {
            System.out.println( e );
        }


        System.out.println( "result"+"\nloaded file " + code + "_" + type );


    }



    // :- ) !!
    @Test
    public void saveFileTest(){
        saveFile( "C:\\Users\\admin\\Desktop\\1.pdf" , "123"  , 1 );
    }



    // *********** L I S T

    /*
    @RequestMapping(value = {"/list"} , method = RequestMethod.GET )
    public String listFile ( Model model ){
        return "list";
    }


    @RequestMapping(value = {"/list"} , method = RequestMethod.POST )
    public String listFile_POST ( Model model, FilesForm form ) throws IOException {


        return "list";
    }



    @RequestMapping(value = {"/list/{code}/{name}"} , method = RequestMethod.GET ,  produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource getFile_GET (@PathVariable String code , @PathVariable String name ) throws IOException { //ResponseEntity<byte[]>

        File productDir = new File(  homepath + code  ); if (!productDir.exists()){ return null; }
        File targetFile = new File(  homepath + code +"/"+name ); if (!targetFile.exists()){ return null; }

        return new FileSystemResource( targetFile );


    }




    @RequestMapping(value = {"/listmont/{code}"} , method = RequestMethod.GET ,  produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource getFile_GET (@PathVariable String code ) throws IOException { //ResponseEntity<byte[]>



        File productDir = new File(  homepath + code  ); if (!productDir.exists()){ return null; }
        File one = new File(  homepath + code +"/"+code+"_A.pdf" ); if (!one.exists()){ return null; }
        File two = new File(  homepath + code +"/"+code+"_AL.pdf" ); if (!one.exists()){ return null; }


        JoinPDFs j = new JoinPDFs();
        // String joinName = j.join( homepath + code + "/" + code + "_" + "AL" + ".pdf"  , homepath + code + "/" + code + "_" + "A" + ".pdf"  );
        String joinName = j.join( one , two );


        System.out.println(  joinName );

        Stamper s = new Stamper();
        s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );


        return new FileSystemResource( new File (  "c:\\temp\\out_stamp.pdf" ) );


    }
    */
}
