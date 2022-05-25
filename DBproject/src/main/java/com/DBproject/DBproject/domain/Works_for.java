package com.DBproject.DBproject.domain;


import com.DBproject.DBproject.domain_id.work_em;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(work_em.class)
@EqualsAndHashCode
public class Works_for {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id" )
    private Employee employee;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)  // Eager -> Fetch + OISV : true - 쿼리 줄임 ,N+1
    @JoinColumn(name ="project_id" )
    private Project project;

    @Column(length = 50,nullable = false)
    private String e_job;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate e_start_d;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate e_end_d;

}