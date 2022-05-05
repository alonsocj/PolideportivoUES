package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion;

import android.provider.ContactsContract;

import java.util.Date;

public class Reservacion {
    private String idReservacion, idPersona, idTipoR,idEvento, idPeriodoReserva,idHorario,idLocal;
    private Integer idCobro;
    private String fechaRegistro;

    public Reservacion() {
    }

    public Reservacion(String idReservacion, String idPersona, String idTipoR, String idEvento, String idPeriodoReserva, String idHorario, String idLocal, Integer idCobro, String fechaRegistro) {
        this.idReservacion = idReservacion;
        this.idPersona = idPersona;
        this.idTipoR = idTipoR;
        this.idEvento = idEvento;
        this.idPeriodoReserva = idPeriodoReserva;
        this.idHorario = idHorario;
        this.idLocal = idLocal;
        this.idCobro = idCobro;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(String idReservacion) {
        this.idReservacion = idReservacion;
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

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Integer idCobro) {
        this.idCobro = idCobro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
