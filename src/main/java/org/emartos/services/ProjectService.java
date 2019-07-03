package org.emartos.services;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // READ
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<Project>();

        for (Project project: projectRepository.findAll()) {
            projects.add(project);
        }
        return projects;
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).get();
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    // CREATE
    public void saveOne(Project project) {
        projectRepository.save(project);
    }
}
