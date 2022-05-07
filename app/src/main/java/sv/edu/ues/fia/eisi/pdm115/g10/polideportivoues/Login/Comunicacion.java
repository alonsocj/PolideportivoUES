package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login;

public interface Comunicacion {
    void toggleProgressBar(boolean status);
    void lanzarActividad(Class<?> tipoActividad);
    void showMessage(String message);
}
