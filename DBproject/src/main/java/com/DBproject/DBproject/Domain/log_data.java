package com.DBproject.DBproject.Domain;

import com.DBproject.DBproject.Domain_Id.id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(id.class)
public class log_data {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private  Employee employee;
    @Id
    private String log_id;
    private String password;
}
