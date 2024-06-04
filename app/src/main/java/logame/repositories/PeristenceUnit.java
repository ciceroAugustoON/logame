package logame.repositories;

import jakarta.persistence.EntityManagerFactory;

public class PeristenceUnit {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        if (emf == null) {
            PeristenceUnit.emf = emf;
        }
    }
    
}
