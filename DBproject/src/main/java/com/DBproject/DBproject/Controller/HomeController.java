package com.DBproject.DBproject.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String Home(){
        log.info("home controller");
        return "home";
    }

}
