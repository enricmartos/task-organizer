package org.emartos.services;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.entities.User;
import org.emartos.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // READ
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<Task>();

        for (Task task: taskRepository.findAll()) {
            tasks.add(task);
        }
        return tasks;
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }


    // CREATE
    public void saveOne(Task task) {
        taskRepository.save(task);
    }

    //DELETE
    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }




}
