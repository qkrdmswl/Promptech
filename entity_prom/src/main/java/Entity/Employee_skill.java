package Entity;

import javax.persistence.*;

@Entity
public class Employee_skill {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int skill_id;

    private String e_skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee_skill;
}
