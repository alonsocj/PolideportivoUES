package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales;

public class HorariosLocales {
    private String idHorario;
    private String idLocal;
    private int disponibilidad;

    public HorariosLocales() {
    }

    public HorariosLocales(String idHorario, String idLocal, int disponibilidad) {
        this.idHorario = idHorario;
        this.idLocal = idLocal;
        this.disponibilidad = disponibilidad;
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

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
