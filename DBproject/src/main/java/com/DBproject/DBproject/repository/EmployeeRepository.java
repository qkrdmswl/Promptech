package com.DBproject.DBproject.repository;

import com.DBproject.DBproject.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EntityManager em;

    public void save(Employee employee) {
        em.persist(employee);
    }
    public Employee findOne(Long id) {
        return em.find(Employee.class, id);
    }
}
