package com.DBproject.DBproject.service;
import com.DBproject.DBproject.Repository.ProjectRepository;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectInputService {

    private final ProjectRepository projectRepository ;
    private final EmployeeRepository employeeRepository ;

    @Autowired  // DI
    public ProjectInputService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository ;
        this.employeeRepository = employeeRepository ;
    }

    // 진행중인 프로젝트 상황들 리스트로 가져오기
    public List<Works_for> findDoingProjectsInfo() {
        return projectRepository.findDoingProjectsInfoAll();
    }

//    // Works_for 특정 사원 가져오기
//    public List<Works_for> findPwEmployee(int id){
//        return projectRepository.findEmployeeById(id);
//    }
//
//    // Works_for 특정 프로젝트 가져오기
//    public List<Works_for> findPwProject(String id){
//        return projectRepository.findProjectById(id);
//    }
//
    public List<Works_for> findPrEmById(int employeeId,String projectId){
        return projectRepository.findEmployeeById(employeeId,projectId);
    }

    // works_for id 가지고 프로젝트 찾기
    public Project findProjectById(String projectId) {
        return projectRepository.findByProjcetId(projectId);
    }

    // works_for id 가지고 사원 찾기
    public Employee findPEmployeeById(int employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    //works_for id 가지고 사원찾기 list로 받아서 그 사원이 몇개의 프로젝트에 투입되어있는지 확인하기 위함.
    public List<Works_for> findPEmployeeByIdList(int employeeId){
        return employeeRepository.findByEmId(employeeId);
    }

    // 프로젝트 투입 저장
    @Transactional
    public void inputProjectSave(Works_for wf) {
        projectRepository.saveWorkingProject(wf);
    }


}
