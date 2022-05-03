package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPago;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

public class ControlBDG10 {

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



    private boolean verificarIntegridadDeDatos(Object valor, int relacion) throws SQLException{
        switch (relacion){
            case 2: {
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
            case 3:{
                Local local = (Local) valor;
                Cursor c=db.query(true, "localEvento", new String[] {"idLocal" }, "idLocal='"+local.getIdLocal()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 8:{
                //Verificar que al insertar el Evento exista el tipoDeEvento
                Evento evento = (Evento)valor;
                String[] idTipoEvento = {evento.getIdTipoE()};
                Cursor cursor = db.query("tipoevento", null, "idTipoE = ?",idTipoEvento,null,null,null);
                if(cursor.moveToFirst()){
                    //Se encontraron datos de tipoEvento
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
        final String[] VADias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        open();

        db.execSQL("DELETE FROM dia");
        /*db.execSQL("DELETE FROM tipoevento");*/
        /*db.execSQL("DELETE FROM tipopago");*/
        /*db.execSQL("DELETE FROM cobro");*/
       /* db.execSQL("DELETE FROM hora");*/


        //Se llenan las tablas con datos

        for (String vaDia : VADias) {
            db.execSQL("INSERT INTO dia (nombreDia) VALUES ('"+vaDia+"')");
        }
        close();

        return "Llenado correctamente";
    }
}
