package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.controller.dto.FindOneProjectForm;
import com.DBproject.DBproject.controller.dto.ProjectEditForm;
import com.DBproject.DBproject.controller.dto.ProjectForm;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import com.DBproject.DBproject.exception.NoIdException;
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
import com.DBproject.DBproject.Service.ProjectService;

@Controller
@RequiredArgsConstructor
public class AdminProjectManageController {

    private final ProjectService projectService;

    // 프로젝트 등록 폼 가져오기
    @GetMapping("/project/new")
    public String goProjectRegister(Model model) {
        model.addAttribute("projectForm", new ProjectForm());
        return "/project/projectRegister";
    }

    // 프로젝트 등록
    @PostMapping("/project/projectRegister")
    public String createProject(@Valid ProjectForm form, BindingResult result, Model model) {
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
            return "redirect:/log/adminPage";

        } catch (AlreadyRegisteredIdException v) {
            model.addAttribute("error", new AlreadyRegisteredIdException(v.getMessage()));
            return "/project/projectRegister";
        }

    }
    // 프로젝트 수정폼 가져오기
    @GetMapping("/project/edit")
    public String goProjectEdit(Model model) {
        model.addAttribute("projectForm", new FindOneProjectForm());
        return "/project/projectEdit";
    }

    //프로젝트를 검색 후 해당 프로젝트의 정보를 담은 폼을 가져오기
    @GetMapping("/project/projectEdit")
    public String projectFindOne(@Valid @ModelAttribute("projectForm") FindOneProjectForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/project/projectEdit";
        }
        try {
            List<Project> project = projectService.findOne(form.getProject_id());
            if (project.isEmpty()) {
                throw new NoIdException("프로젝트가 존재하지 않습니다.");
            }
            boolean visible = true;   // true일때만 수정폼이 보이게함
            model.addAttribute("project", project);
            Project projects = project.get(0);
            model.addAttribute("projects", projects);
            ProjectEditForm projectEditForm = getProjectEditForm(project.get(0));
            model.addAttribute("projectEditForm", projectEditForm);
            model.addAttribute("visibility", visible);   // true일때만 수정폼이 보이게함
            return "/project/projectEdit";
        } catch (NoIdException e) {
            model.addAttribute("error", new NoIdException(e.getMessage()));
            return "/project/projectEdit";
        }
    }
    // 프로젝트 주입 메서드
    private ProjectEditForm getProjectEditForm(Project projectFind) {
        Project project = projectService.findOne(projectFind.getProject_id()).get(0);
        ProjectEditForm editForm = new ProjectEditForm();
        editForm.setProject_id(projectFind.getProject_id());
        editForm.setProject_name(projectFind.getProject_name());
        editForm.setOrdering_company(projectFind.getOrdering_company());
        editForm.setCost(projectFind.getProject_cost().toString());
        editForm.setStart_date(projectFind.getStart_date().toString());
        editForm.setEnd_date(projectFind.getEnd_date().toString());

        return editForm;
    }

    // 프로젝트 수정 완료
    @PostMapping("/project/projectEdit")
    public String editProject(Project projects, ProjectEditForm projectEditForm) {

        projectService.updateProjectInfo(getFormAndEditProject(projects, projectEditForm));

        return "redirect:/log/adminPage";
    }
    // 프로젝트 수정 주입 메서드
    private Project getFormAndEditProject(Project projects, ProjectEditForm projectEditForm) {
        projects.setProject_cost(Long.parseLong(projectEditForm.getCost()));
        projects.setProject_id(projectEditForm.getProject_id());
        projects.setProject_name(projectEditForm.getProject_name());
        projects.setOrdering_company(projectEditForm.getOrdering_company());
        projects.setStart_date(LocalDate.parse(projectEditForm.getStart_date(), DateTimeFormatter.ISO_DATE));
        projects.setEnd_date(LocalDate.parse(projectEditForm.getEnd_date(), DateTimeFormatter.ISO_DATE));
        return projects;
    }




}
