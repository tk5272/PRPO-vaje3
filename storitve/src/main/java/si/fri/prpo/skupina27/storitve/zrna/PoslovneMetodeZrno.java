package si.fri.prpo.skupina27.storitve.zrna;


import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.entitete.Vrata;
import si.fri.prpo.skupina27.storitve.dtos.DodajanjeVratDto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;


@ApplicationScoped
public class PoslovneMetodeZrno
{

    private Logger log = Logger.getLogger(OsebeZrno.class.getName());

    @Inject
    private OsebeZrno osebeZrno;
    @Inject
    private SobeZrno sobeZrno;
    @Inject
    private VrataZrno vrataZrno;

    @PostConstruct
    private void init()
    {
        log.info("Inicializacija: " + OsebeZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy()
    {
        log.info("Unicenje: " + OsebeZrno.class.getSimpleName());
    }

    @Transactional
    public Soba dodajStVrat(DodajanjeVratDto dv) {

        Soba soba = sobeZrno.getSoba(dv.getSobaId());

        if(soba == null) {
            log.info("Soba ne obstaja");
            return null;
        }

        for(int i = 0; i < dv.getStVrat(); i++) {
            Vrata vrata = new Vrata();
            vrata.setSobaId(soba);
            vrataZrno.dodajVrata(vrata);
        }

        return soba;
    }

}
