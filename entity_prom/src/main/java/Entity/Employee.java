package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@SequenceGenerator(
        name ="em_seq_gr",
        sequenceName ="MEMBER_SEQ",
        initialValue = 3,allocationSize = 1
)
@Entity
public class Employee {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int employee_id;
    @Column(unique = true)
    private String employee_number;
    private String employee_name;
    private String employee_school;
    @Enumerated(EnumType.STRING)
    private Enum_Authority authority;

    @OneToMany(mappedBy = "employee_works")
    List<Works_for> works = new ArrayList<Works_for>();
    @OneToMany(mappedBy = "employee_skill")
    List<Employee_skill> skill = new ArrayList<Employee_skill>();
    @OneToMany(mappedBy = "employee_experience")
    List<Employee_career> experience = new ArrayList<Employee_career>();

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public String getEmployee_school() {
        return employee_school;
    }

    public void setEmployee_school(String employee_shool) {
        this.employee_school = employee_school;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }
}
