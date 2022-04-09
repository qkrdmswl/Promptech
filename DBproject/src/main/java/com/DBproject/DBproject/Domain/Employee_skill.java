package com.DBproject.DBproject.Domain;

import com.DBproject.DBproject.Domain_Id.skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(skill.class)
public class Employee_skill {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Id
    private String e_skill;


}
