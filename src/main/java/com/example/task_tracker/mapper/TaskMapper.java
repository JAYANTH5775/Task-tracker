package com.example.task_tracker.mapper;

import com.example.task_tracker.model.Task;
import com.example.task_tracker.dto.TaskDTO;



public class TaskMapper {

    public static TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getDueDate()
        );
    }

    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.id());
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setCompleted(dto.completed());
        task.setDueDate(dto.dueDate());
        return task;
    }
}
