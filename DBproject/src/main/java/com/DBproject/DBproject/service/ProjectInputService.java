package com.DBproject.DBproject.service;

import com.DBproject.DBproject.Repository.ProjectRepository;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectInputService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired  // DI
    public ProjectInputService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    // 진행중인 프로젝트 상황들 리스트로 가져오기
    public List<Works_for> findDoingProjectsInfo() {
        return projectRepository.findDoingProjectsInfoAll();
    }

    // works_for id 가지고 프로젝트 찾기
    public Project findProjectById(String projectId) {
        return projectRepository.findByProjcetId(projectId);
    }

    // works_for id 가지고 사원 찾기
    public Employee findPEmployeeById(int employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    // 프로젝트 투입 저장
    @Transactional
    public void inputProjectSave(Works_for wf) {
        projectRepository.saveWorkingProject(wf);
    }

}
