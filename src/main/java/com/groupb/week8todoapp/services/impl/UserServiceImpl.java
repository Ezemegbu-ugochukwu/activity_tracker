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

    private final UserRepository userRepo;

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
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else return null;
    }


    public User findById(Integer id){
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else return null;
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = findByEmail(loginDto.getEmail());
        if(user == null) return null;
        if(user.getPassword().equals(loginDto.getPassword())) return user;
        else return null;
    }
}
