package com.abhishek.springsecurity.util;

import com.abhishek.springsecurity.dto.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
public class UserUtil {
    private final ServletContext servletContext;

    public UserUtil(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public User getLoginUser(){
        return (User) servletContext.getAttribute("user");
    }
}
