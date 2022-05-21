package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.SumCostDto;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.Repository.ProjectRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CeoController {

    // 조회는 service 거치지 않고 바로 repository 로 접근해서 출력만 해주는 형식이 깔끔합니다
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private  final com.DBproject.DBproject.Service.ProjectService projectService;

    // ceo 전용 마이페이지
    @GetMapping("/log/ceo")
    public String goCEOPage(Model model,@SessionAttribute(name = SessionConstants.LoginMember, required = false)  Employee loginMember){
        List<Employee> employeeList=employeeRepository.findAll();
        List<Works_for> projectWorkingList=projectRepository.findDoingProjectsInfoAll();
        List<Project> projects=projectRepository.findAll();
        LocalDate currentDate= LocalDate.now();
        SumCostDto sum = projectRepository.sumProjectCost().get(0);
        model.addAttribute("emlist",employeeList);
        model.addAttribute("pjworkinglist",projectWorkingList);
        model.addAttribute("currentDate",currentDate);
        model.addAttribute("project",projects);
        model.addAttribute("sum",sum);
        model.addAttribute("ceo",loginMember);


        return "log/ceo";
    }





    // 직원 조회
    @PostMapping("log/ceo/employeeInfo")
    public String getEmployeeInfoById(@RequestParam("employee_name") String employeeName , Model model,@SessionAttribute(name = SessionConstants.LoginMember, required = false)  Employee loginMember){

//        Employee findEmployee =employeeRepository.findOne(employeeId);
//        model.addAttribute("emInfo",findEmployee);
        List<Employee> findEmployee = employeeRepository.findByEmployeeName(employeeName);
        model.addAttribute("emInfo",findEmployee);

        //get 매핑에서 받은 모델을 똑같이 적용해 주어야 데이터가 뜬다.
        List<Project> projects2=projectRepository.findAll();
        LocalDate currentDate2= LocalDate.now();
        model.addAttribute("currentDate",currentDate2);
        model.addAttribute("project",projects2);
        SumCostDto sum = projectRepository.sumProjectCost().get(0);
        model.addAttribute("sum",sum);
        model.addAttribute("ceo",loginMember);
        return "log/ceo";
    }

    // 프로젝트 조회
    @PostMapping("log/ceo/projectInfo")
    public String getProejctInfoById(@RequestParam("project_name") String project_name,Model model,@SessionAttribute(name = SessionConstants.LoginMember, required = false)  Employee loginMember){
        //List<Works_for> findProject=projectRepository.findProjectByName(project_name);
        LocalDate currentDate= LocalDate.now();
        //boolean visibility =true;
        List<Project> projects=projectRepository.findProjectListByName(project_name);
        model.addAttribute("projects",projects);
        //model.addAttribute("pjInfo",findProject);
        model.addAttribute("currentDate",currentDate);
        //model.addAttribute("visibility",visibility);
        SumCostDto sum = projectRepository.sumProjectCost().get(0);
        model.addAttribute("sum",sum);

        model.addAttribute("ceo",loginMember);
        //get 매핑에서 받은 모델을 똑같이 적용해 주어야 데이터가 뜬다.
        List<Employee> employeeList=employeeRepository.findAll();
        model.addAttribute("emlist",employeeList);
        model.addAttribute("currentDate",currentDate);

        return "log/ceo";
    }


    @GetMapping("worksfor/{projectId}/list")
    public String worksforList(@PathVariable("projectId")String id, Model model,@SessionAttribute(name = SessionConstants.LoginMember, required = false)  Employee loginMember){
        List<Works_for> findProject = projectRepository.findProjectById(id);
        boolean visibility =true;
        model.addAttribute("visibility",visibility);
        model.addAttribute("pjInfo",findProject);
        LocalDate currentDate= LocalDate.now();
        List<Project> projects=projectRepository.findById(id);
        model.addAttribute("projects",projects);
        model.addAttribute("currentDate",currentDate);
        SumCostDto sum = projectRepository.sumProjectCost().get(0);
        model.addAttribute("sum",sum);
        model.addAttribute("ceo",loginMember);
        //get 매핑에서 받은 모델을 똑같이 적용해 주어야 데이터가 뜬다.
        List<Employee> employeeList=employeeRepository.findAll();
        model.addAttribute("emlist",employeeList);
        model.addAttribute("currentDate",currentDate);
        return  "log/ceo";
    }



}