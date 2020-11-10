package si.fri.prpo.skupina27.storitve;

import si.fri.prpo.skupina27.entitete.Soba;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped

public class SobeZrno {
    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    public List<Soba> getSobe() {
            List<Soba> sobe = em.createNamedQuery("Soba.getAll").getResultList();
        return sobe;
    }
}