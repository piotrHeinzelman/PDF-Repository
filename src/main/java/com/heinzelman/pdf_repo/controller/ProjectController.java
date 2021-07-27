package com.heinzelman.pdf_repo.controller;

import com.heinzelman.pdf_repo.model.*;
import com.heinzelman.pdf_repo.service.JoinPDFs;
import com.heinzelman.pdf_repo.service.Stamper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Folder;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final PDFNameService pdfNameService;

    public ProjectController(ProjectService projectService, PDFNameService pdfNameService) {
        this.projectService = projectService;
        this.pdfNameService = pdfNameService;
    }

    @Value("${targetfolder}")
    private String folder;


    @GetMapping("/project")
    public String listProject( Model model ){
        //System.out.println( folder );
        return "project_list";
    }




    @PostMapping("/projectfind")
    public String listProject_POST(Model model , @RequestParam String code ){
        if ( code==null )                                                      {  return "index" ;  }
        code = code.trim().toUpperCase();
        if ( code.equals(""))                                                  {  return "index" ;  }

        List<Project> projectList = projectService.findByNameContains( code );
        model.addAttribute( "projectList", projectList );

        return "index";
    }



    @PostMapping("/projectadd")
    public String addProject(Model model , @RequestParam String code ){

        if ( code==null )                                                      { return "index" ; }
        code = code.trim().toUpperCase();
        if ( code.equals(""))                                                  { return "index" ; }

        Project newProject = projectService.findByName( code ).orElse( new Project( code ) );

        if ( newProject.getId()==null ) projectService.save( newProject );

        model.addAttribute("projectList", List.of( newProject ));
        return "index";
    }







    @GetMapping("/project/{id}")
    public String listProject_POST(Model model , @PathVariable Long id){
        if ( id==null ) return "index";

        Optional<Project> reply  = projectService.findById( id );
        if ( reply.isEmpty() ) return "index";
        Project project = reply.get();

        String code = project.getName(); model.addAttribute("code",code);
                                         model.addAttribute("id",project.getId());
                                         model.addAttribute("values", PdfType.values() );
         for ( PdfType type : PdfType.values() ) {
             model.addAttribute(  type.name() , project.getPdfs().get( type )  );
             System.out.println( type.name() + " : " + project.getPdfs().get( type ) );
         }

        return "index";
    }




    @PostMapping("/pdf_add")
    public String addProject(Model model , @RequestParam Long id,
            @RequestParam MultipartFile A , @RequestParam MultipartFile AO ,
            @RequestParam MultipartFile T , @RequestParam MultipartFile TO ,
            @RequestParam MultipartFile C , @RequestParam MultipartFile CO ){

        Optional<Project> reply = projectService.findById( id );
        if ( reply.isEmpty() ) return "project_list";
        Project project = reply.get();

        if ( A!=null ) {
            PDFName pdfName = new PDFName();
                    pdfName.setOldFileName( A.getOriginalFilename() );
                    pdfName = pdfNameService.save( pdfName );
                    saveFile (   pdfName.getId(), A ,  model );
        };

        if ( AO!=null ) {
            PDFName pdfName = new PDFName();
            pdfName.setOldFileName( AO.getOriginalFilename() );
            pdfName = pdfNameService.save( pdfName );
            saveFile (   pdfName.getId(), AO ,  model );
        };

        if ( T!=null ) {
            PDFName pdfName = new PDFName();
            pdfName.setOldFileName( T.getOriginalFilename() );
            pdfName = pdfNameService.save( pdfName );
            saveFile (   pdfName.getId(), T ,  model );
        };

        if ( TO!=null ) {
            PDFName pdfName = new PDFName();
            pdfName.setOldFileName( TO.getOriginalFilename() );
            pdfName = pdfNameService.save( pdfName );
            saveFile (   pdfName.getId(), TO ,  model );
        };

        if ( C!=null ) {
            PDFName pdfName = new PDFName();
            pdfName.setOldFileName( C.getOriginalFilename() );
            pdfName = pdfNameService.save( pdfName );
            saveFile (   pdfName.getId(), C ,  model );
        };

        if ( CO!=null ) {
            PDFName pdfName = new PDFName();
            pdfName.setOldFileName( CO.getOriginalFilename() );
            pdfName = pdfNameService.save( pdfName );
            saveFile (   pdfName.getId(), CO ,  model );
        };

        model.addAttribute("projectList", project );
        return "project_list";
    }





    @GetMapping( value = "/projectmont/{id}",  produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource projectMont_GET(Model model , @PathVariable Long id)  throws IOException {
        if ( id==null ) return null; //"project_list";

        Optional<Project> reply  = projectService.findById( id );
        System.out.println( reply );
        if ( reply.isEmpty() ) return null;//"project_list";
        Project project = reply.get();

        String code = project.getName(); model.addAttribute("code",code);

/*
        File productDirOne = new File(  folder + "0" + ( project.getA().getId()/100)  );
        System.out.println( productDirOne );
        if (!productDirOne.exists()){ return null ; }

        File one = new File(  productDirOne + "/" + project.getA().getId() + ".pdf"  );
        System.out.println(one);
        if (!one.exists()){ return null ; }

        File productDirTwo = new File(  folder + "0" + ( project.getAO().getId()/100)  ); if (!productDirTwo.exists()){ return null ; }
        File two = new File(  productDirTwo + "/" + project.getAO().getId() + ".pdf"  ); if (!two.exists()){ return null ; }


        JoinPDFs j = new JoinPDFs();
        String joinName = j.join( one , two  );


        //System.out.println(  joinName );

        Stamper s = new Stamper();
        s.stamp(  joinName ,  " * napis * " , " strasznieTrudneHasl0" );
*/

        return new FileSystemResource( new File ( folder + "tmp_stamp.pdf" ) );

       // return "pdf_add";
    }












    private void saveFile ( Long id , MultipartFile filePath , Model model ) {

        String suffix = "0" + ( id/100);

        File theDir = new File(  folder  + suffix  ); if (!theDir.exists()){ theDir.mkdirs(); }

        try (InputStream in = filePath.getInputStream();
             OutputStream out = new FileOutputStream( folder  + suffix + "/" + id + ".pdf" ); ) {
            IOUtils.copy(in, out);
        } catch (Exception ex) {
            model.addAttribute("error", model.getAttribute("error") + "\n" + ex);
        }
        model.addAttribute("result", model.getAttribute("\nloaded file " + filePath.getOriginalFilename() + " / " + filePath.getName() ) );
    }


    public String getFileNameById(  Long id , String type ){

        String prefix = "0" + ( Long )( id / 10 );
        String name = "";
        switch ( type ) {
            case "A": name = "1"; break;
            case "AO": name = "2"; break;
            case "T": name = "3"; break;
            case "TO": name = "4"; break;
            case "C": name = "5"; break;
            case "CO": name = "6"; break;
        }

        String out = folder + "/" + prefix;
        File f = new File(out);
        if ( ! f.exists() ) f.mkdir();

        return prefix + "/" + id + "" + name + ".pdf";
    }

}





