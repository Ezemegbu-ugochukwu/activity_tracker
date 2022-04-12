package com.groupb.week8todoapp.services.impl;

import com.groupb.week8todoapp.dto.LoginDto;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.repository.UserRepository;
import com.groupb.week8todoapp.services.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserServiceImplTest {

    @Autowired
    UserRepository ur;

    @Test
    void pro(){
        LoginDto ld = new LoginDto();
        ld.setEmail("lawal@gmail.com");

        User user = ur.findByEmail(ld.getEmail()).get();
    }

}