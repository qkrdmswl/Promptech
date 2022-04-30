package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class EmployeeController {
    // 사원 전용 마이페이지
    private final EmployeeService employeeService;
    @GetMapping("/log/employee")
    public String goEmployeePage(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginMember, Model model){
        model.addAttribute("employee", loginMember);
        List<Works_for> works_fors= employeeService.works_fors(loginMember.getLog_id());
        model.addAttribute("worksFor", works_fors);
        return "/log/employee";
    }
}
