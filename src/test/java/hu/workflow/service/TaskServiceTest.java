package hu.workflow.service;

import hu.workflow.model.Task;
import hu.workflow.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest  {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void testSaveTask() {
        // Dado (configuración)
        Task task = new Task(null, "New Task", "Task Description", LocalDate.now().plusDays(1), false);
        when(taskRepository.save(task)).thenReturn(task);

        // Cuando (acción)
        Task savedTask = taskService.saveTask(task);

        // Entonces (verificación)
        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getTitle());
        assertEquals("Task Description", savedTask.getDescription());
        verify(taskRepository, times(1)).save(task);
    }
}
