package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.Service.LoginService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.LoginForm;
import com.DBproject.DBproject.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final EmployeeService employeeService;

    @PostMapping( "/home")
    public String create(@Valid LoginForm loginform, BindingResult result, HttpServletRequest request, Model model)
    {
        if (result.hasErrors()) { // bindingResult: 에러가 나면 다시 로그인 창으로 돌려준다.
            return "home";
        }
        try {
            StopWatch stopwatch = new StopWatch();
            stopwatch.start();
            Employee loginMember = loginService.findOne(loginform.getLog_id(),loginform.getPassword()).get(0);
            stopwatch.stop();
            log.info(stopwatch.prettyPrint()+"초가 걸렸습니다");


        if(loginService.login(loginform.getLog_id(),loginform.getPassword())=="BASIC"){
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
            log.info(session.getId());

            return "redirect:/log/adminPage";
        }


        }catch (NullPointerException e){
            model.addAttribute("error", new NullPointerException("아이디 또는 비밀번호가 틀렸습니다."));
            return "home";
        }
        return  null;
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

}
