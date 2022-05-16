package com.DBproject.DBproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class ProjectForm {
    @NotEmpty(message = "프로젝트 아이디 입력은 필수입니다.")
    private String project_id;

    @NotEmpty(message = "프로젝트 이름 입력은 필수입니다.")
    private String project_name;

    @NotEmpty(message = "고객사 입력은 필수입니다.")
    private String ordering_company;

    @NotEmpty(message = "프로젝트 시작일짜 입력은 필수입니다.")
    private String start_date;
    @NotEmpty(message = "프로젝트 종료일짜 입력은 필수입니다.")
    private String end_date;

    @NotEmpty(message = "프로젝트 예산 입력은 필수입니다.")
    private String cost;

}
