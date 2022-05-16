package com.DBproject.DBproject.controller.dto;

import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class RegisterPWorkerForm {
    @NotEmpty(message = "사번 입력은 필수입니다.")
    private String employee_id;

    @NotEmpty(message = "프로젝트 아이디 입력은 필수입니다.")
    private String project_id;

    @NotEmpty(message = "직책 입력은 필수입니다.")
    private String e_job;

    @NotEmpty(message = "프로젝트 참여날짜 입력은 필수입니다.")
    //@DateTimeFormat(pattern = "yyyy-MM-dd") 스트링으로 받아야 validation 가능
    private  String e_start_d;  // null 이면 안됨

    //@NotEmpty(message = "프로젝 이탈날짜 입력은 필수입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate e_end_d;  // null일 수가 있음 - 진행중일때는 아직 이탈하지 않은상태

}




