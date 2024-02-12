package com.example.ListTasks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data                                                           // автогенерация геттеров, сеттеров, toString
@Entity                                                         // определяет сущность проекта
// @Table(name = "Tasks")                                          // явно указываем на название таблицы в БД
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)      //авто генерация ID-шника
    private Long id;
    @Column(nullable = false, length = 500)                  // поле обязательно должно быть заполнено
    private String titleTask;
    @Column(nullable = false)                                // аннотация позволяет настраивать способ сохранения и загрузки enum-значений из базы данных при использовании JPA
    private TaskStatus status;
    @Column(name = "dateTimeCreateTask", nullable = false)
    private LocalDateTime dateTimeCreateTask;


    public Task() {
    }

    public Task(Task task) {
        if (task.getTitleTask() != null)
            this.titleTask = task.getTitleTask();
        if (task.status == null)
            this.status = getStatus();
        if (task.dateTimeCreateTask == null)
            this.dateTimeCreateTask = LocalDateTime.now();
        else this.dateTimeCreateTask = task.dateTimeCreateTask;
    }

    public Task(String titleTask, String status) {
        this.titleTask = titleTask;
        this.status = TaskStatus.valueOf("IN_PROGRESS");
    }
}