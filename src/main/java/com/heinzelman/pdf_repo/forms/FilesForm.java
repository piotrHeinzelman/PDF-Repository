package com.heinzelman.pdf_repo.forms;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class FilesForm {

    private String code;
    private MultipartFile pdfA;
    private MultipartFile pdfAL;
    private MultipartFile pdfB;
    private MultipartFile pdfBL;


}
