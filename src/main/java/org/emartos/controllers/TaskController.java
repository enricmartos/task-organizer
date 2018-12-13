package org.emartos.controllers;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.services.ProjectService;
import org.emartos.services.TaskService;
import org.emartos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/addTask")
    public String taskForm(String email, Model model, HttpSession session) {
        //email of the user that with the task assigned
        session.setAttribute("email", email);
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        return "views/taskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@Valid Task task, BindingResult bindingResult, @RequestParam Long projectId, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "views/taskForm";
        }
        Project project = projectService.findById(projectId);
        task.setProject(project);
        String email = (String)session.getAttribute("email");
        taskService.addTask(task, userService.findByEmail(email));

        return "redirect:/users";

    }
}
