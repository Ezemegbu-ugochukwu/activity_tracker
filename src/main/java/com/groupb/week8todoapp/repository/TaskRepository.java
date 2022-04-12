package com.groupb.week8todoapp.repository;

import com.groupb.week8todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {


}
