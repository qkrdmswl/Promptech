package Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String project_num;
    private String project_name;
    private LocalDate start_date;
    private LocalDate end_date;

    @OneToMany
    @JoinColumn(name = "project")
    List<Works_for> work =new ArrayList<Works_for>();
}
