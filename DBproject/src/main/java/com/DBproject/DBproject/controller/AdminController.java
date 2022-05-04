package com.DBproject.DBproject.controller;
import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.Service.ProjectService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.FindOneProjectForm;
import com.DBproject.DBproject.controller.dto.ProjectEditForm;
import com.DBproject.DBproject.controller.dto.ProjectForm;
import com.DBproject.DBproject.controller.dto.RegisterForm;
import com.DBproject.DBproject.domain.Authority;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

   private  final ProjectService projectService;
    // admin 전용 마이페이지
    @GetMapping("/log/adminPage")
    public String goAdminPage(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginMember, Model model){
        model.addAttribute("admin", loginMember);
        model.addAttribute("project",projectService.findAll());

        return "/log/adminPage";
    }



    @GetMapping("/project/new")
    public String goProjectRegister(Model model){
        model.addAttribute("projectForm",new ProjectForm());

        return "/project/projectRegister";
    }

    @GetMapping("/project/edit")
    public String goProjectEdit(Model model){
        model.addAttribute("projectForm",new FindOneProjectForm());

        return "/project/projectEdit";
    }





}
