package com.DBproject.DBproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CeoController {

    // ceo 전용 마이페이지
    @GetMapping("/log/ceo")
    public String goCEOPage(){
        return "/log/ceo";
    }

}
