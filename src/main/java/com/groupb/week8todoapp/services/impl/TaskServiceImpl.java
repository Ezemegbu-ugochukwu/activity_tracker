package com.groupb.week8todoapp.services.impl;

import com.groupb.week8todoapp.dto.EditTaskDto;
import com.groupb.week8todoapp.dto.TaskDto;
import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.repository.TaskRepository;
import com.groupb.week8todoapp.repository.UserRepository;
import com.groupb.week8todoapp.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskServices {


    UserRepository userRepo;
    TaskRepository taskRepo;
    UserServiceImpl userImp;

    @Autowired
    public TaskServiceImpl(UserRepository userRepo, TaskRepository taskRepo, UserServiceImpl userImp){
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.userImp = userImp;
    }

    @Override
    public boolean createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setEndDate(taskDto.getEndDate());
        task.setStatus("PENDING");

        taskRepo.save(task);

        return true;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public boolean editTask(EditTaskDto editTaskDto) {
        return false;
    }

    @Override
    public List<Task> findAllUserTask(Integer id) {
        User user = userImp.findById(id);
        return user.getTasks();
    }
}
