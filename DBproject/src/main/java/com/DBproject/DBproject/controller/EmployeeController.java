package com.DBproject.DBproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class EmployeeController {
    // 사원 전용 마이페이지
    @GetMapping("/log/employee")
    public String goEmployeePage(){
        return "/log/employee";
    }
}
