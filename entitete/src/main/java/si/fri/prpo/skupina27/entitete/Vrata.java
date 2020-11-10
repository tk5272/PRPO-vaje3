package si.fri.prpo.skupina27.entitete;
import javax.persistence.*;

@Entity(name = "vrata")
@NamedQueries(value =
        {
                @NamedQuery(name = "Vrata.getAll", query = "SELECT v FROM vrata v"),
                @NamedQuery(name = "Vrata.getSoba",
                        query = "SELECT v.sobaId FROM vrata v WHERE v.vrataId = :vrataId"),
                @NamedQuery(name = "Vrata.getVratatoSoba",
                        query = "SELECT v.vrataId FROM vrata v WHERE v.sobaId = :sobaId"),
        })

public class Vrata {


    // DEFINIRAJ PODATKE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vrataId;

    @JoinColumn(name = "soba_id")
    @ManyToOne
    private Soba sobaId;


    // GETTERJI IN SETTERJI

    public Integer getVrataId() {
        return vrataId;
    }

    public void setVrataId(Integer vrataId) {
        this.vrataId = vrataId;
    }

    public Soba getSobaId() {
        return sobaId;
    }

    public void setSobaId(Soba sobaId) {
        this.sobaId = sobaId;
    }

    public String toString() {
        return String.format("vrata %d v sobi %d", this.vrataId, this.sobaId.getSobaId());
    }
}
