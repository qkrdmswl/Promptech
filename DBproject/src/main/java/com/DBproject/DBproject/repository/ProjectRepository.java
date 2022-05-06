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

    public List<Project> findById(String id){
        return em.createQuery("select p from Project p where p.project_id = :p_id", Project.class)
                .setParameter("p_id",id)
                .getResultList();
    }

    public Project findByProjcetId(String id){
        return em.find(Project.class ,id);
    }



    public List<Project> findAll(){
        return em.createQuery("select p from Project p ", Project.class)
                .getResultList();
    }


}