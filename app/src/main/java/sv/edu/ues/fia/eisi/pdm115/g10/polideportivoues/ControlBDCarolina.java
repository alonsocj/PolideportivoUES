package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.LocalEvento.LocalEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.Reservacion;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
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

        /*ContentValues values = new ContentValues();
        values.put("idPeriodoReserva", "CAROLI");
        values.put("fechaInicio", "17/04/2022");
        values.put("fechaFin", "02/05/2022");
        db.insert("periodoReserva",null,values);

        ContentValues l=new ContentValues();
        l.put("idReservacion", "R00001");
        l.put("idCobro","C00001");
        l.put("idPersona","P00001");
        l.put("idTipoR","P");
        l.put("idEvento","000001");
        l.put("idPeriodoReserva","CAROLI");
        l.put("fechaRegistro","05/05/2022");
        db.insert("reservacion",null, l);*/

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



    //CRUD LocalEvento
    public String insertarLocalEvento (LocalEvento localEvento){

        return null;
    }

    //CRUD Reservacion
    public String insertarReservacion (Reservacion reservacion){

        return null;
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
            persona.setDireccion(cursor.getString(5));
            persona.setEmail(cursor.getString(6));
            persona.setTelefono(cursor.getString(7));
            arrayPersonas.add(persona);

            while(cursor.moveToNext()) {
                Persona personas = new Persona();
                personas.setIdPersona(cursor.getString(0));
                personas.setNombre(cursor.getString(1));
                personas.setApellido(cursor.getString(2));
                personas.setGenero(cursor.getString(3));
                personas.setNacimiento(cursor.getString(4));
                personas.setDireccion(cursor.getString(5));
                personas.setEmail(cursor.getString(6));
                personas.setTelefono(cursor.getString(7));
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
            arrayEventos.add(evento);

            while(cursor.moveToNext()) {
                Evento eventos = new Evento();
                eventos.setIdEvento(cursor.getString(0));
                eventos.setIdTipoE(cursor.getString(1));
                eventos.setNomEvento(cursor.getString(2));
                eventos.setCostoEvento(cursor.getFloat(3));
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
            arrayPeriodoReservacionString.add(periodoRArray.getFechaFin()+" - "+periodoRArray.getFechaFin());
        }
        return arrayPeriodoReservacionString;
    }

    //Extraer listado de todos los horarios locales
    public List<HorariosLocales> consultarHorariosLocales(){
        List<HorariosLocales> arrayHorariosLocales=new ArrayList<>();
        Cursor cursor = db.query("horariosLocales",null, null, null, null, null, null);

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
            arrayHorariosLocalesString.add("Id local: "+ horariosLArray.getIdLocal()+ " - id horario: "+ horariosLArray.getIdHorario());
        }
        return arrayHorariosLocalesString;
    }

    //Extraer listado de todos los locales
    public List<Local> consultarLocales(){
        List<Local> arrayLocales=new ArrayList<>();
        Cursor cursor = db.query("local",null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            Local local = new Local();
            local.setIdLocal(cursor.getString(0));
            local.setNomLocal(cursor.getString(1));
            local.setCantidadPersonas(Integer.parseInt(cursor.getString(2)));
            arrayLocales.add(local);

            while(cursor.moveToNext()) {
                Local locals = new Local();
                locals.setIdLocal(cursor.getString(0));
                locals.setNomLocal(cursor.getString(1));
                locals.setCantidadPersonas(Integer.parseInt(cursor.getString(2)));
                arrayLocales.add(locals);
            }
        }
        return arrayLocales;
    }
    public List<String> consultarLocalesString(List<Local> arrayLocales){
        List<String>arrayLocalesString=new ArrayList<>();
        arrayLocalesString.add("Seleccione un local");
        for (int i=0;i<arrayLocales.size();i++) {
            Local localessArray=new Local();
            localessArray=arrayLocales.get(i);
            arrayLocalesString.add(localessArray.getNomLocal());
        }
        return arrayLocalesString;
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
            }
            /*case 2:
            {
                //verificar que al modificar nota exista carnet del alumno, el
                codigo de materia y el ciclo
                Nota nota1 = (Nota)dato;
                String[] ids = {nota1.getCarnet(), nota1.getCodmateria(),
                        nota1.getCiclo()};
                abrir();
                Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND
                        ciclo = ?", ids, null, null, null);
                if(c.moveToFirst()){
                //Se encontraron datos
                    return true;
                }
                return false;
            }*/
           /* case 3:
            {
                Alumno alumno = (Alumno)dato;
                Cursor c=db.query(true, "nota", new String[] {
                                "carnet" }, "carnet='"+alumno.getCarnet()+"'",null,
                        null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 4:
            {
                Materia materia = (Materia)dato;
                Cursor cmat=db.query(true, "nota", new String[] {
                                "codmateria" },
                        "codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
                if(cmat.moveToFirst())
                return true;
                else
                return false;
            }
            case 5:
            {
                //verificar que al insertar nota exista carnet del alumno y el
                codigo de materia
                Nota nota = (Nota)dato;
                String[] id1 = {nota.getCarnet()};
                String[] id2 = {nota.getCodmateria()};

                Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null,null, null);
                Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2,null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }return false;
            }
            case 6:
            {
                //verificar que exista Materia
                Materia materia2 = (Materia)dato;
                String[] idm = {materia2.getCodmateria()};
                abrir();
                Cursor cm = db.query("materia", null, "codmateria = ?", idm, null,
                        null, null);
                if(cm.moveToFirst()){
                //Se encontro Materia
                    return true;
                }return false;
            }*/
            default:
                return false;
        }
    }
}






















