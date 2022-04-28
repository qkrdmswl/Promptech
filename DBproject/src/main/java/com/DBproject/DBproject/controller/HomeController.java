package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.controller.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping ("/")
    public String Home(Model model){
        model.addAttribute("loginForm",new LoginForm());
        log.info("home controller");
        return "home";
    }





}
