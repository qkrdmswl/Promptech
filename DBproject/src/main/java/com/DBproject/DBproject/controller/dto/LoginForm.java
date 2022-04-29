package com.DBproject.DBproject.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
//@Data
public class LoginForm {


    @NotEmpty(message = "아이디 입력은 필수입니다.")
    private String log_id;
    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    private String password;

}
