package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora;

public class Hora {
    private String idHora;
    private String horaInicio;
    private String horaFin;

    public Hora(){

    }

    public Hora(String idHora, String horaInicio, String horaFin) {
        this.idHora = idHora;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getIdHora() {
        return idHora;
    }

    public void setIdHora(String idHora) {
        this.idHora = idHora;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
