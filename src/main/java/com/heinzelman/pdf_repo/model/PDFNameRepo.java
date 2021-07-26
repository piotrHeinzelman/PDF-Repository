package com.heinzelman.pdf_repo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFNameRepo extends CrudRepository<PDFName, Long> {
}
