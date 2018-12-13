package org.emartos.controllers;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.services.ProjectService;
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

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public String showProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "views/project/index";
    }

    @GetMapping("/project/add")
    public String projectForm(Model model) {
        //email of the user that with the task assigned
        model.addAttribute("project", new Project());
        return "views/project/add";
    }

    @PostMapping("/addProject")
    public String addProject(@Valid Project project, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "views/project/add";
        }
        projectService.createOne(project);

        return "redirect:/projects";

    }
}
