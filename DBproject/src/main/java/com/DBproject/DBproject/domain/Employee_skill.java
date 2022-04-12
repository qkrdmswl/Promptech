package com.DBproject.DBproject.domain;

import com.DBproject.DBproject.domain_id.skill;
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
@IdClass(skill.class)
public class Employee_skill {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Id @Column(length = 50,nullable = false)
    private String e_skill;


}
