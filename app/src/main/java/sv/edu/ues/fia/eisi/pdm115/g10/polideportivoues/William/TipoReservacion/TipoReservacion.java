package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

public class TipoReservacion {
    private String idTipoR;
    private String nomTipoR;

    public TipoReservacion() {
    }

    public TipoReservacion(String idTipoR, String nomTipoR) {
        this.idTipoR = idTipoR;
        this.nomTipoR = nomTipoR;
    }

    public String getIdTipoR() {
        return idTipoR;
    }

    public void setIdTipoR(String idTipoR) {
        this.idTipoR = idTipoR;
    }

    public String getNomTipoR() {
        return nomTipoR;
    }

    public void setNomTipoR(String nomTipoR) {
        this.nomTipoR = nomTipoR;
    }
}
