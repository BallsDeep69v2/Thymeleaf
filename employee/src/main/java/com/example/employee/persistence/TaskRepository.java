package com.example.employee.persistence;

import com.example.employee.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
            select task
             from Task task
            where task.assignee.id = :id
            """)
    List<Task> allTasksByEmployee(String id);
}
