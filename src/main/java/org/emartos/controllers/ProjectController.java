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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("project")
public class ProjectController {

    private static final String INDEX_PROJECT_PAGE = "views/project/index";
    private static final String NEW_PROJECT_PAGE = "views/project/new";
    private static final String REDIRECT_PROJECT_PAGE = "redirect:/project";
    private static final String INDEX_TASK_PAGE = "views/task/index";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public String showProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return INDEX_PROJECT_PAGE;
    }

    @GetMapping("new")
    public String showProjectForm(Model model) {

        model.addAttribute("project", new Project());
        return NEW_PROJECT_PAGE;
    }

    @PostMapping("new")
    public String processProjectForm(@Valid Project project, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return NEW_PROJECT_PAGE;
        }
        projectService.saveOne(project);
        return REDIRECT_PROJECT_PAGE ;
    }

    @RequestMapping(value="view/{id}", method = RequestMethod.GET)
    public String showProject(Model model, @PathVariable Long id) {

        Project project = projectService.findById(id);
        List<Task> tasks = project.getTasks();
        model.addAttribute("tasks", tasks);

        return INDEX_TASK_PAGE;
    }
}
