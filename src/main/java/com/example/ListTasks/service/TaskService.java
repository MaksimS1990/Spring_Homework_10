package com.example.ListTasks.service;

import com.example.ListTasks.model.Task;
import com.example.ListTasks.model.TaskStatus;
import com.example.ListTasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor                                         //автогенерация конструктора
public class TaskService {

    private final TaskRepository repository;

    public Task add(Task task) {
        return repository.save(task);
    }

    public List<Task> findAll(){
        return repository.findAll();
    }

    public List<Task> findTasksByStatus(TaskStatus taskStatus){
        return repository.findTasksByStatus(taskStatus);
    }

    @Transactional
    public Task updateById(Long id, Task task) {
        repository.updateTaskById(task.getTitleTask(), task.getStatus(), id);
        return repository.findById(id).get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}