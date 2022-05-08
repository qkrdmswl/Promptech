package com.DBproject.DBproject.Repository;

import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }

    // 프로젝트 투입 내역 저장 - 기존에 employee,project는 등록이 되어있는상황- merge를 써야함 persist x
    public void saveWorkingProject(Works_for inputProject){
        em.merge(inputProject);
    }

    public List<Project> findById(String id){
        return em.createQuery("select p from Project p where p.project_id = :p_id", Project.class)
                .setParameter("p_id",id)
                .getResultList();
    }

    public Project findByProjcetId(String id){
        return em.find(Project.class ,id);
    }


    // 진행 중인 프로젝트 상황 가져오기
    public List<Works_for> findDoingProjectsInfoAll(){
        return em.createQuery("select w from Works_for w",Works_for.class)
                .getResultList();
    }

    public List<Project> findAll(){
        return em.createQuery("select p from Project p ", Project.class)
                .getResultList();
    }


}