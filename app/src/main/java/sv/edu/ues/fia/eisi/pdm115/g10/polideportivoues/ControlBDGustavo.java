package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
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
        String regInsertados="Registros Insertados Nº= ";
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
            regInsertados="Registro duplicado.";
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
        String registros = "Registros Eliminados Nº= ";
        int contador = 0;

        if(verificarIntegridadDeDatos(persona,1)){
            contador+=db.delete("persona","idPersona='"+persona.getIdPersona()+"'",null);
            registros = registros + contador;
            return registros;
        }else{
            registros="Registro no existe!";
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
            return "Registo actualizado correctamente!";
        }else{
            return "Registro no existe!";
        }
    }

    /* Funcionalidades de horarios disponibles*/

    public String insertarHorarioDisponible (HorariosDisponibles horariosDisponibles){
        String regInsertados="Registros Insertados Nº= ";
        long contador=0;
        //if(verificarIntegridadDeDatos(horariosDisponibles,4))
        //{
            ContentValues horario = new ContentValues();
            horario.put("idHorario", horariosDisponibles.getIdHorario());
            horario.put("idHora", horariosDisponibles.getHora());
            horario.put("nombreDia", horariosDisponibles.getDia());
            contador=db.insert("horariosDisponibles", null, horario);
        //}
        if(contador==-1 || contador==0)
        {
            regInsertados= "Registro duplicado!";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public HorariosDisponibles consultarHorarioDisponible (String idHorario){
        String[] id = {idHorario};
        Cursor cursor = db.query("horariosDisponibles",new String []{"idHorario","idHora", "nombreDia"}, "idHorario = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            HorariosDisponibles horariosDisponibles = new HorariosDisponibles();
            horariosDisponibles.setIdHorario(cursor.getString(0));
            horariosDisponibles.setHora(cursor.getString(1));
            horariosDisponibles.setDia(cursor.getString(2));
            return horariosDisponibles;
        }else {
            return null;
        }
    }
    public String eliminarHorarioDisponible (HorariosDisponibles horariosDisponibles){
        String registros = "Registros Eliminados Nº= ";
        int contador = 0;

        if(verificarIntegridadDeDatos(horariosDisponibles,3)){
            contador+=db.delete("horariosDisponibles","idHorario='"+horariosDisponibles.getIdHorario()+"'",null);
            registros = registros + contador;
            return registros;
        }else{
            registros="Registro no existe!";
            return registros;
        }
    }
    public String actualizarHorarioDisponible (HorariosDisponibles horariosDisponibles){
        if (verificarIntegridadDeDatos(horariosDisponibles,3)) {
            String[] id = {horariosDisponibles.getIdHorario()};
            ContentValues values = new ContentValues();
            values.put("idHorario", horariosDisponibles.getIdHorario());
            values.put("idHora", horariosDisponibles.getHora());
            values.put("nombreDia", horariosDisponibles.getDia());
            db.update("horariosDisponibles", values, "idHorario=?", id);
            return "Registo actualizado correctamente!";
        }else{
            return "Registro no existe!";
        }
    }

    private boolean verificarIntegridadDeDatos(Object valor, int relacion) throws SQLException{
        switch (relacion){
            //Verificar si existe la persona
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

            //Verificar si existe la disponibilidad de horario
            case 3: {
                HorariosDisponibles horariosDisponibles = (HorariosDisponibles) valor;
                String[]id = {horariosDisponibles.getIdHorario()};
                open();
                Cursor cursor = db.query("horariosDisponibles",null,"idHorario = ?", id,null,null,null);
                if (cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            }
            //Verificar si existe el horario y el día
            case 4: {
                HorariosDisponibles horariosDisponibles= (HorariosDisponibles) valor;
                String [] idHora={horariosDisponibles.getHora()};
                String [] idDia={horariosDisponibles.getDia()};
                Cursor cursor1 = db.query("hora", null, "idHora = ?", idHora, null, null, null);
                Cursor cursor2 = db.query("dia", null, "nombreDia = ?", idDia,null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }
}
