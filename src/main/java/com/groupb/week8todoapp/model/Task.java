package com.groupb.week8todoapp.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private String name;
    @Column(nullable = false)
    private String description;
    private String status;
    @CreationTimestamp
    private LocalDateTime startDate;
    private Timestamp endDate;
    @CreationTimestamp
    private LocalDateTime modified;
    private Boolean done = false;
}
