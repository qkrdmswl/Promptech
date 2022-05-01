package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.controller.dto.RegisterForm;
import com.DBproject.DBproject.domain.Authority;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.exception.AlreadyRegisteredIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {


    private final EmployeeService employeeService;

    @GetMapping("/registers/new")
    public String createForm(Model model){
        model.addAttribute("registerForm",new RegisterForm());
        return "registers/register";
    }

    @ExceptionHandler(AlreadyRegisteredIdException.class)
    @PostMapping(value = "/registers/new") // valid: memberform에 @Notempty를 인식.
    public String create(@Valid RegisterForm form, BindingResult result,Model model) {
        if (result.hasErrors()) { // bindingResult: 에러가 나면 다시 회원가입 창으로 돌려준다.
            return "registers/register";
        }
        try{
        Employee employee =new Employee();
        employee.setEmployee_name(form.getEmployee_name());
        employee.setEmployee_number(form.getEmployee_number());
        employee.setEmployee_school(form.getEmployee_school());
        employee.setLog_id(form.getLog_id());
        employee.setPassword(form.getPassword());
        employee.setPhoneNum(form.getPhoneNum());
        employee.setEmail(form.getEmail());
        employee.setAuthority(Authority.BASIC);

       employeeService.join(employee);
        return "redirect:/";  // 여기 추가로 코드가 들어가면 좋을듯

        }catch (AlreadyRegisteredIdException v){
            model.addAttribute("error", new AlreadyRegisteredIdException(v.getMessage()));
            return "registers/register";
        }

    }




}
