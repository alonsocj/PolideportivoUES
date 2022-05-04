package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

public class Persona {
    String idPersona;
    String nombre;
    String apellido;
    String genero;
    String nacimiento;
    String nacionalidad;
    String direccion;
    String email;
    String telefono;

    public Persona() {
    }

    public Persona(String idPersona, String nombre, String apellido, String genero, String nacimiento, String nacionalidad, String direccion, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.nacimiento = nacimiento;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
