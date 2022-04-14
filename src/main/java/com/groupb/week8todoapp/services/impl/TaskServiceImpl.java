package com.groupb.week8todoapp.services.impl;

import com.groupb.week8todoapp.UserNotFoundException;
import com.groupb.week8todoapp.dto.EditTaskDto;
import com.groupb.week8todoapp.dto.TaskDto;
import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.repository.TaskRepository;
import com.groupb.week8todoapp.repository.UserRepository;
import com.groupb.week8todoapp.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Task> viewAllInProgressTask(Integer id) {
        // intialize the resulting array
        // get the user by the id to know if he exist or not
        // get the list of user's task
        // check the list to get all task in progress
        // save these tasksInProgress into a list and return the resulting array
        List<Task> tasksInProgress = new ArrayList<>();
//        Optional<User> optionalUser = userRepo.findById(id);
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Task> userTasks = user.getTasks();

        for (int i = 0; i < userTasks.size(); i++) {
            if (userTasks.get(i).getStatus().equals("IN_PROGRESS")) {
                tasksInProgress.add(userTasks.get(i));
            }
        }
        return tasksInProgress;
    }

    @Override
    public void moveFromInProgressToPendingTask(Integer userId, Integer taskId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        for(Task each: user.getTasks()) {
            if(each.getId() == taskId){
                each.setStatus("PENDING");
                taskRepo.save(each);
            }
        }
    }



    @Override
    public void moveFromInProgressToDone(Integer userId, Integer taskId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        for(Task each: user.getTasks()) {
            if(each.getId() == taskId){
                each.setStatus("DONE");
                each.setDone(true);
                taskRepo.save(each);
            }
        }
    }


    @Override
    public List<Task> getAllPendingTasks(int id) {
        List<Task> tasks = findAllUserTask(id);
        List<Task> pendingTasks = new ArrayList<>();
        for(Task each: tasks){
            if(each.getStatus().equals("PENDING")){
                pendingTasks.add(each);
            }
        }
        return pendingTasks;
    }
    @Override
    public List<Task> getAllDoneTasks(int id) {
        List<Task> tasks = findAllUserTask(id);
        List<Task> doneTasks = new ArrayList<>();
        for(Task each: tasks){
            if(each.getStatus().equals("DONE")){
                doneTasks.add(each);
            }
        }
        return doneTasks;
    }

    @Override
    public Task getSingleTask(int id) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        if(optionalTask.isPresent())return optionalTask.get();
        return null;
    }
}
