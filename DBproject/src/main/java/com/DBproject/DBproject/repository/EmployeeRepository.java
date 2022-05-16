package com.DBproject.DBproject.Repository;

import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Works_for;
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


    public Employee findOne(int id) {
        return em.find(Employee.class, id);
    }
    public List<Employee> findAll(){
        return em.createQuery("select e from Employee e",Employee.class)
                .getResultList();
    }

    public List<Employee> findByNum(String num){
        return em.createQuery("select e from Employee e where e.employee_number = :num",Employee.class)
                .setParameter("num",num)
                .getResultList();
    }
    public List<Employee> findByLog(String log){
        return em.createQuery("select e from Employee e where e.log_id = :log",Employee.class)
                .setParameter("log",log)
                .getResultList();
    }

    public List<Works_for> findById(String id){
        return em.createQuery("select w from Works_for  w where w.employee.log_id=:log_id",Works_for.class)
                .setParameter("log_id",id)
                .getResultList();
    }

    public List<Works_for> findByEmId(int id){ // 현재 진행중인 프로젝트를 모두 가져오기 위함
        return em.createQuery("select w from Works_for  w where (w.employee.employee_id=:id) and (w.e_end_d >= current_date or w.e_end_d is null) ",Works_for.class)
                .setParameter("id",id)
                .getResultList();
    }

}