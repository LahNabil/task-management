package net.lahlalia.taskmanagement.services;

import lombok.RequiredArgsConstructor;
import net.lahlalia.taskmanagement.dtos.TaskDto;
import net.lahlalia.taskmanagement.entities.Task;
import net.lahlalia.taskmanagement.mappers.TaskMapper;
import net.lahlalia.taskmanagement.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toModel(savedTask);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toModel)
                .toList();
    }

    @Override
    public Optional<TaskDto> getTaskDtoById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        return task.map(taskMapper::toModel);
    }

    @Override
    public TaskDto updateTaskDto(Long id, TaskDto taskDto) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if(existingTaskOpt.isPresent()){
            Task existingTask = existingTaskOpt.get();
            existingTask.setTitle(taskDto.getTitle());
            existingTask.setDescription(taskDto.getDescription());
            Task updatedTask = taskRepository.save(existingTask);
            return taskMapper.toModel(updatedTask);
        }else {
             throw new RuntimeException("Task not found with id: " + id);
        }

    }

    @Override
    public boolean deleteTask(long id) {
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
