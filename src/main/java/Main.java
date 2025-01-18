import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        try{
            EntityManager em = Persistence.createEntityManagerFactory("MSSQL").createEntityManager();
            em.getTransaction().begin();

            em.getTransaction().commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
