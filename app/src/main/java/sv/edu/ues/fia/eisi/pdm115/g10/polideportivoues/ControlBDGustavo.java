package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;


public class ControlBDGustavo {

    private final DatabaseHelper DBhelper;
    private SQLiteDatabase db;

    public ControlBDGustavo(Context ctx) {
        DBhelper = new DatabaseHelper(ctx);
    }

    public void open() throws SQLException {
        db = DBhelper.getWritableDatabase();
        return;
    }

    public void close() {
        DBhelper.close();
    }

    /* Funcionalidades de persona*/

    public String insertarPersona (Persona persona){
        String regInsertados="Persona Agregada Nº= ";
        long contador=0;

        ContentValues values = new ContentValues();
        values.put("idPersona", persona.getIdPersona());
        values.put("nombre", persona.getNombre());
        values.put("apellido", persona.getApellido());
        values.put("genero", persona.getGenero());
        values.put("nacimiento", persona.getNacimiento());
        values.put("direccion", persona.getDireccion());
        values.put("email", persona.getEmail());
        values.put("telefono", persona.getTelefono());
        contador = db.insert("persona",null,values);

        if(contador==-1 || contador==0){
            regInsertados="Registro de persona duplicado.";
        }else {
            regInsertados=regInsertados+" "+contador;
        }
        return regInsertados;
    }

    public Persona consultarPersona (String idPersona){
        String[] id = {idPersona};
        Cursor cursor = db.query("persona",new String []{"idPersona","nombre", "apellido", "genero", "nacimiento", "direccion", "email", "telefono"}, "idPersona = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Persona persona = new Persona();
            persona.setIdPersona(cursor.getString(0));
            persona.setNombre(cursor.getString(1));
            persona.setApellido(cursor.getString(2));
            persona.setGenero(cursor.getString(3));
            persona.setNacimiento(cursor.getString(4));
            persona.setDireccion(cursor.getString(5));
            persona.setEmail(cursor.getString(6));
            persona.setTelefono(cursor.getString(7));
            return persona;
        }else {
            return null;
        }
    }

    public String eliminarPersona (Persona persona){
        String registros = "Personas eliminadas Nº= ";
        int contador = 0;

        if(verificarIntegridadDeDatos(persona,1)){
            contador+=db.delete("persona","idPersona='"+persona.getIdPersona()+"'",null);
            registros = registros + contador;
            return registros;
        }else{
            registros="Registro de persona no existe!";
            return registros;
        }
    }

    public String actualizarPersona (Persona persona){
        if (verificarIntegridadDeDatos(persona,1)) {
            String[] id = {persona.getIdPersona()};
            ContentValues values = new ContentValues();
            values.put("nombre", persona.getNombre());
            values.put("apellido", persona.getApellido());
            values.put("genero", persona.getGenero());
            values.put("nacimiento", persona.getNacimiento());
            values.put("direccion", persona.getDireccion());
            values.put("email", persona.getEmail());
            values.put("telefono", persona.getTelefono());
            db.update("persona", values, "idPersona=?", id);
            return "Registo de persona actualizado correctamente!";
        }else{
            return "Registro de persona no existe!";
        }
    }

    /* Funcionalidades de horarios disponibles*/

    public String insertarHorarioDisponible (String persona){
        return "";
    }
    public String consultarHorarioDisponible (String idPersona){
        return "";
    }
    public String eliminarHorarioDisponible (String persona){
        return "";
    }
    public String actualizarHorarioDisponible (String persona){
        return "";
    }

    private boolean verificarIntegridadDeDatos(Object valor, int relacion) throws SQLException{
        switch (relacion){
            //Verifica si existe la persona
            case 1: {
                Persona persona = (Persona) valor;
                String[]id = {persona.getIdPersona()};
                open();
                Cursor cursor = db.query("persona",null,"idPersona = ?", id,null,null,null);
                if (cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            }

            //Obtener la persona a eliminar
            case 2:{
                Persona persona = (Persona) valor;
                Cursor cursor = db.query(true,"persona", new String[]{"idPersona"},"idPersona='"+persona.getIdPersona()+"'",null,null,null,null,null);
                if(cursor.moveToFirst()){
                    return true;
                }else {
                    return false;
                }

            }
            default:
                return false;
        }
    }
}
