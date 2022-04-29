package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

public class Evento {

    private String idEvento;
    private String idTipoE;
    private String nomEvento;
    private double costoEvento;

    public Evento() {
    }

    public Evento(String idEvento, String idTipoE, String nomEvento, double costoEvento) {
        this.idEvento = idEvento;
        this.idTipoE = idTipoE;
        this.nomEvento = nomEvento;
        this.costoEvento = costoEvento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdTipoE() {
        return idTipoE;
    }

    public void setIdTipoE(String idTipoE) {
        this.idTipoE = idTipoE;
    }

    public String getNomEvento() {
        return nomEvento;
    }

    public void setNomEvento(String nomEvento) {
        this.nomEvento = nomEvento;
    }

    public double getCostoEvento() {
        return costoEvento;
    }

    public void setCostoEvento(double costoEvento) {
        this.costoEvento = costoEvento;
    }
}
