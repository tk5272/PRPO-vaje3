package si.fri.prpo.skupina27.entitete;

public class Zapis {
    Oseba oseba;
    Soba soba;

    public Oseba getOseba() {
        return oseba;
    }

    public void setOseba(Oseba oseba) {
        this.oseba = oseba;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    public String toString() {
        return String.format(oseba + " /// " + soba);
    }

}
