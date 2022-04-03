import Entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em=emf.createEntityManager();

        EntityTransaction tx=em.getTransaction();
        tx.begin();
        try{
            /*Employee employee =new Employee();
            employee.setEmployee_name("lll");

            employee.setEmployee_number("999992");
            em.persist(employee);*/
            Employee ek = em.find(Employee.class,3);
            em.remove(ek);









            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
