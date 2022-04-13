package com.groupb.week8todoapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@ToString
@Setter
public class TaskDto {

    private String title;
    private String description;
    private String endDate;
}
