package hu.workflow.controller;

import hu.workflow.model.Task;
import hu.workflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // Mostrar todas las tareas en una vista
    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/tasks"; // Devuelve la vista ubicada en "templates/tasks/tasks.html"
    }

    // Mostrar formulario para crear una nueva tarea
    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task()); // Crea un objeto vacío para el formulario
        return "tasks/form"; // Asegúrate de tener "form.html" en la carpeta "templates/tasks"
    }


    // Procesar la creación de una nueva tarea
    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si hay errores, se envía de vuelta al formulario con los mensajes de error
            return "tasks/form";
        }
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    // Mostrar formulario para editar una tarea existente
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("task", task);
        return "tasks/form"; // Devuelve "templates/tasks/form.html"
    }


    // Procesar la actualización de una tarea existente
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute("task") Task updatedTask, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si hay errores, se regresa al formulario con los mensajes
            model.addAttribute("task", updatedTask);
            return "tasks/form";
        }
        Task existingTask = taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setCompleted(updatedTask.isCompleted());
        taskService.saveTask(existingTask);
        return "redirect:/tasks";
    }

    // Eliminar una tarea por ID
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks"; // Redirige a la lista de tareas
    }
}
