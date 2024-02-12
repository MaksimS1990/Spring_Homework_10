package com.example.ListTasks.service;

import com.example.ListTasks.model.Task;
import com.example.ListTasks.model.TaskStatus;
import com.example.ListTasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;
import java.util.*;
;

import static com.example.ListTasks.model.TaskStatus.IN_PROGRESS;
import static com.example.ListTasks.model.TaskStatus.NOT_STARTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock                                                // разрываем соединение
    private TaskRepository taskRepository;

    @InjectMocks                                         // создаем экземпляр, который будем тестировать
    public TaskService taskService;

    List<Task> tasks = new ArrayList<>();

    public Task task1 = new Task();
    public Task task2 = new Task();

    @BeforeEach
    public void Starting() {

        task1.setId(1L);
        task1.setTitleTask("сделать домашнюю работу");
        task1.setStatus(IN_PROGRESS);
        task1.setDateTimeCreateTask(LocalDateTime.now());

        task2.setId(2L);
        task2.setTitleTask("лечь спать в 21:00");
        task2.setStatus(NOT_STARTED);
        task2.setDateTimeCreateTask(LocalDateTime.now());

    }

    @Test
    public void findAllTest() {
        // given
        tasks.add(task1);
        tasks.add(task2);
        given(taskRepository.findAll()).willReturn(tasks);
        // when
        List<Task> result = taskService.findAll();
        // then
        Assertions.assertNotNull(result);
        assertEquals(2, result.size(), "Good");
        verify(taskRepository).findAll();
        assertEquals(tasks, result);
    }

    @Test
    public void addTest() {
        // given
        Task result = new Task();
        given(taskService.add(task1)).willReturn(task1);
        // when
        result = taskRepository.save(task1);
        // then
        assertEquals(task1, result);
    }

    @Test
    public void findTasksByStatus() {
        // given
        tasks.add(task1);
        tasks.add(task2);
        given(taskRepository.findTasksByStatus(task1.getStatus())).willReturn(Collections.singletonList(task1));
        // when
        List<Task> result = taskRepository.findTasksByStatus(task1.getStatus());
        // then
        assertEquals(task1, result.get(0));
    }

    @Test
    public void updateByIdTest() {
        // given
        tasks.add(task1);
        given(taskRepository.findById(1L)).willReturn(Optional.ofNullable(tasks.get(0)));
        given(taskRepository.save(task2)).willReturn(task2);
        // when
        taskRepository.updateTaskById(task1.getTitleTask(), task1.getStatus(), task1.getId());
        // then
        assertEquals(task1, tasks.get(0));
    }
}
