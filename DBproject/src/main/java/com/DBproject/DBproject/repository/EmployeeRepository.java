package com.DBproject.DBproject.Repository;

import com.DBproject.DBproject.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Employee employee) {
        em.persist(employee);
    }


    public Employee findOne(int id) { return em.find(Employee.class, id);
    }

    public List<Employee> findByNum(String num){
        return em.createQuery("select e from Employee e where e.employee_number = :num",Employee.class)
                .setParameter("num",num)
                .getResultList();
    }
    public List<Employee> findBylog(String log){
        return em.createQuery("select e from Employee e where e.log_id = :log",Employee.class)
                .setParameter("log",log)
                .getResultList();
    }
}
