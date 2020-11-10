package si.fri.prpo.skupina27.storitve;

import si.fri.prpo.skupina27.entitete.Vrata;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped

public class VrataZrno {
    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    public List<Vrata> getVrata() {
        List<Vrata> vrata = em.createNamedQuery("Vrata.getAll").getResultList();
        return vrata;
    }
}