package Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Works_for {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int w_n;
    private String e_job;
    private LocalDate e_start_d;
    private LocalDate e_end_d;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee_works;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_num")
    private Project project;


}
