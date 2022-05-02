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
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

public class ControlBDG10 {


   private static final String[] camposTipoEvento = new String[]{"idTipoE","nomTipoE"};

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
        String registrosAfec = "Horas eliminadas = ";
        int cuenta = 0;
        if(verificarIntegridadDeDatos(hora,4)){
            cuenta +=db.delete("hora","idHora='"+hora.getIdHora()+"'",null);
        }
        cuenta+=db.delete("hora","idHora='"+hora.getIdHora()+"'",null);
        registrosAfec = registrosAfec + cuenta;
        return registrosAfec;
    }


    /*Funcionalidades de TipoPago*/

    public String insertarTipoPago (TipoPago tipoPago){
        String pagosInsertados = "Pago Insertado";
        long contador = 0;

        ContentValues contentpago = new ContentValues();
        contentpago.put("idPago",tipoPago.getIdPago());
        contentpago.put("tipo",tipoPago.getTipo());
        contador = db.insert("tipopago",null,contentpago);

        if(contador==-1 || contador == 0){
            pagosInsertados = "Error al insertar el pago";
        }else{
            pagosInsertados = pagosInsertados + contador;
        }

        return pagosInsertados;
    }


    public TipoPago consultarTipoPago (String tipoPago){
        String[] id = {tipoPago};
        Cursor cursor = db.query("tipopago", camposTipoPago, "idPago = ?",id,null,null,null,null);
        if(cursor.moveToFirst()){
            TipoPago tPago = new TipoPago();
            tPago.setIdPago(cursor.getString(0));
            tPago.setTipo(cursor.getString(1));
            return tPago;
        }else{
            return null;
        }
    }

    public String actualizarTipoPago (TipoPago tipoPago){
        if(verificarIntegridadDeDatos(tipoPago,6)){
            String[] id = {tipoPago.getIdPago()};
            ContentValues contentValues =  new ContentValues();
            contentValues.put("tipo", tipoPago.getTipo());
            db.update("tipopago", contentValues, "idPago = ?", id);
            return "Tipo de pago actualizado correctamente";
        }else{
            return "Tipo de pago inexistente";
        }
    }

    public String eliminarTipoPago (TipoPago tipoPago){
        String tiposdepagosafectados = "Tipo de pago eliminados = ";
        int cuenta = 0;
        if(verificarIntegridadDeDatos(tipoPago,7)){
            cuenta+=db.delete("tipopago","idPago='"+tipoPago.getIdPago()+"'",null);
        }
        cuenta+=db.delete("tipopago","idPago='"+tipoPago.getIdPago()+"'",null);
        tiposdepagosafectados+=cuenta;
        return tiposdepagosafectados;
    }

    /*
     * Inicio de funcionalidades de TIPO EVENTO
     */

    public String insertar(TipoEvento tipoEvento){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues values = new ContentValues();
        values.put("idTipoE", tipoEvento.getIdTipoE());
        values.put("nomTipoE", tipoEvento.getNombreTipoE());
        contador = db.insert("tipoevento",null,values);
        if(contador==-1 || contador==0){
            regInsertados="Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public String actualizar(TipoEvento tipoEvento){
        if (verificarIntegridadDeDatos(tipoEvento,5)) {
            String[] id = {tipoEvento.getIdTipoE()};
            ContentValues values = new ContentValues();
            values.put("nomTipoE", tipoEvento.getNombreTipoE());
            db.update("tipoevento", values, "idTipoE=?", id);
            return "Registro de Tipo Evento actualizado correctamente";
        }else{
            return "Registro de Tipo Evento inexistente";
        }
    }

    public String eliminar(TipoEvento tipoEvento) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where = "idTipoE = '"+tipoEvento.getIdTipoE()+"'";
        contador+=db.delete("tipoevento", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public TipoEvento consultar(String idTipoE){
        String[] id = {idTipoE};
        Cursor cursor = db.query("tipoevento", camposTipoEvento, "idTipoE = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoE(cursor.getString(0));
            tipoEvento.setNombreTipoE(cursor.getString(1));
            return tipoEvento;
        }else {
            return null;
        }
    }

    /*
     * FINAL de las funcionalidades de TIPO EVENTO
     */



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
            //Verifica que existe el tipo evento
            case 5: {
                TipoEvento tipoEventoV = (TipoEvento) valor;
                String[] id = {tipoEventoV.getIdTipoE()};
                open();
                Cursor tev = db.query("tipoevento",camposTipoEvento,"idTipoE = ?", id,null,null,null);
                return tev.moveToFirst();
            } case 8:{
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
        open();

        /*db.execSQL("DELETE FROM dia");*/
        /*db.execSQL("DELETE FROM tipoevento");*/
        /*db.execSQL("DELETE FROM tipopago");*/
        /*db.execSQL("DELETE FROM cobro");*/
       /* db.execSQL("DELETE FROM hora");*/


        //Se llenan las tablas con datos

        close();

        return "Llenado correctamente";
    }
}
