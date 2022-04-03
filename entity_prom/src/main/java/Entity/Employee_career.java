package Entity;

import javax.persistence.*;

@Entity
public class Employee_career {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee_experience;

    private String experience_name;
}
