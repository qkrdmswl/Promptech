package com.DBproject.DBproject.controller.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class EditForm {
    private String employee_name; // 사원 이름
    private String employee_school;  // 사원 최종학력
    @NotEmpty(message = "비밀번호는 공백으로 두면 안됩니다. ")
    private String password;   // 사원 패스워드 변경
    private String phoneNum;   // 사원 연락처: 핸드폰 번호
    private String email;      // 사원 연락처: 이메일
    private String employee_skill; // 사원 기술 스택
    private String employee_career; // 사원 경력
}

