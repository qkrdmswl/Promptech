package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.EditForm;
import com.DBproject.DBproject.controller.dto.LoginForm;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class EmployeeController {
    // 사원 전용 마이페이지
    private final EmployeeService employeeService;

    @GetMapping("/log/employee")
    public String goEmployeePage(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginMember, Model model) {
        model.addAttribute("employee", loginMember);
        List<Works_for> works_fors = employeeService.works_fors(loginMember.getLog_id());
        model.addAttribute("worksFor", works_fors);
        return "/log/employee";
    }

    @GetMapping("/edit/employee")
    public String goEditForm(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginEmployee, Model model) {
        //정보가져오기
        EditForm editForm = getEditForm(loginEmployee);  // editForm에 loginEmployee 를 주입
        model.addAttribute("form", editForm);
        return "log/employeeEdit";
    }

    // 주입 메서드
    private EditForm getEditForm(Employee loginEmployee) {
        Employee employee = employeeService.findEmployee(loginEmployee.getEmployee_id());
        EditForm editForm = new EditForm();
        editForm.setEmployee_name(loginEmployee.getEmployee_name());
        editForm.setPassword(loginEmployee.getPassword());
        editForm.setEmployee_school(loginEmployee.getEmployee_school());
        editForm.setPhoneNum(loginEmployee.getPhoneNum());
        editForm.setEmail(loginEmployee.getEmail());
        return editForm;
    }


    @PostMapping("edit/employee")
    public String editEmployeeInfo( @SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginEmployee,EditForm form){
        getFormAndEditEmployee(loginEmployee, form); // 준영속 엔티티에 들어가기 전 폼으로부터 데이터받고, 직원을 수정할 준비
        employeeService.updateEmployeeInfo(loginEmployee); // 수정
        return "redirect:/log/employee";
    }
    private void getFormAndEditEmployee(Employee loginEmployee, EditForm form) {
        loginEmployee.setEmployee_name(form.getEmployee_name());
        loginEmployee.setPassword(form.getPassword());
        loginEmployee.setEmployee_school(form.getEmployee_school());
        loginEmployee.setPhoneNum(form.getPhoneNum());
        loginEmployee.setEmail(form.getEmail());
    }

}
