package com.DBproject.DBproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class ProjectEditForm {
    private String project_id;
    private String project_name;
    private String ordering_company;
    private String start_date;
    private String end_date;
    private String cost;
}
