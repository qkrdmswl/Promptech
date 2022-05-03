package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.ProjectService;
import com.DBproject.DBproject.controller.dto.FindOneProjectForm;
import com.DBproject.DBproject.controller.dto.ProjectForm;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project/projectRegister")
    public String ProjectCreate(@Valid ProjectForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/project/projectRegister";
        }
        try {

            Project project = new Project();
            project.setProject_id(form.getProject_id());
            project.setProject_name(form.getProject_name());
            project.setOrdering_company(form.getOrdering_company());
            project.setStart_date(LocalDate.parse(form.getStart_date(), DateTimeFormatter.ISO_DATE));
            project.setEnd_date(LocalDate.parse(form.getEnd_date(), DateTimeFormatter.ISO_DATE));
            project.setProject_cost(Long.parseLong(form.getCost()));

            projectService.join(project);
            return "redirect:/log/adminPage";  // 여기 추가로 코드가 들어가면 좋을듯

        } catch (AlreadyRegisteredIdException v) {
            model.addAttribute("error", new AlreadyRegisteredIdException(v.getMessage()));
            return "/project/projectRegister";
        }

    }

    @GetMapping("/project/projectEdit")
    public String ProjectFindOne(@Valid @ModelAttribute("projectForm") FindOneProjectForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/project/projectEdit";
        }
        List<Project> project = projectService.findOne(form.getProject_id());
        model.addAttribute("project", project);
        return "/project/projectEdit";
    }
}
