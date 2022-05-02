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

    /*Tabla Hora*/
    private static final String[] camposHora = new String[]{"idhora","horaInicio","horaFin"};
    private static final String[] camposTipoEvento = new String[]{"idTipoE","nomTipoE"};
    private static final String[] camposTipoPago = new String[]{"idTipoP","nomTipoP"};
    private static final String[] camposEvento = new String[]{"idEvento", "idTipoE", "nomEvento", "costoEvento"};


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
        if(verificarIntegridadDeDatos(local,2)){
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
        if (verificarIntegridadDeDatos(local,2)) {
            //if (verificarIntegridadDeDatos(local,3)) {
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

    //Metodos para tabla local
    public String ingresarTipoReservacion(TipoReservacion tipoReservacion){
        String TipoReservacionInsertado = "Tipo de reservacion ";
        long cuenta = 0;

        ContentValues tipoR = new ContentValues();
        tipoR.put("idTipoR", tipoReservacion.getIdTipoR());
        tipoR.put("nomTipoR", tipoReservacion.getNomTipoR());
        cuenta = db.insert("tipoReservacion",null,tipoR);

        if(cuenta == -1 || cuenta == 0){
            TipoReservacionInsertado = "Error al ingresar el tipo de reservacion, Verificar su inserción";
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


    public String agregarEvento(Evento evento){
        String eventosRegistrados =  "Evento N°: ";
        long numeroEventos=0;

       if (verificarIntegridadDeDatos(evento, 8)) {
            ContentValues eventos = new ContentValues();
            eventos.put("idEvento", evento.getIdEvento());
            eventos.put("idTipoE", evento.getIdTipoE()); //Llave Foranea
            eventos.put("nomEvento", evento.getNomEvento());
            eventos.put("costoEvento",evento.getCostoEvento());
            numeroEventos = db.insert("evento",null,eventos);
       }

        if (numeroEventos == -1 || numeroEventos == 0){
            eventosRegistrados = "Error al insertar el evento, verificar inserción";
        }else{
            eventosRegistrados =  eventosRegistrados + numeroEventos + " Registrado";
        }

        return eventosRegistrados;

    }

    public Evento consultarEvento(String idEvento){
        String[]id = {idEvento};
        Cursor cursor = db.query("evento", camposEvento, "idEvento = ?", id, null,null,null);
        if(cursor.moveToFirst()){
            Evento evento = new Evento();
            evento.setIdEvento(cursor.getString(0));
            evento.setIdTipoE(cursor.getString(1));
            evento.setNomEvento(cursor.getString(2));
            evento.setCostoEvento(cursor.getFloat(3));
            return evento;
        }else{
            return null;
        }
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

            case 6:{
                //Verificar si existe el tipoPago
                TipoPago p = (TipoPago) valor;
                String[] id = {p.getIdPago()};
                Cursor cursor = db.query("tipopago",null,"idPago = ?",id,null,null,null);
                if(cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            } case 7:{
                //Obtener el tipodePago a eliminar
                TipoPago tipoPago = (TipoPago) valor;
                Cursor cursor = db.query(true,"tipopago",new String[]{"idPago"},"idPago='"+tipoPago.getIdPago()+"'",null,null,null,null,null);
                if(cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            } case 8:{
                //Verificar que al insertar el Evento exista el tipoDeEvento
                Evento evento = (Evento)valor;
                String[] idTipoEvento = {evento.getIdTipoE()};
                Cursor cursor = db.query("tipoevento", null, "idTipoE = ?",idTipoEvento,null,null,null);
                if(cursor.moveToFirst()){
                    //Se encontraron datos de tipoEvento
                    return true;
                }
                return false;
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
