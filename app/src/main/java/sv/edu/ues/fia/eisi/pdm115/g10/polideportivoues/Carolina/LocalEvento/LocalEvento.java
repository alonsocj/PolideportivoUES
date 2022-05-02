package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.LocalEvento;

public class LocalEvento {
    private String idLocalEvento, idEvento, idLocal;
    private Integer cantAutorizada;

    public LocalEvento() {
    }

    public LocalEvento(String idLocalEvento, String idEvento, String idLocal, Integer cantAutorizada) {
        this.idLocalEvento = idLocalEvento;
        this.idEvento = idEvento;
        this.idLocal = idLocal;
        this.cantAutorizada = cantAutorizada;
    }

    public String getIdLocalEvento() {
        return idLocalEvento;
    }

    public void setIdLocalEvento(String idLocalEvento) {
        this.idLocalEvento = idLocalEvento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getCantAutorizada() {
        return cantAutorizada;
    }

    public void setCantAutorizada(Integer cantAutorizada) {
        this.cantAutorizada = cantAutorizada;
    }
}
