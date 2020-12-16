package si.fri.prpo.skupina27.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.*;

@Entity(name = "soba")

@NamedQueries(value =
        {
                @NamedQuery(name = "Soba.getAll", query = "SELECT s FROM soba s"),
                @NamedQuery(name = "Soba.getName",
                        query = "SELECT s.imeSobe FROM soba s WHERE s.imeSobe = :soba"),
                @NamedQuery(name = "Soba.getSteviloLjudi",
                        query = "SELECT s.stLjudi FROM soba s WHERE s.imeSobe = :soba"),
                @NamedQuery(name = "Soba.getOsebje",
                        query = "SELECT s.osebe FROM soba s WHERE s.sobaId = :sobaId")
        })

public class Soba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sobaId;

    @Column(name = "ime_sobe")
    private String imeSobe;

    private Integer velikost;

    @Column(name = "st_ljudi")
    private Integer stLjudi;

    @Column(name = "max_ljudi")
    private Integer maxLjudi;

    @JsonbTransient
    @OneToMany(mappedBy = "soba", cascade = CascadeType.ALL)
    private List<Vrata> vrata;

    @ManyToOne
    @JoinColumn(name = "osebe_sobe")
    private Oseba osebe;

    // GETTERJI IN SETTERJI

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

    public List<Vrata> getVrata() { return vrata; }

    public void setVrata(List<Vrata> vrata) { this.vrata = vrata; }

    public Oseba getOsebe() { return osebe; }

    public void setOsebe(Oseba osebe) { this.osebe = osebe; }

    public String toString() {
        return String.format("id: %d, ime: %s in max ljudi: %d", this.sobaId, this.imeSobe, this.maxLjudi);
    }
}