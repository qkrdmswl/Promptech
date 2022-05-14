package com.DBproject.DBproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int employee_id;
    @Column(unique = true ,length=25,nullable = false)
    private String employee_number;
    @Column(length = 50,nullable = false)
    private String employee_name;
    @Column(length = 50,nullable = false)
    private String employee_school;

    @Column(length = 25,nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @Column(length = 50,nullable = false)
    private String log_id;
    @Column(length = 50,nullable = false)
    private String password;
    @Column(length = 50 ,nullable = false)
    private String phoneNum;
    @Column(length = 50 , nullable =false)
    private String email;

    /*@OneToOne
    @JoinTable(name = "Employee_log",
               joincolejfewkjfnweopdijpowqkdwqdqwwd)*/

   /* @OneToMany(mappedBy = "employee")
    List<Works_for> works = new ArrayList<Works_for>();*/
    /*@OneToMany(mappedBy = "employee_skill")
    List<Employee_skill> skill = new ArrayList<Employee_skill>();
    @OneToMany(mappedBy = "employee_experience")
    List<Employee_career> experience = new ArrayList<Employee_career>();*/


}