package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

public class Local {
    private String idLocal;
    private String nomLocal;
    private int cupo;

    public Local() {
    }

    public Local(String idLocal, String nomLocal, int cupo) {
        this.idLocal = idLocal;
        this.nomLocal = nomLocal;
        this.cupo = cupo;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
}
