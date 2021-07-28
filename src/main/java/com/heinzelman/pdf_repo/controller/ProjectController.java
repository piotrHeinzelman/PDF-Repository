package com.heinzelman.pdf_repo.controller;

import com.heinzelman.pdf_repo.forms.FilesForm;
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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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


    @PostMapping("/projectfind")
    public String listProject_POST(Model model, @RequestParam String code) {
        if (code == null || code.trim().toUpperCase().equals("")) {
            return "index";
        }
        code = code.trim().toUpperCase();

        List<Project> projectList = projectService.findByNameContains(code);
        model.addAttribute("projectList", projectList);
        model.addAttribute("code", code);
        if (!hasEqualCode(projectList, code)) {
            model.addAttribute("new_code", code);
        }
        return "index";
    }


    private boolean hasEqualCode(List<Project> projectList, String code) {
        if (projectList.isEmpty()) return false;
        code = code.trim().toUpperCase();
        for (Project project : projectList) {
            if (project.getName().equals(code)) return true;
        }
        return false;
    }


    @PostMapping("/projectadd")
    public String addProject(Model model, @RequestParam String code) {

        if (code == null || code.trim().toUpperCase().equals("")) {
            return "index";
        }
        code = code.trim().toUpperCase();

        Project newProject = projectService.findByName(code).orElse(new Project(code));
        newProject = projectService.save(newProject);
        model.addAttribute("projectList", List.of(newProject));
        return "index";
    }

// ***************

    @GetMapping("/project/{id}")
    public String listProject_POST(Model model, @PathVariable Long id) {
        try {
            if (id == null) throw new Exception("invalid Id value.");

            Optional<Project> reply = projectService.findById(id);
            if (reply.isEmpty()) return "index";
            Project project = reply.get();

            String code = project.getName();
            model.addAttribute("code", code);
            model.addAttribute("id", project.getId());
            model.addAttribute("PdfTypes", PdfType.values());

            for (PdfType type : PdfType.values()) {
                model.addAttribute(type.name(), project.getPdfs().get(type));
            }


            Map<PdfType, String> validFiles = new HashMap<>();
            Map<PdfType, PDFName> pdfs = project.getPdfs();
            for (PdfType type : PdfType.values()) {
                if ( pdfs.get( type )!= null ) validFiles.put( type, pdfs.get(type).getSerialNumber().toString() );
            }
            model.addAttribute("validFiles", validFiles );
            return "project";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }



    @PostMapping("/pdf/add")
    public String addProject(Model model, FilesForm form) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Long id = form.getId();
        if (id == null) return "index";

        Project project = projectService.findById(id).get();
        Map<PdfType, PDFName> pdfs = project.getPdfs();

        for (PdfType key : PdfType.values()) {
            Method getMethod = form.getClass().getMethod("get" + key.name());
            MultipartFile file = (MultipartFile) getMethod.invoke(form);
            if (file.isEmpty()) continue;
            if (!file.getOriginalFilename().endsWith(".pdf")) continue;

            String oldName = copyFileTORepo(id, key, file, model);
            if ( oldName != null ) {
                PDFName pdfName = new PDFName();
                        pdfName.setOldFileName( file.getOriginalFilename() );
                        pdfName.setSerialNumber( 0L );
                pdfName = pdfNameService.save( pdfName );
                pdfs.put( key , pdfName );
            }
        projectService.save( project );
        }
        return listProject_POST( model, id );
    }






    @GetMapping(value = "/projectmont/{id}", produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource projectMont_GET(Model model, @PathVariable Long id) throws IOException {
        if (id == null) return null; //"project_list";

        Optional<Project> reply = projectService.findById(id);
        System.out.println(reply);
        if (reply.isEmpty()) return null;//"project_list";
        Project project = reply.get();

        String code = project.getName();
        model.addAttribute("code", code);

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

        return new FileSystemResource(new File(folder + "tmp_stamp.pdf"));

        // return "pdf_add";
    }


    // **** TECH


    public String getFileNameById(Long id, PdfType type) {
        String prefix = "0" + (Long) (id / 10);
        int name = 1 + type.ordinal();

        String out = folder + "/" + prefix;
        File f = new File(out);
        if (!f.exists()) f.mkdir();

        return out + "/" + id + "" + name + ".pdf";
    }


    String copyFileTORepo(Long id, PdfType key, MultipartFile file, Model model) {
        String target = getFileNameById(id, key);

        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(  target );) {
            IOUtils.copy(in, out);
        } catch (Exception ex) {
            model.addAttribute("error", model.getAttribute("error") + "\n" + ex);  return null;
        }

        String su =  model.getAttribute( "success" ) + "\nloaded file " + file.getOriginalFilename() + " / " + file.getName();
        model.addAttribute("success", su );
        return  file.getOriginalFilename() ;
    }
}




