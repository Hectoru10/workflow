package hu.workflow.repository;

import hu.workflow.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    /*Page<Task> findAll(Pageable pageable);*/
}
