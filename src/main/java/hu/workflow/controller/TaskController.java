package hu.workflow.controller;

import hu.workflow.model.Task;
import hu.workflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si hay errores de validación, regresa al formulario
            return "task-form";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task Not Foud"));
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si hay errores de validación, regresa al formulario
            return "task-form";
        }
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
