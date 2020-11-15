package si.fri.prpo.skupina27.storitve.dtos;

import si.fri.prpo.skupina27.entitete.Oseba;

import java.util.List;

public class SeznamOsebjaDto {
    private int ura;
    private List<Oseba> osebje;

    public int getUra() {
        return ura;
    }

    public void setUra(int ura) {
        this.ura = ura;
    }

    public List<Oseba> getOsebje() {
        return osebje;
    }

    public void setOsebje(List<Oseba> osebje) {
        this.osebje = osebje;
    }
}
