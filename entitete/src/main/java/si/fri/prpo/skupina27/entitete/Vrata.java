package si.fri.prpo.skupina27.entitete;
import javax.persistence.*;

@Entity(name = "vrata")
@NamedQueries(value =
        {
                @NamedQuery(name = "Opomnik.getAll", query = "SELECT o FROM vrata o")
        })

public class Vrata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vrataId;

    @ManyToOne
    private Soba sobaId;

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
}