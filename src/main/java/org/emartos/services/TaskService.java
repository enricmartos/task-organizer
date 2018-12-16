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

    public void addTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    public List<Task> findUserTask(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> optTask = taskRepository.findById(id);
        Task task = optTask.get();
        return task;
    }
}
