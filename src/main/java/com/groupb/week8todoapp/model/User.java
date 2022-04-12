package com.groupb.week8todoapp.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Size(min = 3, max = 15)
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "tasks", referencedColumnName = "id")
    private List<Task> tasks = new ArrayList<>();
}
