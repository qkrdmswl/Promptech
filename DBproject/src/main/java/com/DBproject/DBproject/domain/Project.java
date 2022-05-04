package com.DBproject.DBproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Project {
    @Id @Column(length = 255)
    private String project_id;
    //@Column(length = 50,nullable = false)
    private String project_name;
    //@Column(length = 50,nullable = false)
    private String ordering_company;
    //@Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    //@Column(nullable = false)
    private Long project_cost;

   /* @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects")
    List<Works_for> work =new ArrayList<Works_for>();*/
}
