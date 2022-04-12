package com.groupb.week8todoapp.services.impl;


import com.groupb.week8todoapp.dto.LoginDto;
import com.groupb.week8todoapp.dto.UserDto;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.repository.UserRepository;
import com.groupb.week8todoapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public boolean createUser(UserDto userDto) {
        if(findByEmail(userDto.getEmail()) == null){

            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepo.save(user);

            return true;
        }


        return false;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = findByEmail(loginDto.getEmail());
        if(user.getPassword().equals(loginDto.getPassword())) return user;
        else return null;
    }
}
