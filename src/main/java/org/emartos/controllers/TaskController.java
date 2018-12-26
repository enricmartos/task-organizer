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
import java.util.Optional;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "views/task/index";
    }

    @GetMapping("add")
    public String showTaskForm(String email, Model model, HttpSession session) {
        //email of the user that with the task assigned
        session.setAttribute("email", email);
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());

        return "views/task/add";
    }

    @PostMapping("add")
    public String processTaskForm(@Valid Task task, BindingResult bindingResult, @RequestParam Long projectId,
                          @RequestParam Long userId) {
        if (bindingResult.hasErrors()) {
            return "views/task/add";
        }

        task.setProject(projectService.findById(projectId));
        task.setUser(userService.findById(userId));

        taskService.saveOne(task);

        //String email = (String)session.getAttribute("email");
        //taskService.createOne(task, userService.findByEmail(email));

        return "redirect:/task";

    }

    @RequestMapping(value="/view/{taskId}", method = RequestMethod.GET)
    public String viewTask(Model model, @PathVariable Long taskId) {

        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);

        return "views/task/view";
    }

    @RequestMapping(value="/delete/{taskId}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteOne(taskId);

        return "redirect:/task";
    }

    @RequestMapping(value="/{taskId}", method = RequestMethod.GET)
    public String updateTask(@PathVariable Long taskId, Model model) {
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("mode", "edit");

        return "views/task/add";
    }


}
