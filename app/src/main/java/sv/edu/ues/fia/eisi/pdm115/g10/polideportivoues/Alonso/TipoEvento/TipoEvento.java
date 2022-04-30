package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

public class TipoEvento {
    String idTipoE;
    String nombreTipoE;

    public TipoEvento() {
    }
    public TipoEvento(String idTipoE, String nombreTipoE) {
        this.idTipoE = idTipoE;
        this.nombreTipoE = nombreTipoE;
    }

    public String getIdTipoE() {
        return idTipoE;
    }

    public void setIdTipoE(String idTipoE) {
        this.idTipoE = idTipoE;
    }

    public String getNombreTipoE() {
        return nombreTipoE;
    }

    public void setNombreTipoE(String nombreTipoE) {
        this.nombreTipoE = nombreTipoE;
    }
}
