package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPago;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.Genero;
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


    public String llenarBDG10() {
        //Metodo para llenar la base de datos con sentencias SQL

        //Tabla dia
        final String[] VADias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

        //Tabla nacionalidad
        final String[] codNac = {"AR", "SV"};
        final String[] nacionalidad = {"Argentina", "El Salvador"};


        //Tabla tipoEvento
        final String[] idTipoE = {"C", "P", "D"};
        final String[] nomTipoE = {"Cultural", "Político", "Deportivo"};

        //Tabla tipoPago
        final String[] idPago = {"EF", "TC"};
        final String[] tipo = {"Efectivo", "Tarjeta de credito"};

        //Tabla hora
        final String[] idHora =
                {"H001", "H002", "H003", "H004", "H005", "H006", "H007", "H008", "H009", "H010", "H011", "H012", "H013", "H014",
                        "H015", "H016", "H017", "H018", "H019", "H020", "H021", "H022", "H023", "H024", "H025"};
        final String[] horaInicio = {"06:30", "06:30", "08:00", "09:00", "10:00", "10:00", "10:00", "10:30", "11:00", "11:25", "11:25", "12:00", "12:00", "12:00",
                "12:15", "12:30", "13:30", "13:30", "13:30", "14:00", "14:00", "14:00", "14:30", "15:00", "16:00"};
        final String[] horaFin = {"07:45", "08:30", "10:00", "10:00", "11:00", "11:30", "14:00", "12:00", "12:00", "13:30", "14:00", "13:00", "13:45", "14:00", "14:00",
                "18:30", "14:30", "15:30", "16:30", "15:00", "15:45", "18:00", "15:30", "17:00", "18:00"};

        //Tabla local
        final String[] idLocal = {"CEU01", "PAEU1"};
        final String[] nomLocal = {"Cancha del estadio Universitario", "Pista de atletismo"};
        final Integer[] cantidadPersonas = {100, 50};

        //Tabla tipoReservacion
        final String[] idTipoR = {"T", "P"};
        final String[] nomTipoR = {"Total", "Parcial"};

        //Tabla horariosDisponibles
        final String[] idHorario = {"H00001", "H00002"};
        final String[] idHoraHD = {"H001", "H002"};
        final String[] nombreDia = {"Lunes", "Martes"};

        //Tabla horariosLocales
        final String[] idHorarioHL = {"H00001", "H00002"};
        final String[] idLocalHL = {"CEU01", "PAEU1"};
        final String[] disponibilidad = {"0", "1"};

        //genero

        final String[] idGenero = {"G00001","G00002"};

        final String[] genero = {"Masculino","Femenino"};


        //Tabla cobro
        final Integer[] idCobro = {1,2,3,4,5,6,7,8,9,10};
        final String[] idPagoC = {"TC","TC","TC","TC","TC","EF","EF","EF","EF","EF"};
        final Integer[] cantPersonas = {50,100,150,200,30,20,15,60,75,250};
        final Float[] duracion = {2.50f,2.50f,2.50f,2.50f,2.50f,2.50f,2.50f,2.50f,2.50f,2.50f};
        final Float[] precio = {0.00f,0.00f,0.00f,0.00f,0.00f,0.00f,0.00f,0.00f,0.00f,0.00f};
        final String[] duracionM = {"02:30","02:30","02:30","02:30","02:30","02:30","02:30","02:30","02:30","02:30"};

        //Tabla periodoReserva
        final String[] idPeriodoReserva = {"PR0001"};
        final String[] fechaInicio = {"01/05/2022"};
        final String[] fechaFin = {"01/05/2022"};

        //Tabla evento
        final String[] idEvento = {"E00001"};
        final String[] VidTipoE = {"C"};
        final String[] nomEvento = {"Festival deportivo de la FRAY"};
        final Float[] costoEvento = {15f};
        final Integer[] cantidadAutorizada = {10};

        open();
        db.execSQL("DELETE FROM dia");
        db.execSQL("DELETE FROM nacionalidad");
        db.execSQL("DELETE FROM genero");
        db.execSQL("DELETE FROM tipoevento");
        db.execSQL("DELETE FROM tipopago");
        db.execSQL("DELETE FROM hora");
        db.execSQL("DELETE FROM local");
        db.execSQL("DELETE FROM tipoReservacion");
        db.execSQL("DELETE FROM horariosDisponibles");
        db.execSQL("DELETE FROM horariosLocales");
        db.execSQL("DELETE FROM cobro");
        db.execSQL("DELETE FROM periodoReserva");
        db.execSQL("DELETE FROM evento");

        //Se llenan las tablas con datos
        for (String vaDia : VADias) {
            db.execSQL("INSERT INTO dia (nombreDia) VALUES ('" + vaDia + "')");
        }
        for (int i = 0; i < codNac.length; i++) {
            db.execSQL("INSERT INTO nacionalidad (codNac,nacionalidad) VALUES ('" + codNac[i] + "','" + nacionalidad[i] + "');");
        }
        for (int i = 0; i < idGenero.length; i++) {
            db.execSQL("INSERT INTO genero (idGenero,genero) VALUES ('" + idGenero[i] + "','" + genero[i] + "');");
        }
        for (int i = 0; i < idTipoE.length; i++) {
            db.execSQL("INSERT INTO tipoEvento (idTipoE,nomTipoE) VALUES ('" + idTipoE[i] + "','" + nomTipoE[i] + "');");
        }
        for (int i = 0; i < idPago.length; i++) {
            db.execSQL("INSERT INTO tipoPago (idPago,tipo) VALUES ('" + idPago[i] + "','" + tipo[i] + "');");
        }
        for (int i = 0; i < idHora.length; i++) {
            db.execSQL("INSERT INTO hora (idHora,horaInicio,horaFin) VALUES ('" + idHora[i] + "','" + horaInicio[i] + "','" + horaFin[i] + "');");
        }
        for (int i = 0; i < idLocal.length; i++) {
            db.execSQL("INSERT INTO local (idLocal,nomLocal,cantidadPersonas) VALUES ('" + idLocal[i] + "','" + nomLocal[i] + "','" + cantidadPersonas[i] + "');");
        }
        for (int i = 0; i < idTipoR.length; i++) {
            db.execSQL("INSERT INTO tipoReservacion (idTipoR,nomTipoR) VALUES ('" + idTipoR[i] + "','" + nomTipoR[i] + "');");
        }
        for (int i = 0; i < idHorario.length; i++) {
            db.execSQL("INSERT INTO horariosDisponibles (idHorario,idHora,nombreDia) VALUES ('" + idHorario[i] + "','" + idHoraHD[i] + "','" + nombreDia[i] + "');");
        }
        for (int i = 0; i < idHorarioHL.length; i++) {
            db.execSQL("INSERT INTO horariosLocales (idHorario,idLocal,disponibilidad) VALUES ('" + idHorarioHL[i] + "','" + idLocalHL[i] + "','" + disponibilidad[i] + "');");
        }

        for (int i = 0; i < idCobro.length; i++) {
            db.execSQL("INSERT INTO cobro (idCobro,idPago,cantPersonas,duracion,precio,duracionM) VALUES ('" + idCobro[i] + "','" + idPagoC[i] + "','" + cantPersonas[i] + "','" + duracion[i] + "','" + precio[i] + "','" + duracionM[i] + "');");
        }
        for (int i = 0; i < idPeriodoReserva.length; i++) {
            db.execSQL("INSERT INTO periodoReserva (idPeriodoReserva,fechaInicio,fechaFin) VALUES ('" + idPeriodoReserva[i] + "','" + fechaInicio[i] + "','" + fechaFin[i] + "');");
        }
        for (int i = 0; i < idEvento.length; i++) {
            db.execSQL("INSERT INTO evento (idEvento,idTipoE,nomEvento,costoEvento,cantidadAutorizada) VALUES ('" + idEvento[i] + "','" + VidTipoE[i] + "','" + nomEvento[i] + "','" + costoEvento[i] + "','" + cantidadAutorizada[i] + "');");
        }

        close();

        return "Llenado correctamente";
    }

    public String datosAcceso() {
        final String[] VAidUsuario = {"01", "02", "03", "04"};
        final String[] VAnomUsuario = {"admin", "Carlos", "Alberto", "Hernan"};
        final String[] VAClave = {"1234", "Ch1q2", "jA3f2", "gD21d"};
        final String[] VAIdOpcion = {"010", "011", "012", "013", "020", "021", "022", "023",
                "030", "031", "032", "033", "040", "041", "042", "043", "050", "051", "052", "053", "060", "061", "062", "063",
                "070", "071", "072", "073", "080", "081", "082", "083", "090", "091", "092", "093", "100", "101", "102", "103",
                "110", "111", "112", "113", "120", "121", "122", "123", "130", "131", "132", "133", "140", "141", "142", "143",
                "150", "151", "152", "153"};
        final String[] DesOpcion = {"Adición de Reservacion", "Eliminacion de Reservacion",
                "Actualización de Reservacion", "Consulta de Reservacion",
                "Adición de Persona", "Eliminacion de Persona", "Actualización de Persona", "Consulta de Persona", "Adición de Evento", "Eliminacion de Evento",
                "Actualización de Evento", "Consulta de Evebto", "Adición de Local", "Eliminacion de Local",
                "Actualización de Local", "Consulta de Local", "Adición de Periodo de Reservacion", "Eliminacion de Periodo de Reservacion",
                "Actualización de Periodo de Reservacion", "Consulta de Periodo de Reservacion",
                "Adición de Horarios Disponibles", "Eliminacion de Horarios Disponibles", "Actualización de Horarios Disponibles", "Consulta de Horarios Disponibles",
                "Adición de Horas", "Eliminacion de Horas", "Actualización de Horas", "Consulta de Horas",
                "Adición de Tipo de Evento", "Eliminacion de Tipo de Evento", "Actualización de Tipo de Evento", "Consulta de Tipo de Evento",
                "Adición de Cobro", "Eliminacion de Cobro", "Actualización de Cobro", "Consulta de Cobro",
                "Adición de Tipo de Pago", "Eliminacion de Tipo de Pago", "Actualización de Tipo de Pago", "Consulta de Tipo de Pago",
                "Adición de Tipo de Reservacion ", "Eliminacion de Tipo de Reservacion", "Actualización de Tipo de Reservacion", "Consulta de Tipo de Reservacion",
                "Adición de Horarios Locales ", "Eliminacion de Horarios Locales", "Actualización de Horarios Locales", "Consulta de Horarios Locales",
                "Adición de Genero", "Eliminacion de Genero", "Actualización de Genero", "Consulta de Genero",
                "Adición de Nacionalidad", "Eliminacion de Nacionalidad", "Actualización de Nacionalidad", "Consulta de Nacionalidad",
                "Adición de Dia", "Eliminacion de Dia", "Actualización de Dia", "Consulta de Dia"};
        final int[] VANumCrud = {1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,
                1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,};

        Log.v("Tamaño", "ID: " + String.valueOf(VAIdOpcion.length) + " Descripcion: " + String.valueOf(DesOpcion.length) + " NumCrud: " + String.valueOf(VANumCrud.length));

        final String[] VAIdUsuario = {"01", "02", "03"};
        final String[] VAIdOpcionEliminar = {"011", "021", "031", "041", "051", "061", "071", "081", "091", "101", "111", "121", "131", "141", "151"};
        final String[] VAIdOpcionInsertar = {"010", "020", "030", "040", "050", "060", "070", "080", "090", "100", "110", "120", "130", "140", "150"};

        db.execSQL("DELETE FROM usuario;");
        db.execSQL("DELETE FROM opcionCrud;");
        db.execSQL("DELETE FROM accesoUsuario;");

        for (int i = 0; i < VAidUsuario.length; i++) {
            db.execSQL("INSERT INTO usuario (idUsuario,nomUsuario,clave) VALUES ('" + VAidUsuario[i] + "','" + VAnomUsuario[i] + "','" + VAClave[i] + "');");
        }

        for (int i = 0; i < VAIdOpcion.length; i++) {
            db.execSQL("INSERT INTO opcionCrud (idOpcion,desOpcion,numCrud) VALUES ('" + VAIdOpcion[i] + "','" + DesOpcion[i] + "','" + VANumCrud[i] + "');");
        }

        //all access
        for (int i = 0; i < VAIdOpcion.length; i++) {
            db.execSQL("INSERT INTO accesoUsuario (idUsuario,idOpcion) VALUES ('" + VAIdUsuario[0] + "','" + VAIdOpcion[i] + "');");
        }

        //This user can't delete
        for (int i = 0; i < VAIdOpcion.length; i++) {
            for (int j = 0; j < VAIdOpcionEliminar.length; j++) {
                if (VAIdOpcion[i].equals(VAIdOpcionEliminar[j])) {
                    db.execSQL("INSERT INTO accesoUsuario (idUsuario,idOpcion) VALUES ('" + VAIdUsuario[1] + "','" + VAIdOpcionEliminar[j] + "');");
                }
            }
        }

        for (int i = 0; i < VAIdOpcion.length; i++) {
            for (int j = 0; j < VAIdOpcionInsertar.length; j++) {
                if (VAIdOpcion[i].equals(VAIdOpcionInsertar[j])) {
                    db.execSQL("INSERT INTO accesoUsuario (idUsuario,idOpcion) VALUES ('" + VAIdUsuario[2] + "','" + VAIdOpcionInsertar[j] + "');");
                }
            }
        }

        return "Valores de acceso";
    }
}
