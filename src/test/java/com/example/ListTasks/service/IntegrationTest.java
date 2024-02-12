package com.example.ListTasks.service;

import com.example.ListTasks.model.Task;
import com.example.ListTasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.ListTasks.model.TaskStatus.IN_PROGRESS;
import static com.example.ListTasks.model.TaskStatus.NOT_STARTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class IntegrationTest {

    @MockBean                                               //создаём Bean
    private TaskRepository taskRepository;

    @Autowired
    public TaskService taskService;

    List<Task> tasks = new ArrayList<>();


        @Test
        public void findAllTest() {

            Task task1 = new Task();
            tasks.add(task1);
            Task task2 = new Task();
            tasks.add(task2);
            given(taskRepository.findAll()).willReturn(tasks);

            List<Task> result = taskService.findAll();

            Assertions.assertNotNull(result);
            Assertions.assertEquals(2, result.size());
            verify(taskRepository).findAll();
            assertEquals(tasks, result);
        }

    }

