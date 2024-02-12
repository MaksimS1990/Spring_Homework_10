package com.example.ListTasks.repository;

import com.example.ListTasks.model.TaskStatus;
import com.example.ListTasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByStatus(TaskStatus taskStatus);

    @Modifying                                                                   // аннотация обновления
    @Query("update Task u set u.titleTask = ?1, u.status = ?2 where u.id = ?3")  // аннотация используется для определения пользовательских запросов в Spring Data JPA
    void updateTaskById(String titleTask, TaskStatus status, Long id);
}