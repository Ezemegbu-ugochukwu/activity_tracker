package com.groupb.week8todoapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class EditTaskDto {
    private int id;
    private String title;
    private String description;
}
