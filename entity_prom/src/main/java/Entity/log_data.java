package Entity;

import javax.persistence.*;

@Entity
public class log_data {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    private String log_id;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private  Employee employee_log;
}
