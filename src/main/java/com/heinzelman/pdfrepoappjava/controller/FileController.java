package com.heinzelman.pdfrepoappjava.controller;

/*
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
*/


//@Controller
public class FileController {

    private String homepath = "c:/temp/"; //  "c:/Users/admin/Desktop/SYSTEM/"; // "P:/TMP/ph/PDF/SYSTEM/" ; //

/*
    @RequestMapping(value = {"/add"} , method = RequestMethod.GET )
    public String addFile ( Model model ){
        return "add";
    }

    @RequestMapping(value = {"/add"} , method = RequestMethod.POST )
    public String addFile ( Model model, FilesForm form ) throws IOException {

        if ( form.getId()==null ) { model.addAttribute("error_" , " *** brak kodu *** " ) ; return  addFile ( model ); }
        return "add";
    }


    private void saveFile ( String code , MultipartFile filePath , String suffix , Model model ) {

        File theDir = new File(  homepath + code  ); if (!theDir.exists()){ theDir.mkdirs(); }

        try (InputStream in = filePath.getInputStream();
            OutputStream out = new FileOutputStream( homepath + code + "/" + code + "_" + suffix + ".pdf" ); ) {
            IOUtils.copy(in, out);
        } catch (Exception ex) {
            model.addAttribute("error", model.getAttribute("error") + "\n" + ex);
        }
        model.addAttribute("result", model.getAttribute("\nloaded file " + code + "_" + suffix ) );
    }



    // *********** L I S T


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
        String joinName = j.join( one , two );

        System.out.println(  joinName );

        Stamper s = new Stamper();
        s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );

        return new FileSystemResource( new File (  "c:\\temp\\out_stamp.pdf" ) );

    }

 */
}
