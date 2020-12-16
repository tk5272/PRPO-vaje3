package si.fri.prpo.skupina27.entitete;
import javax.persistence.*;

@Entity(name = "vrata")
@NamedQueries(value =
        {
                @NamedQuery(name = "Vrata.getAll", query = "SELECT v FROM vrata v"),
                @NamedQuery(name = "Vrata.getSoba",
                        query = "SELECT v.soba FROM vrata v WHERE v.vrataId = :vrataId"),
                @NamedQuery(name = "Vrata.getVratatoSoba",
                        query = "SELECT v.vrataId FROM vrata v WHERE v.soba = :sobaId"),
        })

public class Vrata {


    // DEFINIRAJ PODATKE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vrataId;

    @ManyToOne
    @JoinColumn(name = "vrata_sobe")
    private Soba soba;


    // GETTERJI IN SETTERJI

    public Integer getVrataId() {
        return vrataId;
    }

    public void setVrataId(Integer vrataId) {
        this.vrataId = vrataId;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSobaId(Soba soba) {
        this.soba = soba;
    }

    public String toString() {
        return String.format("vrata %d v sobi %d", this.vrataId, this.soba.getSobaId());
    }
}
