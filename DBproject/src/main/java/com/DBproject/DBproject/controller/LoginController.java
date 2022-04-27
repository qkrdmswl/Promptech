package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.LoginService;
import com.DBproject.DBproject.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;


    @PostMapping(value = "/log/new")
    public String create(@Valid LoginForm loginform, BindingResult result)

    {


        if (result.hasErrors()) { // bindingResult: 에러가 나면 다시 로그인 창으로 돌려준다.
            return "home";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="BASIC"){
            return "log/employee";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="CEO"){
            return "log/ceo";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="ADMIN"){
            return "log/admin";
        }
        else{
            throw new NoIdException("아이디 또는 비밀번호가 틀렸습니다.");
        }


    }

}
