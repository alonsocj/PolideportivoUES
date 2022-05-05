package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPago;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;
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

    /*---------------------Metodos para tabla Local--------------*/
    public String ingresarLocal(Local local){
        String localInsertado = "Local ";
        long cuenta = 0;
        ContentValues locales = new ContentValues();
        locales.put("idLocal", local.getIdLocal());
        locales.put("nomLocal", local.getNomLocal());
        locales.put("cantidadPersonas", local.getCantidadPersonas());
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
            contentValues.put("cantidadPersonas",local.getCantidadPersonas());
            db.update("local",contentValues, "idLocal = ?",id);
            return "Local Actualizado";
        }else{
            return "El local no existe";
        }
    }

    public Local consultarLocal(String idlocal){
        String[] id = {idlocal};
        Cursor cursor = db.query("local", new String [] {"idLocal","nomLocal","cantidadPersonas"}, "idLocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Local local = new Local();
            local.setIdLocal(cursor.getString(0));
            local.setNomLocal(cursor.getString(1));
            local.setCantidadPersonas(Integer.parseInt(cursor.getString(2)));
            return local;
        }else{
            return null;
        }
    }


    public String eliminarLocal(Local local, int tipo){
        String regAfectados;
        int contador;
        switch (tipo){
            case 0:
                if (verificarIntegridadDeDatos(local,1)) {
                    if (verificarIntegridadDeDatos(local,3)) {
                        return "3";
                    }else{
                        if (verificarIntegridadDeDatos(local,2)) {
                            return "2";
                        }else {
                            regAfectados="filas afectadas= ";
                            contador=0;
                            contador += db.delete("local", "idLocal='" + local.getIdLocal() + "'", null);
                            regAfectados += contador;
                            return regAfectados;
                        }
                    }
                }else{
                    return "El local no existe";
                }
            case 1:
                regAfectados="filas afectadas= ";
                contador=0;
                contador+=db.delete("horariosLocales", "idLocal='"+local.getIdLocal()+"'", null);
                contador+=db.delete("local", "idLocal='"+local.getIdLocal()+"'", null);
                regAfectados+=contador;
                return regAfectados;
            case 2:
                regAfectados="filas afectadas= ";
                contador=0;
                contador+=db.delete("reservacion", "idLocal='"+local.getIdLocal()+"'", null);
                contador+=db.delete("horariosLocales", "idLocal='"+local.getIdLocal()+"'", null);
                contador+=db.delete("local", "idLocal='"+local.getIdLocal()+"'", null);
                regAfectados+=contador;
                return regAfectados;
            default:
                return null;
        }

    }

    /*---------------------Metodos para tabla TipoReservacion--------------*/
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
        if(verificarIntegridadDeDatos(tipoReservacion,4)){
        String[] id={tipoReservacion.getIdTipoR()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomTipoR", tipoReservacion.getNomTipoR());
        db.update("tipoReservacion",contentValues, "idTipoR = ?",id);
        return "Tipo de reservacion Actualizado";
        }else{
        return "El Tipo de reservacion no existe";
        }
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

    public String eliminarTipoReservacion(TipoReservacion tipoReservacion, int tipo){
        String regAfectados;
        int contador;
        switch (tipo) {
            case 0:
                regAfectados="filas afectadas= ";
                contador=0;
                if (verificarIntegridadDeDatos(tipoReservacion, 4)) {
                    if (verificarIntegridadDeDatos(tipoReservacion, 5)) {
                        return "1";
                    } else {
                        contador += db.delete("tipoReservacion", "idTipoR='" + tipoReservacion.getIdTipoR() + "'", null);
                        regAfectados += contador;
                        return regAfectados;
                    }
                } else {
                    return "El tipo de reservacion no existe";
                }
            case 1:
                regAfectados="filas afectadas= ";
                contador=0;
                contador += db.delete("reservacion", "idTipoR='" + tipoReservacion.getIdTipoR() + "'", null);
                contador += db.delete("tipoReservacion", "idTipoR='" + tipoReservacion.getIdTipoR() + "'", null);
                regAfectados += contador;
                return regAfectados;
            default:
                return null;
        }
    }

    /*---------------------Metodos para tabla HorariosLocales--------------*/
    public String ingresarHorariosLocales(HorariosLocales horariosLocales){
        String localInsertado = "Horario del Local ";
        long cuenta = 0;

        ContentValues horarios = new ContentValues();
        horarios.put("idHorario", horariosLocales.getIdHorario());
        horarios.put("idLocal", horariosLocales.getIdLocal());
        horarios.put("disponibilidad", horariosLocales.getDisponibilidad());
        cuenta = db.insert("horariosLocales",null,horarios);
        if(cuenta == -1 || cuenta == 0){
            localInsertado = "Error al ingresar un horario de local que ya existe, Verificar su inserción";
        }else{
            localInsertado = localInsertado + cuenta + " Registrado";
        }

        return localInsertado;
    }

    public String actualizarHorariosLocales(HorariosLocales horariosLocales){
        if(verificarIntegridadDeDatos(horariosLocales,6)){
            long cuenta;
            String[] id={horariosLocales.getIdHorario(), horariosLocales.getIdLocal()};
            ContentValues contentValues = new ContentValues();
            contentValues.put("disponibilidad", horariosLocales.getDisponibilidad());
            cuenta = db.update("horariosLocales",contentValues, "idHorario = ? and idLocal = ?",id);
            if(cuenta == -1 || cuenta == 0){
                return "Horario del local no Actualizado";
            }else{
                return "Horario del local Actualizado";
            }
        }else{
            return "El horario del local no existe";
        }
    }

    public HorariosLocales consultarHorariosLocales(String idHorario, String idlocal){
        String[] id = {idHorario, idlocal};
        Cursor cursor = db.query("horariosLocales", new String [] {"idHorario","idlocal","disponibilidad"}, "idHorario = ? and idLocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            HorariosLocales horariosLocales = new HorariosLocales();
            horariosLocales.setIdHorario(cursor.getString(0));
            horariosLocales.setIdLocal(cursor.getString(1));
            horariosLocales.setDisponibilidad(Integer.parseInt(cursor.getString(2)));
            return horariosLocales;
        }else{
            return null;
        }
    }

    public String eliminarHorariosLocales(HorariosLocales horariosLocales, int tipo){
        String regAfectados;
        int contador;
        switch (tipo) {
            case 0:
                regAfectados = "filas afectadas= ";
                contador = 0;
                if (verificarIntegridadDeDatos(horariosLocales, 6)) {
                    if (verificarIntegridadDeDatos(horariosLocales, 7)) {
                        return "1";
                    } else {
                        contador += db.delete("horariosLocales", "idHorario = '" + horariosLocales.getIdHorario() + "' and idLocal = '" + horariosLocales.getIdLocal() + "'", null);
                        regAfectados += contador;
                        return regAfectados;
                    }
                } else {
                    return "El local no existe";
                }
            case 1:
                regAfectados = "filas afectadas= ";
                contador = 0;
                contador += db.delete("reservacion", "idHorario='" + horariosLocales.getIdHorario() + "' and idLocal='" + horariosLocales.getIdLocal() + "'", null);
                contador += db.delete("horariosLocales", "idHorario = '" + horariosLocales.getIdHorario() + "' and idLocal = '" + horariosLocales.getIdLocal() + "'", null);
                regAfectados += contador;
                return regAfectados;
            default:
                return null;
        }
    }

    /*---------------------Verificar integridad--------------*/
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
                //Verifica si existen horarios asociados al local
                Local local = (Local) valor;
                Cursor c=db.query(true, "horariosLocales", new String[] {"idLocal"}, "idLocal='"+local.getIdLocal()+"'",null, null, null, null, null);
                if(c.moveToFirst()){
                    return true;
                }
                else{
                    return false;
                }
            }
            case 3: {
                //verifica si existen reservaciones asociadas a este local
                Local local = (Local) valor;
                Cursor c=db.query(true, "reservacion", new String[] {"idLocal"}, "idLocal='"+local.getIdLocal()+"'",null, null, null, null, null);
                if(c.moveToFirst()){
                    return true;
                }
                else{
                    return false;
                }
            }
            case 4: {
                //Verifica si existe el tipo de reservacion a actualizar
                TipoReservacion tipoR = (TipoReservacion) valor;
                String[]id = {tipoR.getIdTipoR()};
                open();
                Cursor cursor = db.query("tipoReservacion",null,"idTipoR = ?", id,null,null,null);
                if (cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
            }
            case 5:{
                //Verifica si existen reservaciones asociadas al tipo
                TipoReservacion tipoR = (TipoReservacion) valor;
                Cursor c=db.query(true, "reservacion", new String[] {"idTipoR"}, "idTipoR='"+tipoR.getIdTipoR()+"'",null, null, null, null, null);
                if(c.moveToFirst()){
                    return true;
                }
                else{
                    return false;
                }
            }
            case 6:{
                //Verifica si existe el horario
                HorariosLocales horariosLocales = (HorariosLocales) valor;
                String[]id = {horariosLocales.getIdHorario(),horariosLocales.getIdLocal()};
                Cursor cursor = db.query("horariosLocales",null,"idHorario = ? and idLocal = ?", id,null,null,null);
                if(cursor.moveToFirst()){
                    return true;
                }
                else{
                    return false;
                }
            }
            case 7:{
                //Verifica si existen reservaciones asociadas al horario
                HorariosLocales horariosLocales = (HorariosLocales) valor;
                Cursor c=db.query(true, "reservacion", new String[] {"idHorario","idLocal"}, "idHorario='"+horariosLocales.getIdHorario()+"' and idLocal='"+horariosLocales.getIdLocal()+"'",null, null, null, null, null);
                if(c.moveToFirst()){
                    return true;
                }
                else{
                    return false;
                }
            }
            default:
                return false;
        }
    }

    /*---------------------Consultas para los select--------------*/
    public List<HorariosDisponibles> consultarHorarioDisponibles(){
        List<HorariosDisponibles> arrayHorarios=new ArrayList<>();
        Cursor cursor = db.query("horariosDisponibles",null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            HorariosDisponibles horarioDisponible = new HorariosDisponibles();
            horarioDisponible.setIdHorario(cursor.getString(0));
            horarioDisponible.setHora(cursor.getString(1));
            horarioDisponible.setDia(cursor.getString(2));
            arrayHorarios.add(horarioDisponible);
            while(cursor.moveToNext()) {
                HorariosDisponibles horariosDisponible = new HorariosDisponibles();
                horariosDisponible.setIdHorario(cursor.getString(0));
                horariosDisponible.setHora(cursor.getString(1));
                horariosDisponible.setDia(cursor.getString(2));
                arrayHorarios.add(horariosDisponible);
            }
        }
        return arrayHorarios;
    }
    public List<String> consultarHorarioDisponiblesString(List<HorariosDisponibles> arrayHorarios){
        List<String>arrayHorariosString=new ArrayList<>();
        for (int i=0;i<arrayHorarios.size();i++) {
            HorariosDisponibles horariosArray = new HorariosDisponibles();
            horariosArray = arrayHorarios.get(i);
            String[] id = {horariosArray.getHora()};
            Cursor cursor = db.query("hora", null, "idHora = ?", id, null, null, null);
            if (cursor.moveToFirst()) {
                Hora hora = new Hora();
                hora.setIdHora(cursor.getString(0));
                hora.setHoraInicio(cursor.getString(1));
                hora.setHoraFin(cursor.getString(2));
                arrayHorariosString.add(horariosArray.getDia() + " " + hora.getHoraInicio()+"-"+hora.getHoraFin());
            } else {
                arrayHorariosString.add(horariosArray.getHora() + " " + horariosArray.getIdHorario());
            }
        }
        return arrayHorariosString;
    }

    public List<Local> consultarLocales(){
        List<Local> arrayLocales=new ArrayList<>();
        Cursor cursor = db.query("local",null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            Local locales = new Local();
            locales.setIdLocal(cursor.getString(0));
            locales.setNomLocal(cursor.getString(1));
            locales.setCantidadPersonas(Integer.parseInt(cursor.getString(2)));
            arrayLocales.add(locales);
            while(cursor.moveToNext()) {
                Local Locales = new Local();
                Locales.setIdLocal(cursor.getString(0));
                Locales.setNomLocal(cursor.getString(1));
                Locales.setCantidadPersonas(Integer.parseInt(cursor.getString(2)));
                arrayLocales.add(Locales);
            }
        }
        return arrayLocales;
    }

    public List<String> consultarLocalesString(List<Local> arrayLocal){
        List<String>arrayLocalString=new ArrayList<>();
        for (int i=0;i<arrayLocal.size();i++) {
            Local localArray=new Local();
            localArray=arrayLocal.get(i);
            arrayLocalString.add(localArray.getNomLocal());
        }
        return arrayLocalString;
    }
}
