package com.abhishek.springsecurity.service;

import com.abhishek.springsecurity.dto.ServiceResponse;
import com.abhishek.springsecurity.dto.UserDetails;

import java.util.List;

public interface UserService {

    ServiceResponse<List<UserDetails>> getAllUser();

    ServiceResponse<UserDetails> getUser(int id);

    ServiceResponse<Void> addUser(UserDetails userDetails);

    ServiceResponse<Void> updateUser(UserDetails userDetails);

    ServiceResponse<Void> deleteUser(int id);
}

