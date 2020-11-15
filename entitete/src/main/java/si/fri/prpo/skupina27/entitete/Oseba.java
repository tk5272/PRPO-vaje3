package si.fri.prpo.skupina27.entitete;

import javax.persistence.*;
import java.util.*;

@Entity(name = "oseba")
@NamedQueries(value =
        {
                @NamedQuery(name = "Oseba.getAll", query = "SELECT o FROM oseba o"),
                @NamedQuery(name = "Oseba.getById",
                        query = "SELECT o FROM oseba o WHERE o.osebaId = :id"),
                @NamedQuery(name = "Oseba.getImePriimekSobeById",
                        query = "SELECT o.ime, o.priimek FROM oseba o WHERE o.osebaId = :id")
        })

public class Oseba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer osebaId;

    private String ime;

    private String priimek;

    @OneToMany(mappedBy = "osebe",  cascade = CascadeType.ALL) //ena oseba ima lahko več sob
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

    public String toString() {
        return String.format("%s %s", this.ime, this.priimek);
    }
}
