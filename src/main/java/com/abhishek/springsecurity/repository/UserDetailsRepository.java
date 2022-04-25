package com.abhishek.springsecurity.repository;

import com.abhishek.springsecurity.entity.UserDetailsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDetailsRepository extends CrudRepository<UserDetailsEntity, Integer> {

    Optional<UserDetailsEntity> findByName(String username);

}




