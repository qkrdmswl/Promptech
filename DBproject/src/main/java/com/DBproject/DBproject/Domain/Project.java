package com.DBproject.DBproject.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {
    @Id
    private String project_id;
    private String project_name;
    private LocalDate start_date;
    private LocalDate end_date;

   /* @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects")
    List<Works_for> work =new ArrayList<Works_for>();*/
}
