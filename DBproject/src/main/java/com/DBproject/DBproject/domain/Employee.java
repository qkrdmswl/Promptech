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
@Table(indexes = @Index(name="idx_logid_password", columnList = "log_id, password",unique = true))
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
    @Column(length = 50,nullable = false,unique = true)
    private String log_id;
    @Column(length = 50,nullable = false)
    private String password;
    @Column(length = 50 ,nullable = false)
    private String phoneNum;
    @Column(length = 50 , nullable =false)
    private String email;
    @Column(length = 50,nullable = false)
    private String e_skill;
    @Column(length = 50,nullable = false)
    @Id @Column(length = 50,nullable = false)
    private String experience_name;



}