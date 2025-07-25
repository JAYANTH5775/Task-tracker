package com.example.task_tracker.dto;

import java.time.LocalDateTime;

public record TaskDTO(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDateTime dueDate
) {}
