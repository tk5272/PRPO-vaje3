package si.fri.prpo.skupina27.entitete;

import javax.persistence.*;
import java.util.*;

@Entity(name = "soba")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM soba o")
        })

public class Soba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sobaId;

    private String imeSobe;

    private Integer velikost;

    private Integer stLjudi;

    private Integer maxLjudi;

    @OneToMany //soba ima lahko več vrat
    private List<Vrata> vrata;

    public Integer getSobaId() {
        return sobaId;
    }

    public void setSobaId(Integer sobaId) {
        this.sobaId = sobaId;
    }

    public String getImeSobe() {
        return imeSobe;
    }

    public void setImeSobe(String imeSobe) {
        this.imeSobe = imeSobe;
    }

    public Integer getVelikost() {
        return velikost;
    }

    public void setVelikost(Integer velikost) {
        this.velikost = velikost;
    }

    public Integer getStLjudi() {
        return stLjudi;
    }

    public void setStLjudi(Integer stLjudi) {
        this.stLjudi = stLjudi;
    }

    public Integer getMaxLjudi() {
        return maxLjudi;
    }

    public void setMaxLjudi(Integer maxLjudi) {
        this.maxLjudi = maxLjudi;
    }

    public List<Vrata> getVrata() {
        return vrata;
    }

    public void setVrata(List<Vrata> vrata) {
        this.vrata = vrata;
    }
}

