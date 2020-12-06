package si.fri.prpo.skupina27.storitve.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.storitve.anotacije.BeleziKlice;
import si.fri.prpo.skupina27.storitve.interceptorji.BeleziKliceInterceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped

public class SobeZrno {

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
    public List<Soba> getSobe() {

        List<Soba> sobe = em.createNamedQuery("Soba.getAll").getResultList();

        return sobe;
    }

    public List<Soba> getSobe(QueryParameters query) {

        return JPAUtils.queryEntities(em, Soba.class, query);
    }

    public Long getSobeCount(QueryParameters query) {

        return JPAUtils.queryEntitiesCount(em, Soba.class, query);
    }

    @BeleziKlice
    public Soba getSoba(int sobaId) {

        return em.find(Soba.class, sobaId);
    }

    @BeleziKlice
    @Transactional
    public Soba dodajSobo(Soba soba){

        if (soba != null) {
            em.persist(soba);
        }

        return soba;
    }

    @BeleziKlice
    @Transactional
    public Soba posodobiSobo(int sobaId, Soba soba) {

        Soba s = em.find(Soba.class, sobaId);
        soba.setSobaId(s.getSobaId());
        em.merge(soba);

        return soba;
    }

    @BeleziKlice
    @Transactional
    public boolean odstraniSobo(int sobaId) {

        Soba soba = em.find(Soba.class, sobaId);

        if (soba != null) {
            em.remove(soba);
            return true;
        }

        return false;
    }
}