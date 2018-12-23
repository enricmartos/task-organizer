package org.emartos.services;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.entities.User;
import org.emartos.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // READ
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> optTask = taskRepository.findById(id);
        Task task = optTask.get();
        return task;
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }


    // CREATE
    public void createOne(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    //DELETE
    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }




}
