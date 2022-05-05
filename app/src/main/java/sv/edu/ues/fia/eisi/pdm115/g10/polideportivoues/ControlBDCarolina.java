package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.Reservacion;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

public class ControlBDCarolina {
    /*Tabla PeriodoReserva*/
    private static final String[] camposPeriodoReserva = new String[]{"idPeriodoReserva","fechaInicio","fechaFin"};
    private static final String[] camposNacionalidad = new String[]{"codNac","nacionalidad"};

    private final DatabaseHelper DBhelper; /*Instrucciones SQL*/
    private SQLiteDatabase db;

    public ControlBDCarolina(Context ctx) {
        DBhelper = new DatabaseHelper(ctx);
    }
    public void open() throws SQLException {
        db = DBhelper.getWritableDatabase();
        return;
    }
    public void close() {
        DBhelper.close();
    }

    //CRUD Periodo Reserva
    public String insertarPeriodoReserva (PeriodoReserva periodoReserva){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues value = new ContentValues();
        value.put("idPeriodoReserva", periodoReserva.getIdPeriodoReserva());
        value.put("fechaInicio", periodoReserva.getFechaInicio());
        value.put("fechaFin", periodoReserva.getFechaFin());
        contador = db.insert("periodoReserva",null,value);

        if(contador==-1 || contador==0){
            regInsertados="Registro duplicado. Verifique inserción";
        }else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String actualizarPeriodoReserva(PeriodoReserva periodoReserva){

        if(verificarIntegridad(periodoReserva, 1)){
            String[] id = {periodoReserva.getIdPeriodoReserva()};
            ContentValues cv = new ContentValues();
            cv.put("fechaInicio", periodoReserva.getFechaInicio());
            cv.put("fechaFin", periodoReserva.getFechaFin());
            db.update("periodoReserva", cv, "idPeriodoReserva = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Periodo de reserva con id " + periodoReserva.getIdPeriodoReserva() + " no existe";
        }
    }
    public PeriodoReserva consultarPeriodoReserva(String periodoReserva){
        String[] id = {periodoReserva};
        Cursor cursor = db.query("periodoReserva", camposPeriodoReserva, "idPeriodoReserva = ?",id, null, null, null);
        if(cursor.moveToFirst()){
            PeriodoReserva periodo = new PeriodoReserva();
            periodo.setIdPeriodoReserva(cursor.getString(0));
            periodo.setFechaInicio(cursor.getString(1));
            periodo.setFechaFin(cursor.getString(2));
            return periodo;
        }else{
            return null;
        }
    }
    public Boolean verificarExisPeriodo(PeriodoReserva periodoReserva){
        //verifica la existencia del id periodo reserva
        PeriodoReserva periodo = (PeriodoReserva) periodoReserva;
        String[]id = {periodo.getIdPeriodoReserva()};
        open();
        Cursor cursor = db.query("periodoReserva",null,"idPeriodoReserva = ?", id,null,null,null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public Boolean verificarPeriodosReservaCascada(PeriodoReserva periodoReserva){
        //verifica si hay registros de periodo reserva en la tabla reservacion
        PeriodoReserva periodo = (PeriodoReserva)periodoReserva;
        Cursor c=db.query(true, "reservacion", new String[] {"idPeriodoReserva" }, "idperiodoReserva='"+periodo.getIdPeriodoReserva()+"'",null,null, null, null, null);
        if(c.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public String eliminarPeriodoReserva(PeriodoReserva periodoReserva){
        String regAfectados="Filas afectadas= ";
        int contador=0;

          contador+=db.delete("periodoReserva","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
          regAfectados = regAfectados + contador;
          return regAfectados;
    }
    public String eliminarPeriodoReservaCascada(PeriodoReserva periodoReserva){
        String regAfectados1="Filas afectadas en la tabla reservación= ";
        String regAfectados2="Filas afectadas en la tabla periodo reserva= ";
        String suma;
        int contador1=0;
        int contador2=0;

        contador1+=db.delete("reservacion","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
        regAfectados1 = regAfectados1 + contador1;
        contador2+=db.delete("periodoReserva","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
        regAfectados2 = regAfectados2 + contador2;

        suma=regAfectados2+"\n"+regAfectados1;
        return suma;
    }

    //CRUD Nacionalidad
    public String insertarNacionalidad (Nacionalidad nac){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues value = new ContentValues();
        value.put("codNac", nac.getCodNac());
        value.put("nacionalidad", nac.getNacionalidad());
        contador = db.insert("nacionalidad",null,value);
        if(contador==-1 || contador==0){
            regInsertados="Registro duplicado. Verifique inserción";
        }else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String actualizarNacionalidad(Nacionalidad nac){

        if(verificarIntegridad(nac, 2)){
            String[] id = {nac.getCodNac()};
            ContentValues cv = new ContentValues();
            cv.put("nacionalidad", nac.getNacionalidad());
            db.update("nacionalidad", cv, "codNac = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "La nacionalidad con el código " + nac.getCodNac() + " no existe";
        }
    }
    public Nacionalidad consultarNacionalidad(String nac){
        String[] id = {nac};
        Cursor cursor = db.query("nacionalidad", camposNacionalidad, "codNac = ?",id, null, null, null);
        if(cursor.moveToFirst()){
            Nacionalidad naci = new Nacionalidad();
            naci.setCodNac(cursor.getString(0));
            naci.setNacionalidad(cursor.getString(1));
            return naci;
        }else{
            return null;
        }
    }
    public Boolean verificarExisNacionalidad(Nacionalidad nac){
        //verifica la existencia de nacionalidad
        Nacionalidad nacionalidad = (Nacionalidad) nac;
        String[]id = {nacionalidad.getCodNac()};
        open();
        Cursor cursor = db.query("nacionalidad",null,"codNac = ?", id,null,null,null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public Boolean verificarNacionalidadCascada(Nacionalidad nac){
        //verifica si hay registros de nacionalidad en la tabla persona
        Nacionalidad nacionalidad = (Nacionalidad)nac;
        Cursor c=db.query(true, "persona", new String[] {"codNac" }, "codNac='"+nacionalidad.getCodNac()+"'",null,null, null, null, null);
        if(c.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public String eliminarNacionalidad(Nacionalidad nac){
        String regAfectados="Filas afectadas = ";
        int contador=0;

        contador+=db.delete("nacionalidad","codNac='"+nac.getCodNac()+"'",null);
        regAfectados = regAfectados + contador;
        return regAfectados;
    }
    public String eliminarNacionalidadCascada(Nacionalidad nac){
        String regAfectados1="Filas afectadas en la tabla persona= ";
        String regAfectados2="Filas afectadas en la tabla nacionalidad= ";
        String suma;
        int contador1=0;
        int contador2=0;

        contador1+=db.delete("persona","codNac='"+nac.getCodNac()+"'",null);
        regAfectados1 = regAfectados1 + contador1;
        contador2+=db.delete("nacionalidad","codNac='"+nac.getCodNac()+"'",null);
        regAfectados2 = regAfectados2 + contador2;

        suma=regAfectados2+"\n"+regAfectados1;
        return suma;
    }

    //CRUD Reservacion
    public String insertarReservacion (Reservacion reservacion){
        String regInsertados="Registros Insertados Nº= ";
        long contador=0;

        ContentValues reservacion1 = new ContentValues();
        reservacion1.put("idReservacion", reservacion.getIdReservacion());
        reservacion1.put("idCobro", reservacion.getIdCobro());
        reservacion1.put("idPersona", reservacion.getIdPersona());
        reservacion1.put("idTipoR", reservacion.getIdTipoR());
        reservacion1.put("idEvento", reservacion.getIdEvento());
        reservacion1.put("idPeriodoReserva", reservacion.getIdPeriodoReserva());
        reservacion1.put("idHorario", reservacion.getIdHorario());
        reservacion1.put("idLocal", reservacion.getIdLocal());
        reservacion1.put("fechaRegistro", reservacion.getFechaRegistro());


        contador=db.insert("reservacion", null, reservacion1);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Registro duplicado!";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String actualizarReservacion(Reservacion reservacion){

        return null;
    }
    public String consultarReservacion(Reservacion reservacion){

        return null;
    }
    public String eliminarReservacion(Reservacion reservacion){

        return null;
    }


    //Extraer listado de todos los cobros
    public List<Cobro> consultarCobros(){
        List<Cobro> arrayCobros=new ArrayList<>();
        Cursor cursor = db.query("cobro",null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            Cobro cobro = new Cobro();
            cobro.setIdCobro(Integer.parseInt(cursor.getString(0)));
            cobro.setIdPago(cursor.getString(1));
            cobro.setCantPersonas(Integer.parseInt(cursor.getString(2)));
            cobro.setDuracion(Float.parseFloat(cursor.getString(3)));
            cobro.setPrecio(Float.parseFloat(cursor.getString(4)));
            arrayCobros.add(cobro);

            while(cursor.moveToNext()) {
                Cobro cobros = new Cobro();
                cobros.setIdCobro(Integer.parseInt(cursor.getString(0)));
                cobros.setIdPago(cursor.getString(1));
                cobros.setCantPersonas(Integer.parseInt(cursor.getString(2)));
                cobros.setDuracion(Float.parseFloat(cursor.getString(3)));
                cobros.setPrecio(Float.parseFloat(cursor.getString(4)));
                arrayCobros.add(cobros);
            }
        }
        return arrayCobros;
    }
    public List<String> consultarCobrosString(List<Cobro> arrayCobros){
        List<String>arrayCobrosString=new ArrayList<>();
        for (int i=0;i<arrayCobros.size();i++) {
            Cobro cobrosArray=new Cobro();
            cobrosArray=arrayCobros.get(i);
            arrayCobrosString.add(String.valueOf(cobrosArray.getIdCobro()));
        }
        return arrayCobrosString;
    }

    //Extraer listado de todos los Personas
    public List<Persona> consultarPersonas(){
        List<Persona> arrayPersonas=new ArrayList<>();
        Cursor cursor = db.query("persona",null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            Persona persona = new Persona();
            persona.setIdPersona(cursor.getString(0));
            persona.setNombre(cursor.getString(1));
            persona.setApellido(cursor.getString(2));
            persona.setGenero(cursor.getString(3));
            persona.setNacimiento(cursor.getString(4));
            persona.setNacionalidad(cursor.getString(5));
            persona.setDireccion(cursor.getString(6));
            persona.setEmail(cursor.getString(7));
            persona.setTelefono(cursor.getString(8));
            arrayPersonas.add(persona);

            while(cursor.moveToNext()) {
                Persona personas = new Persona();
                personas.setIdPersona(cursor.getString(0));
                personas.setNombre(cursor.getString(1));
                personas.setApellido(cursor.getString(2));
                personas.setGenero(cursor.getString(3));
                personas.setNacimiento(cursor.getString(4));
                personas.setNacionalidad(cursor.getString(5));
                personas.setDireccion(cursor.getString(6));
                personas.setEmail(cursor.getString(7));
                personas.setTelefono(cursor.getString(8));
                arrayPersonas.add(personas);
            }
        }
        return arrayPersonas;
    }
    public List<String> consultarPersonasString(List<Persona> arrayPersonas){
        List<String>arrayPersonasString=new ArrayList<>();
        for (int i=0;i<arrayPersonas.size();i++) {
            Persona personasArray=new Persona();
            personasArray=arrayPersonas.get(i);
            arrayPersonasString.add(personasArray.getNombre()+" "+personasArray.getApellido());
        }
        return arrayPersonasString;
    }

    //Extraer listado de todos los TipoReservacion
    public List<TipoReservacion> consultarTipoReservacion(){
        List<TipoReservacion> arrayTipoReservaciones=new ArrayList<>();
        Cursor cursor = db.query("tipoReservacion",null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            TipoReservacion tipoR = new TipoReservacion();
            tipoR.setIdTipoR(cursor.getString(0));
            tipoR.setNomTipoR(cursor.getString(1));
            arrayTipoReservaciones.add(tipoR);

            while(cursor.moveToNext()) {
                TipoReservacion tipoR1 = new TipoReservacion();
                tipoR1.setIdTipoR(cursor.getString(0));
                tipoR1.setNomTipoR(cursor.getString(1));
                arrayTipoReservaciones.add(tipoR1);
            }
        }
        return arrayTipoReservaciones;
    }
    public List<String> consultarTipoReservacionString(List<TipoReservacion> arrayTipoReservaciones){
        List<String>arrayTipoReservacionesString=new ArrayList<>();
        for (int i=0;i<arrayTipoReservaciones.size();i++) {
            TipoReservacion tipoRArray=new TipoReservacion();
            tipoRArray=arrayTipoReservaciones.get(i);
            arrayTipoReservacionesString.add(tipoRArray.getNomTipoR());
        }
        return arrayTipoReservacionesString;
    }

    //Extraer listado de todos los eventos
    public List<Evento> consultarEventos(){
        List<Evento> arrayEventos=new ArrayList<>();
        Cursor cursor = db.query("evento",null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            Evento evento = new Evento();
            evento.setIdEvento(cursor.getString(0));
            evento.setIdTipoE(cursor.getString(1));
            evento.setNomEvento(cursor.getString(2));
            evento.setCostoEvento(cursor.getFloat(3));
            evento.setCantidadAutorizada(cursor.getInt(4));
            arrayEventos.add(evento);

            while(cursor.moveToNext()) {
                Evento eventos = new Evento();
                eventos.setIdEvento(cursor.getString(0));
                eventos.setIdTipoE(cursor.getString(1));
                eventos.setNomEvento(cursor.getString(2));
                eventos.setCostoEvento(cursor.getFloat(3));
                eventos.setCantidadAutorizada(cursor.getInt(4));
                arrayEventos.add(eventos);
            }
        }
        return arrayEventos;
    }
    public List<String> consultarEventosString(List<Evento> arrayEventos){
        List<String>arrayEventosString=new ArrayList<>();
        for (int i=0;i<arrayEventos.size();i++) {
            Evento eventosArray=new Evento();
            eventosArray=arrayEventos.get(i);
            arrayEventosString.add(eventosArray.getNomEvento());
        }
        return arrayEventosString;
    }

    //Extraer listado de todos los peridos de reserva
    public List<PeriodoReserva> consultarPeriodoReservacion(){
        List<PeriodoReserva> arrayPeriodoReservacion=new ArrayList<>();
        Cursor cursor = db.query("periodoReserva",null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            PeriodoReserva periodoR = new PeriodoReserva();
            periodoR.setIdPeriodoReserva(cursor.getString(0));
            periodoR.setFechaInicio(cursor.getString(1));
            periodoR.setFechaFin(cursor.getString(2));
            arrayPeriodoReservacion.add(periodoR);

            while(cursor.moveToNext()) {
                PeriodoReserva periodoR1 = new PeriodoReserva();
                periodoR1.setIdPeriodoReserva(cursor.getString(0));
                periodoR1.setFechaInicio(cursor.getString(1));
                periodoR1.setFechaFin(cursor.getString(2));
                arrayPeriodoReservacion.add(periodoR1);
            }
        }
        return arrayPeriodoReservacion;
    }
    public List<String> consultarPeriodoReservacionString(List<PeriodoReserva> arrayPeriodoReservacion){
        List<String>arrayPeriodoReservacionString=new ArrayList<>();
        for (int i=0;i<arrayPeriodoReservacion.size();i++) {
            PeriodoReserva periodoRArray=new PeriodoReserva();
            periodoRArray=arrayPeriodoReservacion.get(i);
            arrayPeriodoReservacionString.add(periodoRArray.getFechaInicio()+" - "+periodoRArray.getFechaFin());
        }
        return arrayPeriodoReservacionString;
    }

    //Extraer listado de todos los horarios locales
    public List<HorariosLocales> consultarHorariosLocales(){
        List<HorariosLocales> arrayHorariosLocales=new ArrayList<>();
        Cursor cursor = db.query("horariosLocales",null,"disponibilidad=0" , null, null, null, null);

        if(cursor.moveToFirst()){
            HorariosLocales horariosL = new HorariosLocales();
            horariosL.setIdHorario(cursor.getString(0));
            horariosL.setIdLocal(cursor.getString(1));
            horariosL.setDisponibilidad(Integer.parseInt(cursor.getString(2)));
            arrayHorariosLocales.add(horariosL);

            while(cursor.moveToNext()) {
                HorariosLocales horariosL1 = new HorariosLocales();
                horariosL1.setIdHorario(cursor.getString(0));
                horariosL1.setIdLocal(cursor.getString(1));
                horariosL1.setDisponibilidad(Integer.parseInt(cursor.getString(2)));
                arrayHorariosLocales.add(horariosL1);
            }
        }
        return arrayHorariosLocales;
    }
    public List<String> consultarHorariosLocalesString(List<HorariosLocales> arrayHorariosLocales){
        List<String>arrayHorariosLocalesString=new ArrayList<>();
        for (int i=0;i<arrayHorariosLocales.size();i++) {
            HorariosLocales horariosLArray=new HorariosLocales();
            horariosLArray=arrayHorariosLocales.get(i);

            //Obtenemos el valor especifico del dia de la tabla horarios disponibles
            HorariosDisponibles horariosDisponibles= new HorariosDisponibles();
            horariosDisponibles.setIdHorario(horariosLArray.getIdHorario());
            Cursor cursor1=db.query("horariosDisponibles",null,"idHorario='"+horariosDisponibles.getIdHorario()+"'",null,null,null,null);
            HorariosDisponibles horariosD = new HorariosDisponibles();
            if(cursor1.moveToFirst()){
                horariosD.setIdHorario(cursor1.getString(0));
                horariosD.setHora(cursor1.getString(1));
                horariosD.setDia(cursor1.getString(2));
            }

            //Obtenemos el valor especifico de la hora de la tabla horarios disponibles
            Hora horas= new Hora();
            horas.setIdHora(horariosD.getHora());
            Cursor cursor2=db.query("hora",null,"idHora='"+horas.getIdHora()+"'",null,null,null,null);
            Hora horasD = new Hora();
            if(cursor2.moveToFirst()){
                horasD.setIdHora(cursor2.getString(0));
                horasD.setHoraInicio(cursor2.getString(1));
                horasD.setHoraFin(cursor2.getString(2));
            }

            //Obtenemos el valor especifico del local de la tabla local
            Local local= new Local();
            local.setIdLocal(horariosLArray.getIdLocal());
            Cursor cursor3=db.query("local",null,"idLocal='"+local.getIdLocal()+"'",null,null,null,null);
            Local locales = new Local();
            if(cursor3.moveToFirst()){
                locales.setIdLocal(cursor3.getString(0));
                locales.setNomLocal(cursor3.getString(1));
                locales.setCantidadPersonas(Integer.parseInt(cursor3.getString(2)));
            }

            arrayHorariosLocalesString.add(locales.getNomLocal()+ " - "+ horariosD.getDia()+" "+horasD.getHoraInicio()+ "-"+horasD.getHoraFin());
        }
        return arrayHorariosLocalesString;
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1: //verificar que exista el periodo de reserva
            {
                PeriodoReserva periodo = (PeriodoReserva)dato;
                String[] id = {periodo.getIdPeriodoReserva()};
                open();
                Cursor c2 = db.query("periodoReserva", null, "idPeriodoReserva= ?", id, null, null,null);
                if(c2.moveToFirst()){//Se encontro periodo reserva
                    return true;
                }return false;
            }
            case 2://verificar que exista la nacionalidad
            {
                Nacionalidad nac = (Nacionalidad) dato;
                String[] id = {nac.getCodNac()};
                open();
                Cursor c2 = db.query("nacionalidad", null, "codNac= ?", id, null, null,null);
                if(c2.moveToFirst()){//Se encontro nacionalidad
                    return true;
                }return false;
            }
            case 3:
            {
                PeriodoReserva periodo = (PeriodoReserva)dato;
                Cursor c=db.query(true, "reservacion", new String[] {"idPeriodoReserva" }, "idperiodoReserva='"+periodo.getIdPeriodoReserva()+"'",null,null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }default:
                return false;
        }
    }
}






















