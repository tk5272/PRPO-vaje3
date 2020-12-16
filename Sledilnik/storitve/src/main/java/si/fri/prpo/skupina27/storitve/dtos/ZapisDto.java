package si.fri.prpo.skupina27.storitve.dtos;

import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Soba;

public class ZapisDto {
    Oseba oseba;
    Soba soba;

    public ZapisDto(Oseba oseba, Soba soba) {
        this.oseba = oseba;
        this.soba = soba;
    }

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
}
