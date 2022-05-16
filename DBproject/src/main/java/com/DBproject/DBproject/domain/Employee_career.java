package com.DBproject.DBproject.domain;

import com.DBproject.DBproject.domain_id.career;
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
@IdClass(career.class)
public class Employee_career {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")  // ManyToOne은 항상 연관관계의 주인
    private Employee employee;
    @Id @Column(length = 50,nullable = false)
    private String experience_name;
}
