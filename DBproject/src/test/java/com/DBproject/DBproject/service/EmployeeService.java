package com.DBproject.DBproject.service;

import com.DBproject.DBproject.domain.Employee;
//import com.DBproject.DBproject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final com.DBproject.DBproject.repository.EmployeeRepository employeeRepository;

    @Autowired  // 생성자 주입
    public EmployeeService(com.DBproject.DBproject.repository.EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public Long join(Employee employee) {
        employeeRepository.save(employee);
        return employee.getEmployee_id();
    }

}
