package com.heinzelman.pdf_repo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PDFNameService implements PDFNameRepo {

    @Autowired
    private final PDFNameRepo pdfNameRepo;

    public PDFNameService(PDFNameRepo pdfNameRepo) { this.pdfNameRepo = pdfNameRepo; }

    @Override
    public <S extends PDFName> S save(S s) {
        return pdfNameRepo.save( s );
    }

    @Override
    public Optional<PDFName> findById(Long aLong) {
        return pdfNameRepo.findById( aLong );
    }

    @Override
    public Iterable<PDFName> findAll() {
        return pdfNameRepo.findAll();
    }

    @Override
    public long count() {
        return pdfNameRepo.count();
    }

    //   * * * * * *   F A K E   * * * * * *


    @Override public <S extends PDFName> Iterable<S> saveAll(Iterable<S> iterable) { return null; }
    @Override public boolean existsById(Long aLong) { return false; }
    @Override public Iterable<PDFName> findAllById(Iterable<Long> iterable) { return null; }
    @Override public void deleteById(Long aLong) { }
    @Override public void delete(PDFName pdfName) { }
    @Override public void deleteAllById(Iterable<? extends Long> iterable) { }
    @Override public void deleteAll(Iterable<? extends PDFName> iterable) { }
    @Override public void deleteAll() { }


}
