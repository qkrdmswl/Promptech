package com.DBproject.DBproject.domain;

import com.DBproject.DBproject.domain_id.id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
//@IdClass(id.class)
public class log_data {
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private  Employee employee;
    @Id
    private String id;
    @Column(length = 50,nullable = false)
    private String log_id;
    @Column(length = 50,nullable = false)
    private String password;
}
