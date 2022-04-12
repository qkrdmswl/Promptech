package com.DBproject.DBproject.Service;

import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Autowired  // 생성자 주입
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public int join(Employee employee) {
        employeeRepository.save(employee);
        return employee.getEmployee_id();
    }

}