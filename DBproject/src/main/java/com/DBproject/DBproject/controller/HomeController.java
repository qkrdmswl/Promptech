package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.Session.SessionConstants;
import com.DBproject.DBproject.controller.dto.LoginForm;
import com.DBproject.DBproject.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;



@Controller
@RequiredArgsConstructor
public class HomeController   {

    @GetMapping ("/")
    public String Home(Model model,@SessionAttribute(name = SessionConstants.LoginMember, required = false) Employee loginMember){
        model.addAttribute("loginForm",new LoginForm());

        /*if (loginMember == null) {
            return "home";
        }*/
        return "home";
    }







}
