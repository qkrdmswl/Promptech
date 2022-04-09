package com.DBproject.DBproject.Domain;


import com.DBproject.DBproject.Domain_Id.work_em;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(work_em.class)

public class Works_for {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="project_id" )
    private Project project;

    private String e_job;
    private LocalDate e_start_d;
    private LocalDate e_end_d;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee_works;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_number",nullable = false)
    private Project projects;*/


}
