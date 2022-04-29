package com.DBproject.DBproject.Service;

import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.Repository.LoginRepository;
import com.DBproject.DBproject.domain.Authority;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class LoginService {
    private final LoginRepository loginRepository;


    @Autowired  // 생성자 주입
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String login(String id,String password){
        List<Employee> findId = loginRepository.findById_password(id,password);
        if(findId.get(0).getAuthority() == Authority.BASIC){
            return "BASIC";

        }
        else if(findId.get(0).getAuthority() == Authority.ADMIN){
            return "ADMIN";

        }
        else if(findId.get(0).getAuthority() == Authority.CEO){
            return "CEO";
        }

        else if(!findId.isEmpty()){
            throw new AlreadyRegisteredIdException("아이디가 존재하지 않습니다.");
        }
        return "";
    }

    public List<Employee> findOne(String id,String password){
        //loginRepository.findById(employee);
        List<Employee> findId = loginRepository.findById_password(id,password);

        if(findId.isEmpty()){
            return null;
        }

        return findId;
    }

}
