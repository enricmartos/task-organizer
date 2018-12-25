package org.emartos.repositories;

import org.emartos.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByName(String name);

}
