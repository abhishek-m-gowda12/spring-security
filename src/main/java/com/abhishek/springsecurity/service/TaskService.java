package com.abhishek.springsecurity.service;

import com.abhishek.springsecurity.dto.ServiceResponse;
import com.abhishek.springsecurity.dto.Task;

import java.util.List;

public interface TaskService {

    ServiceResponse<List<Task>> getAllTask();

    ServiceResponse<List<Task>> getTask(int id);

    ServiceResponse<Void> addTask(Task task);

    ServiceResponse<Void> updateTask(Task task);

    ServiceResponse<Void> deleteTask(int id);

    ServiceResponse<Void> addManyTask(List<Task> tasks);

    ServiceResponse<Void> updateManyTask(List<Task> task);

    ServiceResponse<Void> deleteManyTask(List<Integer> ids);

}

