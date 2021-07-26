package com.heinzelman.pdf_repo.controller;

import com.heinzelman.pdf_repo.forms.FilesForm;
import com.heinzelman.pdf_repo.service.JoinPDFs;
import com.heinzelman.pdf_repo.service.Stamper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;



@Controller
public class FileController {

    private String homepath = "c:/temp/"; //  "c:/Users/admin/Desktop/SYSTEM/"; // "P:/TMP/ph/PDF/SYSTEM/" ; //



    @RequestMapping(value = {"/",""} , method = RequestMethod.GET )
    public String indexGet ( Model model ){
        return "index";
    }

    @RequestMapping(value = {"/add"} , method = RequestMethod.GET )
    public String addFile ( Model model ){
        return "add";
    }

    @RequestMapping(value = {"/add"} , method = RequestMethod.POST )
    public String addFile ( Model model, FilesForm form ) throws IOException {

        if ( form.getCode()==null || form.getCode().trim().toUpperCase().equals("")) { model.addAttribute("error_" , " *** brak kodu *** " ) ; return  addFile ( model ); }

        String code = form.getCode();
        if ( form.getPdfA()!= null && !form.getPdfA().isEmpty()) { saveFile (  code , form.getPdfA(), "A" , model); }
        if ( form.getPdfAL()!= null && !form.getPdfAL().isEmpty()) { saveFile (  code , form.getPdfAL(), "AL" , model ); }
        if ( form.getPdfB()!= null && !form.getPdfB().isEmpty()) { saveFile (  code , form.getPdfB(), "B" , model ); }
        if ( form.getPdfBL()!= null && !form.getPdfBL().isEmpty()) { saveFile (  code , form.getPdfBL(), "BL" , model ); }

        /*
        JoinPDFs j = new JoinPDFs();
        String joinName = j.join( homepath + code + "/" + code + "_" + "AL" + ".pdf"  , homepath + code + "/" + code + "_" + "A" + ".pdf"  );

        System.out.println(  joinName );

        Stamper s = new Stamper();
           s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );
        */
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

        if ( form.getCode()==null || form.getCode().trim().toUpperCase().equals("")) { model.addAttribute("error" , " *** brak kodu *** " ) ; return  listFile ( model ); }
        String code = form.getCode();

        File productDir = new File(  homepath + code  ); if (!productDir.exists()){ /* model.addAttribute("error" , " *** brak kodu *** " ) ; */ return  listFile ( model ); }

        model.addAttribute("fileList" , productDir.list());
        model.addAttribute("code" , code );
/*
        JoinPDFs j = new JoinPDFs();
        String joinName = j.join( homepath + code + "/" + code + "_" + "AL" + ".pdf"  , homepath + code + "/" + code + "_" + "A" + ".pdf"  );

        System.out.println(  joinName );

        Stamper s = new Stamper();
        s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );
*/

        return "list";
    }



    @RequestMapping(value = {"/list/{code}/{name}"} , method = RequestMethod.GET ,  produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource getFile_GET (@PathVariable String code , @PathVariable String name ) throws IOException { //ResponseEntity<byte[]>

        File productDir = new File(  homepath + code  ); if (!productDir.exists()){ return null; }
        File targetFile = new File(  homepath + code +"/"+name ); if (!targetFile.exists()){ return null; }

        return new FileSystemResource( targetFile );
        /*
        public FileSystemResource getFile(@ModelAttribute PdfFile pdfFile) {
            PdfFileGenerator pdfFileGenerator = new PdfFileGeneratorImpl();
            File file = pdfFileGenerator.generatePdf(pdfFile);
            return new FileSystemResource(file);
        }
        */

        //System.out.println( "OK !" );

/*
        JoinPDFs j = new JoinPDFs();
        String joinName = j.join( homepath + code + "/" + code + "_" + "AL" + ".pdf"  , homepath + code + "/" + code + "_" + "A" + ".pdf"  );

        System.out.println(  joinName );

        Stamper s = new Stamper();
        s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );
*/


    }

    /*

    response.setContentType("application/pdf");

https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc


       public FileSystemResource getFile(@ModelAttribute PdfFile pdfFile) {
            PdfFileGenerator pdfFileGenerator = new PdfFileGeneratorImpl();
            File file = pdfFileGenerator.generatePdf(pdfFile);
            return new FileSystemResource(file);
        }

@RequestMapping(value="/getpdf", method=RequestMethod.POST)
public ResponseEntity<byte[]> getPDF(@RequestBody String json) {
    // convert JSON to Employee
    Employee emp = convertSomehow(json);

    // generate the file
    PdfUtil.showHelp(emp);

    // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
    byte[] contents = (...);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    // Here you have to set the actual filename of your pdf
    String filename = "output.pdf";
    headers.setContentDispositionFormData(filename, filename);
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
    return response;
}



    * */


    @RequestMapping(value = {"/listmont/{code}"} , method = RequestMethod.GET ,  produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource getFile_GET (@PathVariable String code ) throws IOException { //ResponseEntity<byte[]>



        File productDir = new File(  homepath + code  ); if (!productDir.exists()){ return null; }
        File one = new File(  homepath + code +"/"+code+"_A.pdf" ); if (!one.exists()){ return null; }
        File two = new File(  homepath + code +"/"+code+"_AL.pdf" ); if (!one.exists()){ return null; }


        JoinPDFs j = new JoinPDFs();
        String joinName = j.join( homepath + code + "/" + code + "_" + "AL" + ".pdf"  , homepath + code + "/" + code + "_" + "A" + ".pdf"  );


        System.out.println(  joinName );

        Stamper s = new Stamper();
        s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );


        return new FileSystemResource( new File (  "c:\\temp\\out_stamp.pdf" ) );


    }
}
