package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPago;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

public class ControlBDG10William {
    private final DatabaseHelper DBhelper; /*Esta es la clase que contiene todas las instrucciones SQL*/
    private SQLiteDatabase db;

    public ControlBDG10William(Context ctx) {
        DBhelper = new DatabaseHelper(ctx);
    }

    public void open() throws SQLException {
        db = DBhelper.getWritableDatabase();
        return;
    }
    public void close() {
        DBhelper.close();
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
            localInsertado = "Error al ingresar un local con id que ya existe, Verificar su inserción";
        }else{
            localInsertado = localInsertado + cuenta + " Registrado";
        }

        return localInsertado;
    }

    public String actualizarLocal(Local local){
        if(verificarIntegridadDeDatos(local,1)){
            String[] id={local.getIdLocal()};
            ContentValues contentValues = new ContentValues();
            contentValues.put("nomLocal", local.getNomLocal());
            contentValues.put("cupo",local.getCupo());
            db.update("local",contentValues, "idLocal = ?",id);
            return "Local Actualizado";
        }else{
            return "El local no existe";
        }
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
        if (verificarIntegridadDeDatos(local,1)) {
            //if (verificarIntegridadDeDatos(local,2)) {
            //return "El local no puede ser eliminado porque existen registros de local evento con este local.";
            //}else{
            contador+=db.delete("local", "idLocal='"+local.getIdLocal()+"'", null);
            regAfectados+=contador;
            return regAfectados;
            //}
        }else{
            return "El local no existe";
        }
    }

    //Metodos para tabla Reservacion
    public String ingresarTipoReservacion(TipoReservacion tipoReservacion){
        String TipoReservacionInsertado = "Tipo de reservacion ";
        long cuenta = 0;

        ContentValues tipoR = new ContentValues();
        tipoR.put("idTipoR", tipoReservacion.getIdTipoR());
        tipoR.put("nomTipoR", tipoReservacion.getNomTipoR());
        cuenta = db.insert("tipoReservacion",null,tipoR);

        if(cuenta == -1 || cuenta == 0){
            TipoReservacionInsertado = "Error al ingresar un tipo de reservación con id que ya existe, Verificar su inserción";
        }else{
            TipoReservacionInsertado = TipoReservacionInsertado + cuenta + " Registrado";
        }

        return TipoReservacionInsertado;
    }

    public String actualizarTipoReservacion(TipoReservacion tipoReservacion){
        //if(verificarIntegridadDeDatos(tipoReservacion,2)){
        String[] id={tipoReservacion.getIdTipoR()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomTipoR", tipoReservacion.getNomTipoR());
        db.update("tipoReservacion",contentValues, "idTipoR = ?",id);
        return "Tipo de reservacion Actualizado";
        //}else{
        //return "El Tipo de reservacion no existe";
        //}
    }

    public TipoReservacion consultarTipoReservacion(String idTipoR){
        String[] id = {idTipoR};
        Cursor cursor = db.query("tipoReservacion", new String [] {"idTipoR","nomTipoR"}, "idTipoR = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoReservacion tipoReservacion = new TipoReservacion();
            tipoReservacion.setIdTipoR(cursor.getString(0));
            tipoReservacion.setNomTipoR(cursor.getString(1));
            return tipoReservacion;
        }else{
            return null;
        }
    }

    public String eliminarTipoReservacion(TipoReservacion tipoReservacion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridadDeDatos(tipoReservacion,2)) {
        //if (verificarIntegridadDeDatos(tipoReservacion,3)) {
        //return "El tipo de reservacion no puede ser eliminado porque existen registros de reservacion con este tipo.";
        //}else{
        contador+=db.delete("tipoReservacion", "idTipoR='"+tipoReservacion.getIdTipoR()+"'", null);
        regAfectados+=contador;
        return regAfectados;
        //}
        //}else{
        //return "El tipo de reservacion no existe";
        //}

    }
    private boolean verificarIntegridadDeDatos(Object valor, int relacion) throws SQLException{
        switch (relacion){
            case 1: {
                //Verifica si existe el local a actualizar
                Local localV = (Local) valor;
                String[]id = {localV.getIdLocal()};
                open();
                Cursor cursor = db.query("local",null,"idLocal = ?", id,null,null,null);
                if (cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            }
            case 2:{
                Local local = (Local) valor;
                Cursor c=db.query(true, "localEvento", new String[] {"idLocal" }, "idLocal='"+local.getIdLocal()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            default:
                return false;
        }
    }
}
