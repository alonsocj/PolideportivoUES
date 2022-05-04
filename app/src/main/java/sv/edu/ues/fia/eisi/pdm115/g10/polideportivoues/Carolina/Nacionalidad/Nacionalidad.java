package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad;

public class Nacionalidad {
    String codNac,nacionalidad;

    public Nacionalidad() {
    }

    public Nacionalidad(String codNac, String nacionalidad) {
        this.codNac = codNac;
        this.nacionalidad = nacionalidad;
    }

    public String getCodNac() {
        return codNac;
    }

    public void setCodNac(String codNac) {
        this.codNac = codNac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
