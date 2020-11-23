package si.fri.prpo.skupina27.storitve.zrna;

import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.storitve.anotacije.BeleziKlice;

import javax.annotation.*;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped //@RequestScoped

public class OsebeZrno
{

    private Logger log = Logger.getLogger(OsebeZrno.class.getName());
    private String zrno;

    @PostConstruct
    private void init() {

        zrno = UUID.randomUUID().toString();
        log.info("Inicializacija zrna " + OsebeZrno.class.getSimpleName() +
                "Identifikator zrna je: " + zrno);

    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + OsebeZrno.class.getSimpleName() +
                "Identifikator zrna je: " + zrno);

    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    @BeleziKlice
    public List<Oseba> getOsebe() {

        List<Oseba> osebe = em.createNamedQuery("Oseba.getAll").getResultList();
        System.out.println(osebe.size());

        return osebe;
    }

    @BeleziKlice
    public Oseba getOseba(int osebaId) {

        return em.find(Oseba.class, osebaId);
    }

    @BeleziKlice
    @Transactional
    public Oseba dodajOsebo(Oseba oseba){

        if (oseba != null) {
            em.persist(oseba);
        }

        return oseba;
    }

    @BeleziKlice
    @Transactional
    public Oseba posodobiOsebo(int osebaId, Oseba oseba) {

        Oseba o = em.find(Oseba.class, osebaId);
        oseba.setOsebaId(o.getOsebaId());
        em.merge(oseba);

        return oseba;
    }

    @BeleziKlice
    @Transactional
    public boolean odstraniOsebo(int osebaId) {

        Oseba oseba = em.find(Oseba.class, osebaId);

        if (oseba != null) {
            em.remove(oseba);
            return true;
        }

        return false;
    }
}