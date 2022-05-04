package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero;

public class Genero {
    String idGenero;
    String genero;

    public Genero() {
    }
    public Genero(String idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
