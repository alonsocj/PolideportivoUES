package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.LocalEvento.LocalEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.Reservacion;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;

public class ControlBDCarolina {
    /*Tabla PeriodoReserva*/
    private static final String[] camposPeriodoReserva = new String[]{"idPeriodoReserva","fechaInicio","fechaFin"};

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

        /*PeriodoReserva p= new PeriodoReserva();
        ContentValues values = new ContentValues();
        values.put("idPeriodoReserva", "CAROLI");
        values.put("fechaInicio", "17/04/2022");
        values.put("fechaFin", "02/05/2022");
        contador += db.insert("periodoReserva",null,values);

        ContentValues l=new ContentValues();
        l.put("idReservacion", "R00001");
        l.put("idCobro","C00001");
        l.put("idPersona","P00001");
        l.put("idTipoR","P");
        l.put("idEvento","E00001");
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
        //verifica si hay registros de periodo reserva en la tabla reservacion y en la tabla detallePeriodosReservados
        PeriodoReserva periodo = (PeriodoReserva)periodoReserva;
        Cursor c=db.query(true, "reservacion", new String[] {"idPeriodoReserva" }, "idperiodoReserva='"+periodo.getIdPeriodoReserva()+"'",null,null, null, null, null);
        if(c.moveToFirst()){
            return true;
        }else{
            return false;
        }

        /*Cursor c1=db.query(true, "detallePeriodosReservados", new String[] {"idPeriodoReserva" }, "idperiodoReserva='"+periodo.getIdPeriodoReserva()+"'",null,null, null, null, null);

        if(c.moveToFirst()||c1.moveToFirst()){
            return true;
        }else{
            return false;
        }*/

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
        String regAfectados3="Filas afectadas en la tabla detalle de periodos reservados= ";
        String suma;
        int contador1=0;
        int contador2=0;
        int contador3=0;

        contador1+=db.delete("reservacion","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
        regAfectados1 = regAfectados1 + contador1;
        contador2+=db.delete("periodoReserva","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
        regAfectados2 = regAfectados2 + contador2;
        /*contador3+=db.delete("detallePeriodosReservados","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);*/
        regAfectados3 = regAfectados3 + contador3;

        suma=regAfectados2+"\n"+regAfectados1+"\n"+regAfectados3;
        return suma;
    }
    /*public String eliminarPeriodoReserva(PeriodoReserva periodoReserva){
        String regAfectados="filas afectadas= ";
        int contador=0;
            //verificar que exista el id periodo reserva
            if (verificarIntegridad(periodoReserva,3)) {
                contador+=db.delete("reservacion","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
                regAfectados = regAfectados + contador;
            }else{
                contador+=db.delete("periodoReserva","idPeriodoReserva='"+periodoReserva.getIdPeriodoReserva()+"'",null);
                regAfectados = regAfectados + contador;
            }
        return regAfectados;
    }*/

    //CRUD LocalEvento
    public String insertarLocalEvento (LocalEvento localEvento){

        return null;
    }
    public String actualizarLocalEvento(LocalEvento localEvento){

        return null;
    }
    public String consultarLocalEvento(LocalEvento localEvento){

        return null;
    }
    public String eliminarLocalEvento(LocalEvento localEvento){

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
            case 2:
            {
                PeriodoReserva periodo = (PeriodoReserva) dato;
                String[]id = {periodo.getIdPeriodoReserva()};
                open();
                Cursor cursor = db.query("periodoReserva",null,"idPeriodoReserva = ?", id,null,null,null);
                if (cursor.moveToFirst()){
                    return true;
                }else{
                    return false;
                }
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
