package com.abhishek.springsecurity.controller;

import com.abhishek.springsecurity.dto.ServiceResponse;
import com.abhishek.springsecurity.dto.StudentResponse;
import com.abhishek.springsecurity.dto.Task;
import com.abhishek.springsecurity.dto.User;
import com.abhishek.springsecurity.service.TaskService;
import com.abhishek.springsecurity.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserUtil userUtil;

    public TaskController(
            TaskService taskService,
            UserUtil userUtil) {
        this.taskService = taskService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public ResponseEntity<StudentResponse<List<Task>>> getAllTask() {
        User user = userUtil.getLoginUser();
        log.info("api = /task, method = GET, result = IN_PROGRESS, userId = {}", user.getUserId());

        ServiceResponse<List<Task>> serviceResponse = taskService.getAllTask();

        log.info("api = /task, method = GET, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).body(new StudentResponse<>(serviceResponse.getData()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse<List<Task>>> getTask(@PathVariable("id") int id) {
        User user = userUtil.getLoginUser();
        log.info("api = /task{id}, method = GET, result = IN_Progress, userId = {}", user.getUserId());

        ServiceResponse<List<Task>> serviceResponse = taskService.getTask(id);

        log.info("api = /task{id}, method = GET, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).body(new StudentResponse<>(serviceResponse.getData()));
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody Task task) {
        User user = userUtil.getLoginUser();
        log.info("api = /task, method = POST, result = IN_Progress, userId = {}", user.getUserId());

        ServiceResponse<Void> serviceResponse = taskService.addTask(task);

        log.info("api = /task, method = POST, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }


    @PutMapping()
    public ResponseEntity<Void> updateTask(@RequestBody Task task) {
        User user = userUtil.getLoginUser();
        log.info("api = /task, method = PUT, result = IN_Progress, userId = {}", user.getUserId());

        ServiceResponse<?> serviceResponse = taskService.updateTask(task);

        log.info("api = /task,method = PUT, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id) {
        User user = userUtil.getLoginUser();
        log.info("api = /task{id}, method = DELETE, result = IN_Progress, userId = {}", user.getUserId());

        ServiceResponse<?> serviceResponse = taskService.deleteTask(id);
        log.info("api = /task{id},method = DELETE, result = SUCCESS, userId = {}", user.getUserId());

        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @PostMapping("/addMany")
    public ResponseEntity<Void> addManyTask(@RequestBody List<Task> tasks) {
        User user = userUtil.getLoginUser();
        log.info("api = /task/addMany, method = POST, result = IN_PROGRESS, userId = {}", user.getUserId());

        ServiceResponse<?> serviceResponse = taskService.addManyTask(tasks);

        log.info("api = /task/addMany, method = POST, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @PutMapping("/updateMany")
    public ResponseEntity<Void> updateManyTask(@RequestBody List<Task> task) {
        User user = userUtil.getLoginUser();
        log.info("api =  /task/updateMany, method = PUT, result = IN_Progress, userId = {}", user.getUserId());

        ServiceResponse<Void> serviceResponse = taskService.updateManyTask(task);

        log.info("api =  /task/updateMany,method = PUT, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @DeleteMapping("/deleteMany")
    public ResponseEntity<Void> deleteManyTask(@RequestParam("ids") List<Integer> ids) {
        User user = userUtil.getLoginUser();
        log.info("api =  /task/deleteMany, method = DELETE, result = IN_Progress, userId = {}", user.getUserId());

        ServiceResponse<?> serviceResponse = taskService.deleteManyTask(ids);

        log.info("api =  /task/deleteMany,method = DELETE, result = SUCCESS, userId = {}", user.getUserId());
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

}