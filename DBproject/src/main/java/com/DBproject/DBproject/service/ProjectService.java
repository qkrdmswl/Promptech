package com.DBproject.DBproject.Service;

import com.DBproject.DBproject.Repository.ProjectRepository;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<Project> findOne(String id){return projectRepository.findById(id);}


    public boolean validateionDuplicateId(Project project) {
        List<Project> findId = projectRepository.findById(project.getProject_id());
        if (!findId.isEmpty()) {
            throw new AlreadyRegisteredIdException("이미 존재하는 프로젝트 아이디입니다.");
        }
        return true;
    }
}
