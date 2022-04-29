package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.LoginService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.LoginForm;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping( "/log/new")
    public String create(@Valid LoginForm loginform, BindingResult result, HttpServletRequest request, Model model)
    {
        Employee loginMember = loginService.findOne(loginform.getLog_id(),loginform.getPassword()).get(0);
        if (result.hasErrors()) { // bindingResult: 에러가 나면 다시 로그인 창으로 돌려준다.
            return "home";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="BASIC"){
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LoginMember, loginMember);
            return "redirect:/log/employee";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="CEO"){
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LoginMember, loginMember);
            return "redirect:/log/ceo";
        }

        else if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="ADMIN"){
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LoginMember, loginMember);
            //log.info("애드민 세션 굿굿");
            log.info(session.getId());
            model.addAttribute("member", loginMember);
            return "redirect:/log/admin";
        }
        else{
            throw new NoIdException("아이디 또는 비밀번호가 틀렸습니다.");
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            log.info("제발 가라");//세션 날림
        }

        return "redirect:/";
    }



    // 사원 전용 마이페이지
    @GetMapping("/log/employee")
    public String goEmployeePage(){
        return "/log/employee";
    }
    // admin 전용 마이페이지
    @GetMapping("/log/admin")
    public String goAdminPage(){
        return "/log/admin";
    }
    // ceo 전용 마이페이지
    @GetMapping("/log/ceo")
    public String goCEOPage(){
        return "/log/ceo";
    }



}
