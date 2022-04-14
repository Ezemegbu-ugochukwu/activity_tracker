package com.groupb.week8todoapp.services.impl;

import com.groupb.week8todoapp.dto.LoginDto;
import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.repository.TaskRepository;
import com.groupb.week8todoapp.repository.UserRepository;
import com.groupb.week8todoapp.services.TaskServices;
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

    @Autowired
    TaskRepository tr;

    @Test
    void pro(){
        LoginDto ld = new LoginDto();
        ld.setEmail("lawal@gmail.com");

        User user = ur.findByEmail(ld.getEmail()).get();
    }

    @Test
    void toPesistTAskToAuser(){
        User user = ur.findById(1).get();
        Task t1 = tr.findById(1).get();
        Task t2 = tr.findById(2).get();

        user.getTasks().add(t1);
        user.getTasks().add(t2);
        ur.save(user);






    }


}