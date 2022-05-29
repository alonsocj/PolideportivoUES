package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPago;

public class ControlBDChristian {

    private static final String[] camposHora = new String[]{"idhora","horaInicio","horaFin"};
    private static final String[] camposTipoPago = new String[]{"idPago","tipo"};
    private static final String[] camposEvento = new String[]{"idEvento", "idTipoE", "nomEvento", "costoEvento","cantidadAutorizada"};

    private final DatabaseHelper DBhelper; /*Esta es la clase que contiene todas las instrucciones SQL*/
    private SQLiteDatabase db;

    public ControlBDChristian(Context ctx) {
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
        //cuenta+=db.delete("hora","idHora='"+hora.getIdHora()+"'",null);
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
        //cuenta+=db.delete("tipopago","idPago='"+tipoPago.getIdPago()+"'",null);
        tiposdepagosafectados+=cuenta;
        return tiposdepagosafectados;
    }

    /*Funcionalidades del Evento*/

    public String agregarEvento(Evento evento){
        String eventosRegistrados =  "Evento N°: ";
        long numeroEventos=0;

        if (verificarIntegridadDeDatos(evento, 8)) {
            ContentValues eventos = new ContentValues();
            eventos.put("idEvento", evento.getIdEvento());
            eventos.put("idTipoE", evento.getIdTipoE()); //Llave Foranea
            eventos.put("nomEvento", evento.getNomEvento());
            eventos.put("costoEvento",evento.getCostoEvento());
            eventos.put("cantidadAutorizada",evento.getCantidadAutorizada());
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
            evento.setCantidadAutorizada(cursor.getInt(4));
            return evento;
        }else{
            return null;
        }
    }

    public String actualizarEvento(Evento evento){
        if(verificarIntegridadDeDatos(evento,9)){
            String[] id = {evento.getIdEvento()};
            ContentValues contentValues = new ContentValues();
            contentValues.put("idTipoE",evento.getIdTipoE());
            contentValues.put("nomEvento",evento.getNomEvento());
            contentValues.put("costoEvento", evento.getCostoEvento());
            contentValues.put("cantidadAutorizada",evento.getCantidadAutorizada());
            db.update("evento",contentValues, "idEvento = ?",id);
            return "Evento actualizado correctamente";
        }else{
            return "No existe el evento";
        }
    }

    //Sin cascada
    public String eliminarEvento(Evento evento){
        String eventosEliminados = "Evento eliminado: ";
        int contador = 0;
        if(verificarIntegridadDeDatos(evento,10)){
            contador+=db.delete("evento","idEvento='"+evento.getIdEvento()+"'",null);
        }
        //contador+=db.delete("evento","idEvento='"+evento.getIdEvento()+"'",null);
        eventosEliminados += contador;
        return eventosEliminados;
    }


    //Metodos para eliminar Con cascada y utilizarlos en el AlertDialog
    public Boolean verificarExisEvento(Evento evento){
        //Se verifica la existencia del idEvento

        Evento evento1 = (Evento) evento;
        String[] idE = {evento1.getIdEvento()};
        open();
        Cursor cursor = db.query("evento",null,"idEvento = ?",idE,null,null,null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public Boolean verificarEventosCascada(Evento evento){
        //Verificar si hay registros de evento en la tabla reservacion
        Evento eve = (Evento) evento;
        Cursor cursor = db.query(true,"reservacion", new String[]{"idEvento"}, "idEvento='"+eve.getIdEvento()+"'", null,null,null,null,null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public String eliminarEventosCascada(Evento evento){
        String regAfectadosEvento = "Filas afectadas en la tabla evento = ";
        String regAfectadosReservacion = "Filas afectadas en la tabla reservacion = ";
        String suma;
        int contadorE = 0;
        int contadorR = 0;

        contadorR = db.delete("reservacion","idEvento ='"+evento.getIdEvento()+"'",null);
        regAfectadosReservacion = regAfectadosReservacion + contadorR;
        contadorE = db.delete("evento","idEvento= '"+evento.getIdEvento()+"'",null);
        regAfectadosEvento = regAfectadosEvento + contadorE;

        suma = regAfectadosEvento + "\n\n" + regAfectadosReservacion;
        return suma;
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
                }else{
                    return false;
                }
            } case 9:{
                //Verificar que existe el Evento
                Evento evento1 = (Evento)valor;
                String[] idEven = {evento1.getIdEvento()};
                open();
                Cursor cursor = db.query("evento",camposEvento,"idEvento = ?",idEven, null,null,null);
                if(cursor.moveToFirst()){
                    //Se encontro el evento
                    return true;
                }else{
                    return false;
                }
            } case 10:{
                //Obtener el evento para eliminarlo
                Evento evento = (Evento) valor;
                Cursor cursor = db.query(true,"evento", new String[]{"idEvento"},"idEvento='"+evento.getIdEvento()+"'",null,null,null,null,null);
                if(cursor.moveToFirst()){
                    //Se encontro el evento
                    return true;
                }else{
                    return false;
                }
            }
            default:
                return false;
        }
    }


}
