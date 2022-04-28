package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.LoginService;
import com.DBproject.DBproject.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping( "/log/new")
    public String create(@Valid LoginForm loginform, BindingResult result)
    {
        if (result.hasErrors()) { // bindingResult: 에러가 나면 다시 로그인 창으로 돌려준다.
            return "home";
        }
        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="BASIC"){
            return "redirect:/log/employee";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="CEO"){
            return "redirect:/log/ceo";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="ADMIN"){
            return "redirect:/log/admin";
        }
        else{
            throw new NoIdException("아이디 또는 비밀번호가 틀렸습니다.");
        }
    }


    // 로그인 후 뷰를 내려주고 api 명세를 위한 매핑
    @GetMapping("/log/employee")
    public String goEmployeePage(){
        return "/log/employee";
    }
    // 로그인 후 뷰를 내려주고 api 명세를 위한 매핑
    @GetMapping("/log/admin")
    public String goAdminPage(){
        return "/log/admin";
    }
    // 로그인 후 뷰를 내려주고 api 명세를 위한 매핑
    @GetMapping("/log/ceo")
    public String goCEOPage(){
        return "/log/ceo";
    }



}
