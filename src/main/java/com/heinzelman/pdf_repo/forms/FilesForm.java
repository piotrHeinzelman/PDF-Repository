package com.heinzelman.pdf_repo.forms;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class FilesForm {

    private Long id;
    private MultipartFile A;
    private MultipartFile AO;
    private MultipartFile T;
    private MultipartFile TO;
    private MultipartFile C;
    private MultipartFile CO;

}
