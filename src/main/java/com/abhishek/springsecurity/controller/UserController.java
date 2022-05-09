package com.abhishek.springsecurity.controller;

import com.abhishek.springsecurity.dto.ServiceResponse;
import com.abhishek.springsecurity.dto.StudentResponse;
import com.abhishek.springsecurity.dto.UserDetails;
import com.abhishek.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<StudentResponse<List<UserDetails>>> getAllUser() {

        log.info("api = /user, method = GET, result = IN_PROGRESS");

        ServiceResponse<List<UserDetails>> serviceResponse = userService.getAllUser();

        log.info("api = /user, method = GET, result = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).body(new StudentResponse<>(serviceResponse.getData()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse<UserDetails>> getUser(@PathVariable("id") int id) {
        log.info("api = /user/{id}, method = GET, result = IN_PROGRESS");

        ServiceResponse<UserDetails> serviceResponse = userService.getUser(id);

        log.info("api = /user/{id}, method = GET, result = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).body(new StudentResponse<>(serviceResponse.getData()));
    }

    @PostMapping
    public ResponseEntity<StudentResponse<Void>> addUser(@RequestBody UserDetails userDetails) {
        log.info("api = /user, method = POST, result = IN_PROGRESS");

        ServiceResponse<Void> serviceResponse = userService.addUser(userDetails);

        log.info("api = /user, method = POST, result = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @PutMapping()
    public ResponseEntity<StudentResponse<Void>> updateUser(@RequestBody UserDetails userDetails) {
        log.info("api = /user, method = PUT, result = IN_PROGRESS");

        ServiceResponse<Void> serviceResponse = userService.updateUser(userDetails);

        log.info("api = /user, method = PUT, result = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse<Void>> deleteUser(@PathVariable("id") int id) {
        log.info("api = /user/{id}, method = DELETE, result = IN_PROGRESS");

        ServiceResponse<Void> serviceResponse = userService.deleteUser(id);

        log.info("api = /user/{id}, method = DELETE, result = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

}