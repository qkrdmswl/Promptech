package com.DBproject.DBproject.Service;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.Repository.ProjectRepository;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired  // 생성자 주입
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    // 프로젝트 저장
    public String join(Project project) {
        validateionDuplicateId(project);
        projectRepository.save(project);
        return project.getProject_id();
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<Project> findOne(String id){
        return projectRepository.findById(id);}

    public Project findProject(String id){
        return projectRepository.findByProjcetId(id);
    }

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

    // 프로젝트 아이디 중복 방지
    public boolean validateionDuplicateId(Project project) {
        List<Project> findId = projectRepository.findById(project.getProject_id());
        if (!findId.isEmpty()) {
            throw new AlreadyRegisteredIdException("이미 존재하는 프로젝트 아이디입니다.");
        }
        return true;
    }

}
