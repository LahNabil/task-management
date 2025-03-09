package net.lahlalia.taskmanagement.repositories;

import net.lahlalia.taskmanagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
