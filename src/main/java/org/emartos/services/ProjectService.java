package org.emartos.services;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        Optional<Project> optProject = projectRepository.findById(id);
        Project project = optProject.get();
        return project;
    }

    public void createOne(Project project) {
        //Encoding the password in a hash
        projectRepository.save(project);
    }


    public List<Task> findTasksById(Long id) {
        Optional<Project> optProject = projectRepository.findById(id);
        Project project = optProject.get();

        return project.getTasks();
    }
}
