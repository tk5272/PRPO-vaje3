package si.fri.prpo.skupina27.storitve.zrna;


import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.entitete.Vrata;
import si.fri.prpo.skupina27.storitve.dtos.DodajanjeVratDto;
import si.fri.prpo.skupina27.storitve.dtos.OsebaDto;
import si.fri.prpo.skupina27.storitve.dtos.SobaDto;
import si.fri.prpo.skupina27.storitve.dtos.ZapisDto;
import si.fri.prpo.skupina27.storitve.izjeme.NeveljavenUporabnikIdIzjema;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
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

    private Client httpClient;
    private String baseUrl;

    private Client httpClientSlike;
    private String baseUrlSlike;
    private String slikeKey;

    @PostConstruct
    private void init() {

        log.info("Inicializacija: " + PoslovneMetodeZrno.class.getSimpleName());

        httpClient = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance().get("integrations.podatki.base-url")
                .orElse("http://localhost:8081/V1podatki");
        httpClientSlike = ClientBuilder.newClient();
        baseUrlSlike = ConfigurationUtil.getInstance().get("integrations.slike-api.base-url")
                .orElse("https://api.unsplash.com");
        slikeKey = ConfigurationUtil.getInstance().get("integrations.slike-api.api-key")
                .orElse("zAUlUQA2fa4G53X-7-cdtVjOBpoOjNNOqx1acSWOlcM");
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
    public int dodajOsebe(int sobaId, int sprememba) {

        Soba soba = sobeZrno.getSoba(sobaId);
        int spremenjeno = soba.getStLjudi() + sprememba;

        if(spremenjeno >= 0 && spremenjeno <= soba.getMaxLjudi()) {
            soba.setStLjudi(spremenjeno);
            return spremenjeno;
        }

        return -1;
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

    @Transactional
    public void dodajZapis(Oseba o, Soba s) {
        try {
            httpClient
                    .target(baseUrl + "/zasedenost/")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(new ZapisDto(o, s)));

        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    @Transactional
    public String pridobiSlike(String kljucnaBeseda) {

        try {
            String response = httpClientSlike
                    .target(baseUrlSlike + "/search/photos?query="+kljucnaBeseda+"&order_by=relevant&limit=8&client_id="+slikeKey)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);

            return response;

        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

}
