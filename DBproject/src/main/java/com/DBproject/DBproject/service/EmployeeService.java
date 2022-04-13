package com.DBproject.DBproject.Service;

import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Autowired  // 생성자 주입
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public int join(Employee employee) {
        //validateionDuplicateNum(employee);
        //validateionDuplicateId(employee);
        employeeRepository.save(employee);
        return employee.getEmployee_id();
    }

    public boolean validateionDuplicateNum(Employee employee) {
        List<Employee> findnum = employeeRepository.findByNum(employee.getEmployee_number());
        /*if(!findnum.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }*/
        if (!findnum.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateionDuplicateId(Employee employee) {
        List<Employee> findId = employeeRepository.findBylog(employee.getLog_id());
        /*if (!findId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 ID입니다.");
        }*/
        if(!findId.isEmpty()){return false;}
        return true;
    }



}