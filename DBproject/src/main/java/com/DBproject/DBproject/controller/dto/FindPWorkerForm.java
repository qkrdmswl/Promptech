package com.DBproject.DBproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindPWorkerForm {
    @NotEmpty(message = "사원 아이디를 입력하세요.")
    private int employee_id;
    @NotEmpty(message = "프로젝트 아이디를 입력하세요.")
    private String project_id;
}
