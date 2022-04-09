package com.DBproject.DBproject;

import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.repository.EmployeeRepository;
import com.DBproject.DBproject.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class EmployeeServiceTest {


    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 직원_작동여부() throws Exception {
        // given
        Employee employee = new Employee();
        employee.setEmployee_name("hanjisu");
        employee.setEmployee_school("mju");
        // when
        Long savedId = employeeService.join(employee);
        // then
        Assertions.assertEquals(employee, employeeRepository.findOne(savedId));
    }

}
