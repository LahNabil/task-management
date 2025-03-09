package net.lahlalia.taskmanagement.services;

import net.lahlalia.taskmanagement.dtos.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);
    List<TaskDto> getAllTasks();
    Optional<TaskDto> getTaskDtoById(Long id);
    TaskDto updateTaskDto(Long id,TaskDto taskDto);
    boolean deleteTask(long id);
}
