package com.groupb.week8todoapp.services;

import com.groupb.week8todoapp.dto.EditTaskDto;
import com.groupb.week8todoapp.dto.TaskDto;
import com.groupb.week8todoapp.model.Task;

import java.util.List;

public interface TaskServices {


    boolean createTask(TaskDto taskDto, int id);
    List<Task> findAll();
    boolean editTask(EditTaskDto editTaskDto);

    List<Task> findAllUserTask(Integer id);

    void updateDto(int id, TaskDto taskDto);

    List<Task> viewAllInProgressTask(Integer id);
    void  moveFromInProgressToPendingTask(Integer userId, Integer taskId);
    void  moveFromInProgressToDone(Integer userId, Integer taskId);
    List<Task> getAllPendingTasks(int id);
    List<Task> getAllDoneTasks(int id);
    Task getSingleTask(int id);

    void deleteTask(Integer userId, Integer taskId);
}
