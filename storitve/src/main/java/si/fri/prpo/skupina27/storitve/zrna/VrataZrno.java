package si.fri.prpo.skupina27.storitve.zrna;

import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Vrata;
import si.fri.prpo.skupina27.storitve.anotacije.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped //@RequestScoped

public class VrataZrno {

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

    public List<Vrata> getAllVrata() {

        List<Vrata> vrata = em.createNamedQuery("Vrata.getAll").getResultList();

        return vrata;
    }

    public Vrata getVrata(int vrataId) {

        return em.find(Vrata.class, vrataId);
    }

    @BeleziKlice
    @Transactional
    public Vrata dodajVrata(Vrata vrata){

        if (vrata != null) {
            em.persist(vrata);
        }

        return vrata;
    }

    @BeleziKlice
    @Transactional
    public boolean odstraniVrata(int vrataId) {

        Vrata vrata = em.find(Vrata.class, vrataId);

        if (vrata != null) {
            em.remove(vrata);
            return true;
        }

        return false;
    }
}