package si.fri.prpo.skupina27.storitve;

import si.fri.prpo.skupina27.entitete.Oseba;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped

public class OsebeZrno
{
    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    public List<Oseba> getOsebe() {
        List<Oseba> osebe = em.createNamedQuery("Oseba.getAll").getResultList();
        System.out.println(osebe.size());
        return osebe;
    }
}