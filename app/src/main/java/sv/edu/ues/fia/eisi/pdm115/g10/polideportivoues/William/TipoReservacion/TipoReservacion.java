package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

public class TipoReservacion {
    private String idTipo;
    private String nomTipoR;

    public TipoReservacion() {
    }

    public TipoReservacion(String idTipo, String nomTipoR) {
        this.idTipo = idTipo;
        this.nomTipoR = nomTipoR;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomTipoR() {
        return nomTipoR;
    }

    public void setNomTipoR(String nomTipoR) {
        this.nomTipoR = nomTipoR;
    }
}
