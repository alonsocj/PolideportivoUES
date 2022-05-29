package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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



    public String llenarBDG10(){
        //Metodo para llenar la base de datos con sentencias SQL

        //Tabla dia
        final String[] VADias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};

        //Tabla nacionalidad
        final String[] codNac = {"AR","SV"};
        final String[] nacionalidad = {"Argentina","El Salvador"};

        //Tabla genero
        final String[] idGenero = {"G00001"};
        final String[] genero = {"Masculino"};

        //Tabla tipoEvento
        final String[] idTipoE = {"C","P","D"};
        final String[] nomTipoE = {"Cultural","Político","Deportivo"};

        //Tabla tipoPago
        final String[] idPago = {"EF","TC"};
        final String[] tipo = {"Efectivo","Tarjeta de credito"};

        //Tabla hora
        final String[] idHora =
                {"H001", "H002", "H003", "H004", "H005", "H006", "H007", "H008", "H009", "H010", "H011", "H012", "H013", "H014",
                        "H015", "H016", "H017", "H018", "H019", "H020", "H021", "H022", "H023", "H024", "H025"};
        final String[] horaInicio = {"06:30","06:30", "08:00","09:00","10:00","10:00","10:00", "10:30", "11:00", "11:25","11:25", "12:00", "12:00", "12:00",
                                    "12:15", "12:30", "13:30", "13:30", "13:30", "14:00","14:00","14:00","14:30","15:00","16:00"};
        final String[] horaFin = {"07:45","08:30","10:00","10:00","11:00","11:30","14:00","12:00","12:00","13:30","14:00","13:00","13:45","14:00", "14:00",
                                    "18:30","14:30","15:30","16:30","15:00","15:45","18:00","15:30","17:00","18:00"};

        //Tabla local
        final String[] idLocal = {"CEU01","PAEU1"};
        final String[] nomLocal = {"Cancha del estadio Universitario","Pista de atletismo"};
        final Integer[] cantidadPersonas = {100,50};

        //Tabla tipoReservacion
        final String[] idTipoR = {"T","P"};
        final String[] nomTipoR = {"Total","Parcial"};

        //Tabla horariosDisponibles
        final String[] idHorario = {"H00001","H00002"};
        final String[] idHoraHD = {"H001","H002"};
        final String[] nombreDia = {"Lunes","Martes"};

        //Tabla horariosLocales
        final String[] idHorarioHL = {"H00001","H00002"};
        final String[] idLocalHL = {"CEU01","PAEU1"};
        final String[] disponibilidad = {"0","1"};

        //Tabla persona
        final String[] idPersona = {"111111111"};
        final String[] nombre = {"Paul"};
        final String[] apellido = {"Paz"};
        final String[] idGeneroP = {"000001"};
        final String[] nacimiento = {"15/04/1998"};
        final String[] codNacP = {"AR"};
        final String[] direccion = {"Calle las bermudas casa #4"};
        final String[] email = {"paul@gmail.com"};
        final String[] telefono = {"75189930"};

        //Tabla cobro
        final Integer[] idCobro = {1};
        final String[] idPagoC = {"01"};
        final Integer[] cantPersonas = {25};
        final Float[] duracion = {2.50f};
        final Float[] precio = {2.0f};
        final String[] duracionM = {"02:30"};

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
        //Tabla Reservacion
        final String[]  idReservacion={"R00001"};
        final Integer[] idCobroR ={1};
        final String[] idPersonaR ={"111111111"};
        final String[] idTipoRR ={"T"};
        final String[] idEventoR ={"E00001"};
        final String[] idPeriodoReservaR ={"PR0001"};
        final String[] idHorarioR ={"H00002"};
        final String[] idLocalR ={"PAEU1"};
        final String[] fechaRegistro ={"30/4/2022"};

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
        db.execSQL("DELETE FROM persona");
        db.execSQL("DELETE FROM cobro");
        db.execSQL("DELETE FROM periodoReserva");
        db.execSQL("DELETE FROM evento");
        db.execSQL("DELETE FROM reservacion");

        //Se llenan las tablas con datos
        for (String vaDia : VADias) {
            db.execSQL("INSERT INTO dia (nombreDia) VALUES ('"+vaDia+"')");
        }
        for(int i=0;i<codNac.length;i++){
            db.execSQL("INSERT INTO nacionalidad (codNac,nacionalidad) VALUES ('"+codNac[i]+"','"+nacionalidad[i]+"');");
        }
        for(int i=0;i<idGenero.length;i++){
            db.execSQL("INSERT INTO genero (idGenero,genero) VALUES ('"+idGenero[i]+"','"+genero[i]+"');");
        }
        for(int i=0;i<idTipoE.length;i++){
            db.execSQL("INSERT INTO tipoEvento (idTipoE,nomTipoE) VALUES ('"+idTipoE[i]+"','"+nomTipoE[i]+"');");
        }
        for(int i=0;i<idPago.length;i++){
            db.execSQL("INSERT INTO tipoPago (idPago,tipo) VALUES ('"+idPago[i]+"','"+tipo[i]+"');");
        }
        for(int i=0;i<idHora.length;i++){
            db.execSQL("INSERT INTO hora (idHora,horaInicio,horaFin) VALUES ('"+idHora[i]+"','"+horaInicio[i]+"','"+horaFin[i]+"');");
        }
        for(int i=0;i<idLocal.length;i++){
            db.execSQL("INSERT INTO local (idLocal,nomLocal,cantidadPersonas) VALUES ('"+idLocal[i]+"','"+nomLocal[i]+"','"+cantidadPersonas[i]+"');");
        }
        for(int i=0;i<idTipoR.length;i++){
            db.execSQL("INSERT INTO tipoReservacion (idTipoR,nomTipoR) VALUES ('"+idTipoR[i]+"','"+nomTipoR[i]+"');");
        }
        for(int i=0;i<idHorario.length;i++){
            db.execSQL("INSERT INTO horariosDisponibles (idHorario,idHora,nombreDia) VALUES ('"+idHorario[i]+"','"+idHoraHD[i]+"','"+nombreDia[i]+"');");
        }
        for(int i=0;i<idHorarioHL.length;i++){
            db.execSQL("INSERT INTO horariosLocales (idHorario,idLocal,disponibilidad) VALUES ('"+idHorarioHL[i]+"','"+idLocalHL[i]+"','"+disponibilidad[i]+"');");
        }
        for(int i=0;i<idPersona.length;i++){
            db.execSQL("INSERT INTO persona (idPersona,nombre,apellido,idGenero,nacimiento,codNac,direccion,email,telefono) VALUES ('"+idPersona[i]+"','"+nombre[i]+"','"+apellido[i]+"','"+idGeneroP[i]+"','"+nacimiento[i]+"','"+codNacP[i]+"','"+direccion[i]+"','"+email[i]+"','"+telefono[i]+"');");
        }
        for(int i=0;i<idCobro.length;i++){
            db.execSQL("INSERT INTO cobro (idCobro,idPago,cantPersonas,duracion,precio,duracionM) VALUES ('"+idCobro[i]+"','"+idPagoC[i]+"','"+cantPersonas[i]+"','"+duracion[i]+"','"+precio[i]+"','"+duracionM[i]+"');");
        }
        for(int i=0;i<idPeriodoReserva.length;i++){
            db.execSQL("INSERT INTO periodoReserva (idPeriodoReserva,fechaInicio,fechaFin) VALUES ('"+idPeriodoReserva[i]+"','"+fechaInicio[i]+"','"+fechaFin[i]+"');");
        }
        for(int i=0;i<idEvento.length;i++){
            db.execSQL("INSERT INTO evento (idEvento,idTipoE,nomEvento,costoEvento,cantidadAutorizada) VALUES ('"+idEvento[i]+"','"+VidTipoE[i]+"','"+nomEvento[i]+"','"+costoEvento[i]+"','"+cantidadAutorizada[i]+"');");
        }
        for(int i=0;i<idReservacion.length;i++){
            db.execSQL("INSERT INTO reservacion (idReservacion,idCobro,idPersona,idTipoR,idEvento,idPeriodoReserva,idHorario,idLocal,fechaRegistro) VALUES ('"+idReservacion[i]+"','"+idCobroR[i]+"','"+idPersonaR[i]+"','"+idTipoRR[i]+"','"+idEventoR[i]+"','"+idPeriodoReservaR[i]+"','"+idHorarioR[i]+"','"+idLocalR[i]+"','"+fechaRegistro[i]+"');");
        }

        close();

        return "Llenado correctamente";
    }
    public String datosAcceso(){
        final String[] VAidUsuario = {"01","02","03"};
        final String[] VAnomUsuario = {"Carlos","Alberto","Hernan"};
        final String[] VAClave = {"Ch1q2","jA3f2","gD21d"};

        db.execSQL("DELETE FROM usuario;");

        for(int i=0;i<VAidUsuario.length;i++){
            db.execSQL("INSERT INTO usuario (idUsuario,nomUsuario,clave) VALUES ('"+VAidUsuario[i]+"','"+VAnomUsuario[i]+"','"+VAClave[i]+"');");
        }

        return "Valores de acceso";
    }
}
