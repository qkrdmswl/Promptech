package com.DBproject.DBproject.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class FindOneProjectForm {
    @NotEmpty(message = "프로젝트 아이디를 입력하세요.")
    private String project_id;
}
