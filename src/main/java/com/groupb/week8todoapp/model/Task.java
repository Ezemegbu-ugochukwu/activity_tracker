package com.groupb.week8todoapp.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

@ToString
@Entity
@Data
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private String endDate;
    @CreationTimestamp
    private LocalDateTime modified;
    private Boolean done = false;
}
