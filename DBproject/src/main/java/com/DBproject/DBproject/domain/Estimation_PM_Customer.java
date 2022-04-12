package com.DBproject.DBproject.domain;

import com.DBproject.DBproject.domain_id.estimation_pm;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
@IdClass(estimation_pm.class)
public class Estimation_PM_Customer {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_number", nullable = false)
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Id
    @Column(length = 50)
    private String estimator_type;
    @Column(nullable = false,length = 10)
    private int working_score;
    @Column(nullable = false,length = 255)
    private String working_comments;
    @Column(nullable = false,length = 10)
    private int comunication_score;
    @Column(nullable = false,length = 255)
    private String comunication_comments;
}
