package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

public class Cobro {
    int idCobro;
    String idPago;
    int cantPersonas;
    String duracion;
    float precio;

    public Cobro(){
    }
    public Cobro(int idCobro, String idPago, int cantPersonas, String duracion, float precio){
        this.idCobro = idCobro;
        this.idPago = idPago;
        this.cantPersonas = cantPersonas;
        this.duracion = duracion;
        this.precio = precio;
    }

    public int getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
