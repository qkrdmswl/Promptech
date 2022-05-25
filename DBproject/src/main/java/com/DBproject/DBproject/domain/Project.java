package com.DBproject.DBproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {

    @Id @Column(length = 255)
    private String project_id;
    private String project_name;
    private String ordering_company;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    //@Column(nullable = false)
    private Long project_cost;

    @OneToMany(mappedBy = "project")
    private List<Works_for> works_fors = new ArrayList<>();
}
