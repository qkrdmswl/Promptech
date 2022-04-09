package com.DBproject.DBproject.Domain;

import com.DBproject.DBproject.Domain_Id.career;
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
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Id
    private String experience_name;
}
