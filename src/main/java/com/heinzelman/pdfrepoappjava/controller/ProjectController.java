package com.heinzelman.pdfrepoappjava.controller;

//import com.heinzelman.pdf_repo.forms.FilesForm;

//@Controller
public class ProjectController {
/*
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

        if (code == null || code.trim().toUpperCase().equals("")) { return "index"; }
        code = code.trim().toUpperCase();

        Project newProject = projectService.findByName(code).orElse(new Project(code));
        newProject = projectService.save(newProject);
        model.addAttribute("projectList", List.of(newProject));
        return "index";
    }



    @GetMapping("/project/{id}")
    public String listProject_POST(Model model, @PathVariable Long id) {
        try {
            if ( id == null ) throw new Exception("invalid Id value.");

            Optional<Project> reply = projectService.findById(id);
            if (reply.isEmpty()) return "index";
            Project project = reply.get();

            String code = project.getName();
            model.addAttribute("code", code);
            model.addAttribute("id", project.getId());
            model.addAttribute("PdfTypes", PdfType.values());

            for ( PdfType type : PdfType.values()) {
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




    @GetMapping(value = "/pdf/get/{id}/{pdfType}/*", produces = {"application/pdf"} )
    @ResponseBody
    public FileSystemResource pdfDownload_GET( Model model, @PathVariable Long id, @PathVariable PdfType pdfType  ) throws IOException {
        if (id == null) throw new IOException ("invalid Id");
         String fileFullName = null;

        Optional<Project> reply = projectService.findById(id);
        if (reply.isEmpty())  throw new IOException("invalid Id");

        Project project = reply.get();
        Map<PdfType, PDFName> pdfs = project.getPdfs();

        fileFullName = getFileNameById( id, pdfType );

        FileSystemResource outSystemResource = new  FileSystemResource( new File( fileFullName ));
        return outSystemResource;
    }



// ***************


    @GetMapping(value = "/pdf/getEN/{id}/{type}/*", produces = {"application/pdf"})
    @ResponseBody
    public FileSystemResource pdfEnDownload_GET( Model model, @PathVariable Long id, @PathVariable String type ) throws IOException {
        if (id == null) throw new IOException ("invalid Id");
        String fileFullName = null;

        Optional<Project> reply = projectService.findById(id);
        if (reply.isEmpty())  throw new IOException("invalid Id");

        Project project = reply.get();
        Map<PdfType, PDFName> pdfs = project.getPdfs();

        switch ( type ){
            case "BW": fileFullName = prepareBW( id , false ); break;
            case "COLOR": fileFullName = prepareColor( id , false );  break;
            case "ARCH": fileFullName = prepareArch( id , false ); break;
            case "TECH": fileFullName = prepareTech( id , false ); break;
            case "CENNIK": fileFullName = prepareCennik( id , false ); break;

            case "EnBW": fileFullName = prepareBW( id , true ); break;
            case "EnCOLOR": fileFullName = prepareColor( id , true );  break;
            case "EnARCH": fileFullName = prepareArch( id , true ); break;
            case "EnTECH": fileFullName = prepareTech( id , true ); break;
            case "EnCENNIK": fileFullName = prepareCennik( id , true ); break;
        }
        return new FileSystemResource( new File( fileFullName ));
    }







    private String  prepareBW( Long id , boolean encrypt ) {
        return  joinArrayOfPDF(   new String[]{  getFileNameById( id , PdfType.A ) , getFileNameById( id , PdfType.T ) , getFileNameById( id , PdfType.C )   } , encrypt );
    }
    private String  prepareColor( Long id , boolean encrypt) {
        return  joinArrayOfPDF(   new String[]{  getFileNameById( id , PdfType.AO ) , getFileNameById( id , PdfType.TO ) , getFileNameById( id , PdfType.CO )   } , encrypt );
    }
    private String  prepareArch( Long id , boolean encrypt) {
        return  joinArrayOfPDF(   new String[]{  getFileNameById( id , PdfType.AO ) , getFileNameById( id , PdfType.A ) } , encrypt );
    }
    private String  prepareTech( Long id , boolean encrypt) {
        return  joinArrayOfPDF(   new String[]{  getFileNameById( id , PdfType.TO ) , getFileNameById( id , PdfType.T ) } , encrypt );
    }
    private String  prepareCennik( Long id , boolean encrypt ) {
        return  joinArrayOfPDF(   new String[]{  getFileNameById( id , PdfType.CO ) , getFileNameById( id , PdfType.C ) } , encrypt );
    }


    public String joinArrayOfPDF(  String[] files , boolean encrypt ) {
        try {
            // prepare output file
            Random gen =  new Random();
            File outputFile = new File(folder + "/join" + gen.nextInt(99) + ".pdf");
            //outputFile.createNewFile();

            JoinPDFs j = new JoinPDFs();
            j.joinArray(  files , outputFile );

            if ( encrypt ) {
                Stamper s = new Stamper();
                s.stamp(  outputFile.getPath().toString() ,  " * napis * " , " strasznieTrudneHasl0" );
                return outputFile.getPath().toString().replace(".pdf","en.pdf");
            }

            return outputFile.getPath().toString();
        } catch( Exception ex ) { System.out.println( ex );return null; }

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


        return new FileSystemResource(new File(folder + "tmp_stamp.pdf"));

    }









    // ***********************************
    // **
    // **            TECH
    // **
    // **
    // ***********************************

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
        String su = model.getAttribute( "success" )==null ? "" : model.getAttribute( "success" ).toString();
               su += "\nloaded file " + file.getOriginalFilename() + " / " + file.getName();
        model.addAttribute("success", su );
        return  file.getOriginalFilename() ;
    }
*/
}

