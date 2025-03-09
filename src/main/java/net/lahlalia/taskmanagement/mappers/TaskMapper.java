package net.lahlalia.taskmanagement.mappers;


import net.lahlalia.taskmanagement.dtos.TaskDto;
import net.lahlalia.taskmanagement.entities.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {

    private final ModelMapper mapper;

    @Autowired
    public TaskMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public Task toEntity(TaskDto dto){
        return mapper.map(dto, Task.class);
    }
    public TaskDto toModel(Task task){
        return mapper.map(task, TaskDto.class);
    }

}
