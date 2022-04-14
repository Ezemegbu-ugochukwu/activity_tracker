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
import java.util.Optional;

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
    public boolean createTask(TaskDto taskDto, int id) {
        User user = userImp.findById(id);

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setEndDate(taskDto.getEndDate());
        task.setStatus("PENDING");

//        taskRepo.save(task);
        user.getTasks().add(task);

        userRepo.save(user);


        return true;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public boolean editTask(EditTaskDto editTaskDto) {

        Task task = findTaskById(editTaskDto.getId());
        task.setTitle(editTaskDto.getTitle());
        task.setDescription(editTaskDto.getDescription());

        taskRepo.save(task);
        return true;
    }

    @Override
    public List<Task> findAllUserTask(Integer id) {
        User user = userImp.findById(id);
        return user.getTasks();
    }

    public Task findTaskById(int id){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if(optionalTask.isPresent()){
            return optionalTask.get();
        }
        return null;
    }

    @Override
    public void updateDto(int id, TaskDto taskDto) {
        Task task = findTaskById(id);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());

        taskRepo.save(task);
    }
}
