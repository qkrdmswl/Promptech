package com.DBproject.DBproject.Service;

import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.EditForm;
import com.DBproject.DBproject.controller.dto.RegisterForm;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Works_for;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

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
        validateionDuplicateId(employee);
        employeeRepository.save(employee);
        return employee.getEmployee_id();
    }

    public Employee findEmployee(int employeeId){
        return employeeRepository.findOne(employeeId);
    }

    @Transactional
    public void updateEmployeeInfo(@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginEmployee){
        // JPA 자동 변경 감지 - merge 쓰지 않고, 준영속 엔티티(DB에 한번들어갔다나온) 를 영속상태에서 JPA 변경 처리 코드
        // merge 쓰면 안됨 --> 수정되지않은값은 그냥 기존값이 아니라 null로 set 할 수 있음
        Employee foundEmployee=employeeRepository.findOne(loginEmployee.getEmployee_id());
        foundEmployee.setEmployee_name(loginEmployee.getEmployee_name());
        foundEmployee.setPassword(loginEmployee.getPassword());
        foundEmployee.setEmployee_school(loginEmployee.getEmployee_school());
        foundEmployee.setPhoneNum(loginEmployee.getPhoneNum());
        foundEmployee.setEmail(loginEmployee.getEmail());
        foundEmployee.setEmployee_career(loginEmployee.getEmployee_career());
        foundEmployee.setEmployee_skill(loginEmployee.getEmployee_skill());
    }


    public boolean validateionDuplicateNum(Employee employee) {
        List<Employee> findnum = employeeRepository.findByNum(employee.getEmployee_number());
        if (!findnum.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateionDuplicateId(Employee employee) {
        List<Employee> findId = employeeRepository.findByLog(employee.getLog_id());
        if (!findId.isEmpty()) {
            throw new AlreadyRegisteredIdException("이미 존재하는 아이디입니다.");
        }
        //if(!findId.isEmpty()){return false;}
        //return true;
        //}
        return true;
    }
    public List<Works_for> works_fors(String id){
        return employeeRepository.findById(id);
    }
}