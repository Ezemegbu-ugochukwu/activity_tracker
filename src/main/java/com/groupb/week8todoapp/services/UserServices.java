package com.groupb.week8todoapp.services;


import com.groupb.week8todoapp.dto.LoginDto;
import com.groupb.week8todoapp.dto.UserDto;
import com.groupb.week8todoapp.model.User;

public interface UserServices {
    boolean createUser(UserDto userDto);

    User findByEmail(String email);
    User login(LoginDto loginDto);
}
