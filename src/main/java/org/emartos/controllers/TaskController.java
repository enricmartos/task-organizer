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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("task")
public class TaskController {

    private static final String INDEX_TASK_PAGE = "views/task/index";
    private static final String NEW_TASK_PAGE = "views/task/new";
    private static final String REDIRECT_TASK_PAGE = "redirect:/task";
    private static final String VIEW_TASK_PAGE = "views/task/view";
    private static final String EDIT_TASK_PAGE = "views/task/edit";

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return INDEX_TASK_PAGE;
    }

    @GetMapping("new")
//    public String showTaskForm(String email, Model model, HttpSession session) {
    public String showTaskForm(Model model) {
        //email of the user that with the task assigned
//        session.setAttribute("email", email);
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());

        return NEW_TASK_PAGE;
    }

    @PostMapping("new")
    public String processTaskForm(@ModelAttribute @Valid Task task, Errors errors, Model model,
                                  @RequestParam Long projectId, @RequestParam Long userId) {
        if (errors.hasErrors()) {
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("users", userService.findAll());
            return NEW_TASK_PAGE;
        }

        task.setProject(projectService.findById(projectId));
        task.setUser(userService.findById(userId));

        taskService.saveOne(task);

        return REDIRECT_TASK_PAGE;

    }

    @RequestMapping(value="/view/{taskId}", method = RequestMethod.GET)
    public String viewTask(Model model, @PathVariable Long taskId) {

        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);

        return VIEW_TASK_PAGE;
    }

    @RequestMapping(value="/delete/{taskId}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteOne(taskId);

        return REDIRECT_TASK_PAGE;
    }

    @GetMapping(value="/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());

        return EDIT_TASK_PAGE;
    }

    @PostMapping(value="/{id}/edit")
    public String editTask(@PathVariable("id") Long id, @ModelAttribute @Valid Task task, Errors errors,
                           Model model, @RequestParam Long projectId, @RequestParam Long userId) {
        //set the values to related entities of binding object task
        task.setProject(projectService.findById(projectId));
        task.setUser(userService.findById(userId));

        if (errors.hasErrors()) {
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("users", userService.findAll());
            return EDIT_TASK_PAGE;
        }

        taskService.saveOne(task);

        return REDIRECT_TASK_PAGE;
    }


}
