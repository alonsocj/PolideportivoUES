package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPago;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

public class ControlBDG10 {

    /*Tabla Hora*/
    private static final String[] camposHora = new String[]{"idhora","horaInicio","horaFin"};
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

    public String actualizarTipoPago(TipoPago tipoEvento){
        return "";
    }
    public TipoPago consultarTipoPago(String tipoEvento){
        TipoPago tipoPago=new TipoPago();
        return tipoPago;
    }
    public String eliminarTipoPago(TipoPago tipoEvento){
        return "";
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
}
