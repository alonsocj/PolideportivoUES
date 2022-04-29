package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

public class TipoPago {

    private String idPago;
    private String tipo;

    public TipoPago() {
    }

    public TipoPago(String idPago, String tipo) {
        this.idPago = idPago;
        this.tipo = tipo;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
