package com.example.task_tracker.service;

import com.example.task_tracker.model.Task;
import com.example.task_tracker.repository.TaskRepository;
import com.example.task_tracker.dto.TaskDTO;
import com.example.task_tracker.mapper.TaskMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository taskRepo;

    // ✅ Constructor injection
    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return TaskMapper.toDto(task);
    }

    public TaskDTO createTask(TaskDTO dto) {
        Task task = TaskMapper.toEntity(dto);
        return TaskMapper.toDto(taskRepo.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setCompleted(dto.completed());
        task.setDueDate(dto.dueDate());

        return TaskMapper.toDto(taskRepo.save(task));
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
