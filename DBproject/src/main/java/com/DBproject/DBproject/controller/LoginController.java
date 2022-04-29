package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.Service.LoginService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.LoginForm;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Works_for;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import com.DBproject.DBproject.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final EmployeeService employeeService;

    @PostMapping( "/log/new")
    public String create(@Valid LoginForm loginform, BindingResult result, HttpServletRequest request, Model model)
    {
        if (result.hasErrors()) { // bindingResult: 에러가 나면 다시 로그인 창으로 돌려준다.
            return "home";
        }
        try {


        Employee loginMember = loginService.findOne(loginform.getLog_id(),loginform.getPassword()).get(0);





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

            return "redirect:/log/admin";
        }
        //else{
          //  throw new NoIdException("아이디 또는 비밀번호가 틀렸습니다.");
        //}
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



    // 사원 전용 마이페이지
    @GetMapping("/log/employee")
    public String goEmployeePage(){
        return "/log/employee";
    }

    // admin 전용 마이페이지
    @GetMapping("/log/admin")
    public String goAdminPage(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginMember, Model model){
        model.addAttribute("employee", loginMember);
        List<Works_for> works_fors= employeeService.works_fors(loginMember.getLog_id());
        model.addAttribute("works_for", works_fors);
        return "/log/admin";
    }
    // ceo 전용 마이페이지
    @GetMapping("/log/ceo")
    public String goCEOPage(){
        return "/log/ceo";
    }



}
