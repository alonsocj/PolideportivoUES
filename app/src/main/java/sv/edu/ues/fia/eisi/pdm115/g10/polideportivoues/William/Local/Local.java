package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

public class Local {
    private String idLocal;
    private String nomLocal;
    private int cantidadPersonas;

    public Local() {
    }

    public Local(String idLocal, String nomLocal, int cantidadPersonas) {
        this.idLocal = idLocal;
        this.nomLocal = nomLocal;
        this.cantidadPersonas = cantidadPersonas;
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

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cupo) {
        this.cantidadPersonas = cupo;
    }
}
