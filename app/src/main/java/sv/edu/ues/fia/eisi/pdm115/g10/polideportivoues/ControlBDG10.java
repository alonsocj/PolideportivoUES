package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

public class ControlBDG10 {

    /*Tabla Hora*/
    private static final String[] camposHora = new String[]{"idhora","horaInicio","horaFin"};

    private final DatabaseHelper DBhelper; /*Esta es la clase que contiene todas las instrucciones SQL*/
    private SQLiteDatabase db;

    public ControlBDG10(Context ctx) {
        DBhelper = new DatabaseHelper(ctx);
    }

    public void open() throws SQLException {
        db = DBhelper.getWritableDatabase();
        return;
    }

    public void close() {
        DBhelper.close();
    }

    /*Funcionalidades de Hora*/

    public String insertarHora(Hora hora){
        String horaInsertada = "Hora ";
        long cuenta = 0;

        ContentValues horas = new ContentValues();
        horas.put("idHora", hora.getIdHora());
        horas.put("horaInicio", hora.getHoraInicio());
        horas.put("horaFin", hora.getHoraFin());
        cuenta = db.insert("hora",null,horas);

        if(cuenta == -1 || cuenta == 0){
            horaInsertada = "Error al ingresar la Hora, Verificar su inserción";
        }else{
            horaInsertada = horaInsertada + cuenta + " Registrada";
        }

        return horaInsertada;

    }

    public Hora ConsultarHora(String hora){
        String[] id = {hora};
        Cursor cursor = db.query("hora",camposHora,"idHora = ?",id,null,null,null);
        if(cursor.moveToFirst()){
            Hora identificadorH = new Hora();
            identificadorH.setIdHora(cursor.getString(0));
            identificadorH.setHoraInicio(cursor.getString(1));
            identificadorH.setHoraFin(cursor.getString(2));
            return identificadorH;
        }else{
            return null;
        }

    }

    public String modificarHora(Hora hora){
        if(verificarIntegridadDeDatos(hora,1)){
            String[] id={hora.getIdHora()};
            ContentValues contentValues = new ContentValues();
            contentValues.put("horaInicio", hora.getHoraInicio());
            contentValues.put("horaFin",hora.getHoraFin());
            db.update("hora",contentValues, "idHora = ?",id);
            return "Hora Actualizada";
        }else{
            return "La hora no existe";
        }
    }

    public String eliminarHora(Hora hora){
        return null;
    }


    //Metodos para tabla local
    public String ingresarLocal(Local local){
        String localInsertado = "Local ";
        long cuenta = 0;

        ContentValues locales = new ContentValues();
        locales.put("idLocal", local.getIdLocal());
        locales.put("nomLocal", local.getNomLocal());
        locales.put("cupo", local.getCupo());
        cuenta = db.insert("local",null,locales);

        if(cuenta == -1 || cuenta == 0){
            localInsertado = "Error al ingresar el local, Verificar su inserción";
        }else{
            localInsertado = localInsertado + cuenta + " Registrado";
        }

        return localInsertado;
    }

    public String actualizarLocal(Local local){
        //if(verificarIntegridadDeDatos(local,2)){
            String[] id={local.getIdLocal()};
            ContentValues contentValues = new ContentValues();
            contentValues.put("nomLocal", local.getNomLocal());
            contentValues.put("cupo",local.getCupo());
            db.update("local",contentValues, "idLocal = ?",id);
            return "Local Actualizado";
        //}else{
            //return "El local no existe";
        //}
    }

    public Local consultarLocal(String idlocal){
        String[] id = {idlocal};
        Cursor cursor = db.query("local", new String [] {"idLocal","nomLocal","cupo"}, "idLocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Local local = new Local();
            local.setIdLocal(cursor.getString(0));
            local.setNomLocal(cursor.getString(1));
            local.setCupo(Integer.parseInt(cursor.getString(2)));
            return local;
        }else{
            return null;
        }
    }

    public String eliminarLocal(Local local){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(alumno,3)) {
            //contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
        //}
        contador+=db.delete("local", "carnet='"+local.getIdLocal()+"'", null);
        regAfectados+=contador;
        return regAfectados;
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
            default:
                return false;
        }
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
