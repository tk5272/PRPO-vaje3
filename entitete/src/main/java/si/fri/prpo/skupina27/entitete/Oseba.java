package si.fri.prpo.skupina27.entitete;

import javax.persistence.*;
import java.util.*;

@Entity(name = "oseba")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM oseba o")
        })

public class Oseba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer osebaId;

    private String ime;

    private String priimek;

    @OneToMany //ena oseba ima lahko več sob
    private List<Soba> sobe;

    public Integer getOsebaId() {
        return osebaId;
    }

    public void setOsebaId(Integer osebaId) {
        this.osebaId = osebaId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public List<Soba> getSobe() {
        return sobe;
    }

    public void setSobe(List<Soba> sobe) {
        this.sobe = sobe;
    }
}
