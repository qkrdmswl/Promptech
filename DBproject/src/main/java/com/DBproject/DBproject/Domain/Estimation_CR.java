package com.DBproject.DBproject.Domain;

import com.DBproject.DBproject.Domain_Id.estimation_cr;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@IdClass(estimation_cr.class)
public class Estimation_CR {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_number",nullable = false)
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;



    private int working_score;
    private String working_comments;
    private int comunication_score;
    private String comunication_comments;
}
