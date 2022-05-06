package com.DBproject.DBproject.Service;

import com.DBproject.DBproject.Repository.ProjectRepository;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired  // 생성자 주입
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String join(Project project) {
        validateionDuplicateId(project);
        projectRepository.save(project);
        return project.getProject_id();
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    // 진행중인 프로젝트 상황들 리스트로 가져오기
    public List<Works_for>findDoingProjectsInfo(){
        return projectRepository.findDoingProjectsInfoAll();
    }

    public List<Project> findOne(String id){
        return projectRepository.findById(id);}

    @Transactional
    public void updateProjectInfo(Project project){
        // JPA 자동 변경 감지 - merge 쓰지 않고, 준영속 엔티티(DB에 한번들어갔다나온) 를 영속상태에서 JPA 변경 처리 코드
        // merge 쓰면 안됨 --> 수정되지않은값은 그냥 기존값이 아니라 null로 set 할 수 있음
        Project foundProject=projectRepository.findByProjcetId(project.getProject_id());
        foundProject.setProject_id(project.getProject_id());
        foundProject.setOrdering_company(project.getOrdering_company());
        foundProject.setProject_name(project.getProject_name());
        foundProject.setProject_cost(project.getProject_cost());
        foundProject.setStart_date(project.getStart_date());
        foundProject.setEnd_date(project.getEnd_date());
    }



    public boolean validateionDuplicateId(Project project) {
        List<Project> findId = projectRepository.findById(project.getProject_id());
        if (!findId.isEmpty()) {
            throw new AlreadyRegisteredIdException("이미 존재하는 프로젝트 아이디입니다.");
        }
        return true;
    }
}
