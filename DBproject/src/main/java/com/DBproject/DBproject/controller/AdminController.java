package com.DBproject.DBproject.controller;
import com.DBproject.DBproject.Service.ProjectService;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.service.ProjectInputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProjectService projectService;
    private final ProjectInputService projectInputService;

    // admin 전용 마이페이지
    @GetMapping("/log/adminPage")
    public String goAdminPage(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginMember, Model model) {
        model.addAttribute("admin", loginMember);
        model.addAttribute("project", projectService.findAll());
        model.addAttribute("doingPInfo", projectInputService.findDoingProjectsInfo());
        return "/log/adminPage";
    }
}
