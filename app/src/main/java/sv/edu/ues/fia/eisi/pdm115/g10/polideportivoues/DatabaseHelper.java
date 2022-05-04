package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "polideportivo.s3db";
    private static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            //Consultas para crear las tablas y triggers de la base de datos

            //Tabla Nacionalidad
            db.execSQL("CREATE TABLE nacionalidad (codNac VARCHAR(2) NOT NULL PRIMARY KEY, nacionalidad VARCHAR(50) NOT NULL);");
            //tabla PeriodoReserva
            db.execSQL("CREATE TABLE periodoReserva (idPeriodoReserva VARCHAR(6) NOT NULL PRIMARY KEY,fechaInicio VARCHAR(10) NOT NULL,fechaFin VARCHAR(10) NOT NULL);");
            //tabla Dia
            db.execSQL("CREATE TABLE dia (nombreDia VARCHAR(10) PRIMARY KEY);");
            //tabla TipoEvento
            db.execSQL("CREATE TABLE tipoevento (idTipoE VARCHAR(1) PRIMARY KEY, nomTipoE VARCHAR(50));");
            //tabla Evento
            db.execSQL("CREATE TABLE evento(idEvento VARCHAR(6) NOT NULL PRIMARY KEY, idTipoE VARCHAR(1) NOT NULL, " +
                    " nomEvento VARCHAR(100) NOT NULL, costoEvento REAL NOT NULL, cantidadAutorizada INT NOT NULL," +
                    " CONSTRAINT fk_evento_tipoevento FOREIGN KEY (idTipoE) REFERENCES tipoevento(idTipoE) ON DELETE RESTRICT);");
            //tabla TipoPago
            db.execSQL("CREATE TABLE tipopago(idPago VARCHAR(2) NOT NULL PRIMARY KEY, tipo VARCHAR(20) NOT NULL);");
            //Tabla Cobro
            db.execSQL("CREATE TABLE cobro (idCobro INTEGER NOT NULL PRIMARY KEY , idPago VARCHAR(2) NOT NULL, cantPersonas INTEGER NOT NULL, duracion FLOAT NOT NULL, precio FLOAT NOT NULL, duracionM VARCHAR(5) NOT NULL," +
                    " CONSTRAINT fk_cobro_tipopago FOREIGN KEY (idPago) REFERENCES tipopago(idPago) ON DELETE RESTRICT);");
            //Tabla Hora
            db.execSQL("CREATE TABLE hora (idHora VARCHAR(4) NOT NULL PRIMARY KEY, horaInicio VARCHAR(25) NOT NULL , horaFin VARCHAR(25) NOT NULL);");
            //Tabla Local
            db.execSQL("CREATE TABLE local (idLocal VARCHAR(5) NOT NULL PRIMARY KEY, nomLocal VARCHAR(50) NOT NULL, cantidadPersonas INTEGER NOT NULL);");
            //Tabla LocalEvento
            db.execSQL("CREATE TABLE localEvento (idEvento VARCHAR(6) NOT NULL ,idLocal VARCHAR(5) NOT NULL ,idLocalEvento VARCHAR(5) ,cantAutorizada INTEGER NOT NULL , PRIMARY KEY(idEvento,idLocal,idLocalEvento));");
            //Tabla TipoReservacion
            db.execSQL("CREATE TABLE tipoReservacion (idTipoR VARCHAR(1) NOT NULL PRIMARY KEY, nomTipoR VARCHAR(10) NOT NULL);");
            //Tabla Reservacion
            db.execSQL("CREATE TABLE reservacion(\n" +
                    "idReservacion VARCHAR(6) NOT NULL PRIMARY KEY,\n" +
                    "idCobro VARCHAR (6) NOT NULL,\n" +
                    "idPersona VARCHAR(6) NOT NULL,\n" +
                    "idTipoR VARCHAR  (1) NOT NULL,\n" +
                    "idEvento VARCHAR (6) NOT NULL,\n" +
                    "idPeriodoReserva VARCHAR (6) NOT NULL,\n" +
                    "idHorario VARCHAR (2) NOT NULL,\n" +
                    "idLocal VARCHAR (5) NOT NULL,\n" +
                    "fechaRegistro VARCHAR(10) NOT NULL,\n" +
                    "CONSTRAINT fk_reservacion_cobro FOREIGN KEY (idCobro) REFERENCES cobro(idCobro) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_persona FOREIGN KEY (idPersona) REFERENCES persona(idPersona) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_tipoReservacion FOREIGN KEY (idTipoR) REFERENCES tipoReservacion(idTipoR) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_evento FOREIGN KEY (idEvento) REFERENCES Evento(idEvento) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_periodoReserva FOREIGN KEY (idPeriodoReserva) REFERENCES periodoReserva(idPeriodoReserva) ON DELETE RESTRICT\n" +
                    ");\n");

            db.execSQL("CREATE TABLE horariosLocales(\n" +
                    "   idHorario VARCHAR(6) NOT NULL,\n" +
                    "   idLocal VARCHAR(5) NOT NULL,\n" +
                    "   disponibilidad INTEGER NOT NULL,\n" +
                    "   PRIMARY KEY (idHorario, idLocal),\n" +
                    "   CONSTRAINT FK_DETALLEP_PERIODOR FOREIGN KEY (idHorario) REFERENCES horariosDisponibles (idHorario) ON DELETE RESTRICT,\n" +
                    "   CONSTRAINT FK_DETALLEP_HORARIOS FOREIGN KEY (idLocal) REFERENCES Local (idLocal) ON DELETE RESTRICT\n" +
                    ");\n");

            //Trigger de relacion de llaves foraneas de la tabla Evento con tipoevento
            db.execSQL("CREATE TRIGGER fk_evento_tipoevento " +
                        "BEFORE INSERT ON evento " +
                        "FOR EACH ROW " +
                        "BEGIN " +
                        "SELECT CASE " +
                        "WHEN ((SELECT idTipoE FROM tipoevento WHERE idTipoE = NEW.idTipoE ) IS NULL)" +
                        "THEN RAISE(ABORT, 'No existe el tipo de evento')" +
                        "END;" +
                        "END;");

            //Triggers de integridad de relación de tabla Reservación
            db.execSQL("CREATE TRIGGER fk_reservacion_cobro\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idCobro FROM Cobro WHERE idCobro = NEW.idCobro) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe cobro')\n" +
                    "END;\n" +
                    "END;\n");

            db.execSQL("CREATE TRIGGER fk_reservacion_persona\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idPersona FROM persona WHERE idPersona = NEW.idPersona) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe persona')\n" +
                    "END;\n" +
                    "END;\n");

            db.execSQL("CREATE TRIGGER fk_reservacion_tipoReservacion\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idTipoR FROM tipoReservacion WHERE idTipoR = NEW.idTipoR) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe el tipo de reservacion')\n" +
                    "END;\n" +
                    "END;\n");

            db.execSQL("CREATE TRIGGER fk_reservacion_evento\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idEvento FROM evento WHERE idEvento = NEW.idEvento) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe el evento')\n" +
                    "END;\n" +
                    "END;\n");

            db.execSQL("CREATE TRIGGER fk_reservacion_periodoReserva\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idPeriodoReserva FROM periodoReserva WHERE idPeriodoReserva = NEW.idPeriodoReserva) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe el periodo de reserva')\n" +
                    "END;\n" +
                    "END;\n");

            //tabla Persona
            db.execSQL("CREATE TABLE persona  (\n" +
                    "  idPersona VARCHAR(9) NOT NULL PRIMARY KEY,\n" +
                    "  nombre VARCHAR(50) NOT NULL,\n" +
                    "  apellido VARCHAR(50) NOT NULL,\n" +
                    "  idGenero VARCHAR(6) NOT NULL,\n" +
                    "  nacimiento VARCHAR(10) NOT NULL,\n" +
                    "  codNac VARCHAR(2) NOT NULL,\n" +
                    "  direccion VARCHAR(100) NOT NULL,\n" +
                    "  email VARCHAR(50) NOT NULL,\n" +
                    "  telefono VARCHAR(8) NOT NULL\n" +
                    ");");

            //tabla Horarios Disponibles
            db.execSQL("CREATE TABLE horariosDisponibles  (\n" +
                    "  idHorario VARCHAR(6) NOT NULL PRIMARY KEY,\n" +
                    "  idHora VARCHAR(4) NOT NULL,\n" +
                    "  nombreDia VARCHAR(10) NOT NULL\n" +
                    ");");

            //tabla Genero
            db.execSQL("CREATE TABLE genero  (\n" +
                    "  idGenero VARCHAR(6) NOT NULL PRIMARY KEY,\n" +
                    "  genero VARCHAR(10) NOT NULL\n" +
                    ");");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
