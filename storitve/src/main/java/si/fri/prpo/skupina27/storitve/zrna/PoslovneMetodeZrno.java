package si.fri.prpo.skupina27.storitve.zrna;


import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.entitete.Vrata;
import si.fri.prpo.skupina27.storitve.dtos.DodajanjeVratDto;
import si.fri.prpo.skupina27.storitve.dtos.OsebaDto;
import si.fri.prpo.skupina27.storitve.dtos.SobaDto;
import si.fri.prpo.skupina27.storitve.izjeme.NeveljavenUporabnikIdIzjema;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class PoslovneMetodeZrno {

    private Logger log = Logger.getLogger(OsebeZrno.class.getName());

    @Inject
    private OsebeZrno osebeZrno;
    @Inject
    private SobeZrno sobeZrno;
    @Inject
    private VrataZrno vrataZrno;

    @PostConstruct
    private void init() {
        log.info("Inicializacija: " + OsebeZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Unicenje: " + OsebeZrno.class.getSimpleName());
    }

    @Transactional
    public Soba dodajStVrat(DodajanjeVratDto dv) {

        Soba soba = sobeZrno.getSoba(dv.getSobaId());

        if (soba == null) {
            log.info("Soba ne obstaja");
            return null;
        }

        for (int i = 0; i < dv.getStVrat(); i++) {
            Vrata vrata = new Vrata();
            vrata.setSobaId(soba);
            vrataZrno.dodajVrata(vrata);
        }

        return soba;
    }

    @Transactional
    public List<String> sobeOsebe(OsebaDto oDto) {

        Oseba oseba = osebeZrno.getOseba(oDto.getOsebaId());

        if (oseba == null) {
            String msg = "Oseba ne obstaja";
            log.info(msg);
            throw new NeveljavenUporabnikIdIzjema(msg);
        }

        if (oseba.getSobe() == null) {
            log.severe("Sobe niso inicializirane");
            return null;
        }

        ArrayList<String> imenaSob = new ArrayList<String>();

        for(Soba s : oseba.getSobe())
            imenaSob.add(s.getImeSobe());

        return imenaSob;
    }

    @Transactional
    public double ljudiNaMeter(SobaDto sDto) {

        Soba soba = sobeZrno.getSoba(sDto.getSobaId());

        if (soba == null) {
            log.info("Soba ne obstaja");
            return -1;
        }

        double izracun = (double) soba.getStLjudi() / soba.getVelikost();
        izracun = (double) Math.round(izracun*100) / 100;
        return izracun;
    }
}
