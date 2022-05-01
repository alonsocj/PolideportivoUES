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
    /*Tabla Hora*/
    private static final String[] camposHora = new String[]{"idhora","horaInicio","horaFin"};
    private static final String[] camposTipoEvento = new String[]{"idTipoE","nomTipoE"};

    private final DatabaseHelper DBhelper; /*Esta es la clase que contiene todas las instrucciones SQL*/
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


    private boolean verificarIntegridadDeDatos(Object valor, int relacion) throws SQLException{
        switch (relacion){
            //Verifica si existe la hora
            case 1: {
                Hora horaV = (Hora) valor;
                String[]id = {horaV.getIdHora()};
                open();
                Cursor cursor = db.query("hora",null,"idHora = ?", id,null,null,null);
                if (cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            }

            /*2 y 3 los tomo williamn*/

            //obtener la hora a eliminar
            case 4:{
                Hora hora = (Hora) valor;
                Cursor cursor = db.query(true,"hora", new String[]{"idHora"},"idHora='"+hora.getIdHora()+"'",null,null,null,null,null);
                if(cursor.moveToFirst()){
                    return true;
                }else {
                    return false;
                }

            }

            //Verifica que existe el tipo evento
            case 5: {
                TipoEvento tipoEventoV = (TipoEvento) valor;
                String[] id = {tipoEventoV.getIdTipoE()};
                open();
                Cursor tev = db.query("tipoevento",camposTipoEvento,"idTipoE = ?", id,null,null,null);
                return tev.moveToFirst();
            }
            default:
                return false;
        }
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
        contador = db.insert("tipoevento",null,values);
        if(contador==-1 || contador==0){
            regInsertados="Error al Insertar el registro!, Registro Duplicado.";
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
    public String eliminarPersona (String persona){
        return "";
    }
    public String actualizarPersona (String persona){
        return "";
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


    public String llenarBDG10(){
        //Metodo para llenar la base de datos con sentencias SQL
        open();
        db.execSQL("DELETE FROM dia");
        db.execSQL("DELETE FROM tipoevento");
        db.execSQL("DELETE FROM tipopago");
        db.execSQL("DELETE FROM cobro");
        /* db.execSQL("DELETE FROM hora");*/

        //Se llenan las tablas con datos

        close();

        return "Llenado correctamente";
    }
}
