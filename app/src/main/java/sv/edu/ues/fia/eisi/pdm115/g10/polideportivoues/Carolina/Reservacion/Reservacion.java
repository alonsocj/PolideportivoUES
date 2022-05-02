package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion;

import android.provider.ContactsContract;

import java.util.Date;

public class Reservacion {
    private String idReservacion, idCobro, idPersona, idTipoR,idEvento, idPeriodoReserva;
    private String fechaRegistro;

    public Reservacion() {
    }

    public Reservacion(String idReservacion, String idCobro, String idPersona, String idTipoR, String idEvento, String idPeriodoReserva, String fechaRegistro) {
        this.idReservacion = idReservacion;
        this.idCobro = idCobro;
        this.idPersona = idPersona;
        this.idTipoR = idTipoR;
        this.idEvento = idEvento;
        this.idPeriodoReserva = idPeriodoReserva;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(String idReservacion) {
        this.idReservacion = idReservacion;
    }

    public String getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(String idCobro) {
        this.idCobro = idCobro;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdTipoR() {
        return idTipoR;
    }

    public void setIdTipoR(String idTipoR) {
        this.idTipoR = idTipoR;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdPeriodoReserva() {
        return idPeriodoReserva;
    }

    public void setIdPeriodoReserva(String idPeriodoReserva) {
        this.idPeriodoReserva = idPeriodoReserva;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
