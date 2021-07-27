package com.heinzelman.pdf_repo.model;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {

    Optional<Project> findByName(String s);

    List<Project> findByNameContains(String s);
}
