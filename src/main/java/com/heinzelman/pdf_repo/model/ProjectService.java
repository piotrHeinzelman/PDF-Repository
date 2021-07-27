package com.heinzelman.pdf_repo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements ProjectRepo{

    @Autowired
    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public List<Project> findByNameContains(String s) {
        return projectRepo.findByNameContains( s );
    }

    @Override
    public Optional<Project> findByName( String name ) {
        return projectRepo.findByName( name.trim().toUpperCase() );
    }

    @Override
    public <S extends Project> S save(S s) {
        return projectRepo.save( s );
    }


    @Override
    public Optional<Project> findById(Long aLong) {
        return projectRepo.findById( aLong );
    }

    @Override
    public Iterable<Project> findAll() {
        return projectRepo.findAll();
    }


    @Override
    public long count() {
        return projectRepo.count();
    }

    //   * * * * * *   F A K E   * * * * * *

    @Override public <S extends Project> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }
    @Override public boolean existsById(Long aLong) {
        return false;
    }
    @Override public Iterable<Project> findAllById(Iterable<Long> iterable) {
        return null;
    }
    @Override public void deleteById(Long aLong) { }
    @Override public void delete(Project project) { }
    @Override public void deleteAllById(Iterable<? extends Long> iterable) { }
    @Override public void deleteAll(Iterable<? extends Project> iterable) { }
    @Override public void deleteAll() {}


}
