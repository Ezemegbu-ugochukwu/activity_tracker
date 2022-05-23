package com.groupb.week8todoapp.repository;

import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
