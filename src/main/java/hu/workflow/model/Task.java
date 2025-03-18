package hu.workflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be in the present or future")
    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private boolean completed;
}
