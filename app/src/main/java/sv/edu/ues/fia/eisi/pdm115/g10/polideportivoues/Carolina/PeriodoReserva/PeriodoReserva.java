package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva;

import java.util.Date;

public class PeriodoReserva {
    private String idPeriodoReserva;
    private String fechaInicio;
    private String fechaFin;

    public PeriodoReserva() {
    }

    public PeriodoReserva(String idPeriodoReserva, String fechaInicio, String fechaFin) {
        this.idPeriodoReserva = idPeriodoReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getIdPeriodoReserva() {
        return idPeriodoReserva;
    }

    public void setIdPeriodoReserva(String idPeriodoReserva) {
        this.idPeriodoReserva = idPeriodoReserva;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
