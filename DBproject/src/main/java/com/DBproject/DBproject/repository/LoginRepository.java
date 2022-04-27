package com.DBproject.DBproject.Repository;

import com.DBproject.DBproject.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    @PersistenceContext
    private final EntityManager em;

    public List<Employee> findById_password(String id,String password){
        return em.createQuery("select e from Employee e where e.log_id= :id and e.password= :password" ,Employee.class)
                .setParameter("id",id)
                .setParameter("password",password)
                .getResultList();
    }

}
