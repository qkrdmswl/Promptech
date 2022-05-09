package com.DBproject.DBproject.controller.dto;

import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class EditPWorkerForm {

    private int employee_id;
    private String project_id;
    private String e_job;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate e_start_d;  // null 이면 안됨
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate e_end_d;  // null일 수가 있음 - 진행중일때는 아직 이탈하지 않은상태

}
