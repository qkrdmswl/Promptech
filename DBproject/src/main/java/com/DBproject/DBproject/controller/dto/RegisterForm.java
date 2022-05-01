package com.DBproject.DBproject.controller.dto;

import com.DBproject.DBproject.domain.Authority;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterForm {
    @NotEmpty(message = "이름 입력은 필수입니다.")
    private String employee_name;

    @NotEmpty(message = "주민등록 번호 입력은 필수입니다.")
    private String employee_number;
    @NotEmpty(message = "최종학력 입력은 필수입니다.")
    private String employee_school;

    @NotEmpty(message = "아이디 입력은 필수입니다.")
    private String log_id;
    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    private String password;
    @NotEmpty(message = "핸드폰 번호 입력은 필수입니다.")
    private String phoneNum;
    @NotEmpty(message ="E-Mail 입력은 필수입니다.")
    private String email;
}








