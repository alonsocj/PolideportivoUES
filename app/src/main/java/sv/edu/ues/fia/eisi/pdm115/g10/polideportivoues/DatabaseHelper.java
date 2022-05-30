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
             //Tabla TipoReservacion
            db.execSQL("CREATE TABLE tipoReservacion (idTipoR VARCHAR(1) NOT NULL PRIMARY KEY, nomTipoR VARCHAR(10) NOT NULL);");

            db.execSQL("CREATE TABLE horariosLocales(\n" +
                    "   idHorario VARCHAR(6) NOT NULL,\n" +
                    "   idLocal VARCHAR(5) NOT NULL,\n" +
                    "   disponibilidad INTEGER NOT NULL,\n" +
                    "   PRIMARY KEY (idHorario, idLocal),\n" +
                    "   CONSTRAINT FK_DETALLEP_PERIODOR FOREIGN KEY (idHorario) REFERENCES horariosDisponibles (idHorario) ON DELETE RESTRICT,\n" +
                    "   CONSTRAINT FK_DETALLEP_HORARIOS FOREIGN KEY (idLocal) REFERENCES Local (idLocal) ON DELETE RESTRICT\n" +
                    ");\n");

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
            //Tabla Reservacion
            db.execSQL("CREATE TABLE reservacion(\n" +
                    "idReservacion VARCHAR(6) NOT NULL PRIMARY KEY,\n" +
                    "idCobro INTEGER NOT NULL,\n" +
                    "idPersona VARCHAR(9) NOT NULL,\n" +
                    "idTipoR VARCHAR  (1) NOT NULL,\n" +
                    "idEvento VARCHAR (6) NOT NULL,\n" +
                    "idPeriodoReserva VARCHAR (6) NOT NULL,\n" +
                    "idHorario VARCHAR(6) NOT NULL,\n" +
                    "idLocal VARCHAR(5) NOT NULL,\n" +
                    "fechaRegistro VARCHAR(10) NOT NULL,\n" +
                    "CONSTRAINT fk_reservacion_cobro FOREIGN KEY (idCobro) REFERENCES cobro(idCobro) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_persona FOREIGN KEY (idPersona) REFERENCES persona(idPersona) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_tipoReservacion FOREIGN KEY (idTipoR) REFERENCES tipoReservacion(idTipoR) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_evento FOREIGN KEY (idEvento) REFERENCES evento(idEvento) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_periodoReserva FOREIGN KEY (idPeriodoReserva) REFERENCES periodoReserva(idPeriodoReserva) ON DELETE RESTRICT,\n" +
                    "CONSTRAINT fk_reservacion_horariosLocales FOREIGN KEY (idHorario,idLocal) REFERENCES horariosLocales(idHorario,idLocal) ON DELETE RESTRICT\n" +
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
                    "WHEN ((SELECT idCobro FROM cobro WHERE idCobro = NEW.idCobro) IS NULL)\n" +
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
            db.execSQL("CREATE TRIGGER fk_reservacion_Horarios1 \n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW \n" +
                    "BEGIN \n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idHorario FROM horariosLocales WHERE idHorario = NEW.idHorario) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe el horario')\n" +
                    "END;\n" +
                    "END;\n");
            db.execSQL("CREATE TRIGGER fk_reservacion_Locales1\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT idLocal FROM horariosLocales WHERE idLocal=NEW.idLocal) IS NULL)\n" +
                    "THEN RAISE(ABORT, 'No existe local')\n" +
                    "END;\n" +
                    "END;\n");

            //Trigger de de calculo de precio al insertar un registro de cobro
            db.execSQL("CREATE TRIGGER insertar_precio AFTER INSERT ON cobro\n" +
                    "WHEN new.precio = 0\n" +
                    "BEGIN\n" +
                    "UPDATE cobro SET precio = cantPersonas * duracion*0.25 WHERE cobro.idCobro = new.idCobro;\n" +
                    "END;");

            //Trigger de re-calculo de precio al actualizar la cantidad de personas
            db.execSQL("CREATE TRIGGER actualizar_precio_cantPersonas AFTER UPDATE OF cantPersonas ON cobro\n" +
                    "BEGIN\n" +
                    "UPDATE cobro SET precio = cantPersonas * duracion*0.25 WHERE cobro.idCobro = new.idCobro;\n" +
                    "END;");

            //Trigger de re-calculo de precio al actualizar la duracion
            db.execSQL("CREATE TRIGGER actualizar_precio_duracion AFTER UPDATE OF duracion ON cobro\n" +
                    "BEGIN\n" +
                    "UPDATE cobro SET precio = cantPersonas * duracion*0.25 WHERE cobro.idCobro = new.idCobro;\n" +
                    "END;");

            //Trigger de actualizacion disponibilidad de horarios al crear reservacion
            db.execSQL("CREATE TRIGGER reservacion_insetar\n" +
                    "AFTER INSERT ON reservacion\n" +
                    "BEGIN\n" +
                    "UPDATE horariosLocales SET disponibilidad=1 WHERE horariosLocales.idHorario=new.idHorario AND horariosLocales.idLocal=new.idLocal;\n" +
                    "END;");

            //Trigger de actualizacion disponibilidad de horarios al eliminar reservacion
            db.execSQL("CREATE TRIGGER reservacion_eliminar\n" +
                    "BEFORE DELETE ON reservacion\n" +
                    "BEGIN\n" +
                    "UPDATE horariosLocales SET disponibilidad=0 WHERE horariosLocales.idHorario=old.idHorario AND horariosLocales.idLocal=old.idLocal;\n" +
                    "END;");

            //Trigger de actualizacion disponibilidad de horarios al elegir otro horario
            db.execSQL("CREATE TRIGGER fk_nota_actualizar_nuevo\n" +
                    "AFTER UPDATE ON reservacion\n" +
                    "WHEN new.idHorario != old.idHorario and new.idLocal != old.idLocal\n" +
                    "BEGIN\n" +
                    "UPDATE horariosLocales SET disponibilidad=1 WHERE horariosLocales.idHorario=new.idHorario AND horariosLocales.idLocal=new.idLocal;\n" +
                    "END;");
            db.execSQL("CREATE TRIGGER fk_nota_actualizar_viejo\n" +
                    "AFTER UPDATE ON reservacion\n" +
                    "WHEN new.idHorario != old.idHorario and new.idLocal != old.idLocal\n" +
                    "BEGIN\n" +
                    "UPDATE horariosLocales SET disponibilidad=0 WHERE horariosLocales.idHorario=old.idHorario AND horariosLocales.idLocal=old.idLocal;\n" +
                    "END");

            //Trigger de relacion semantica
            db.execSQL("CREATE TRIGGER semantico_reservacion_locales\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT count(idLocal) FROM reservacion WHERE (idEvento = NEW.idEvento)) =5)\n" +
                    "THEN RAISE(ABORT, 'No se puede reservar mas de 5 locales para el evento')\n" +
                    "END;\n" +
                    "END;\n");
            db.execSQL("CREATE TRIGGER semantico_reservacion_fechaHorario\n" +
                    "BEFORE INSERT ON reservacion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "SELECT CASE\n" +
                    "WHEN ((SELECT count(idReservacion) FROM reservacion WHERE (idPeriodoReserva = NEW.idPeriodoReserva AND idHorario=NEW.idHorario AND idLocal=NEW.idLocal)) =1)\n" +
                    "THEN RAISE(ABORT, 'No se puede hacer mas de una reserva para el mismo periodo, horario y local')\n" +
                    "END;\n" +
                    "END;\n");
            // Tablas adicionales
            //Usuario
            db.execSQL("CREATE TABLE usuario (idUsuario VARCHAR(2) PRIMARY KEY NOT NULL, nomUsuario VARCHAR(30) NOT NULL, clave VARCHAR(5) NOT NULL);");
            //OPCION CRUD
            db.execSQL("CREATE TABLE opcionCrud (idOpcion VARCHAR(3) PRIMARY KEY NOT NULL, numCrud INTEGER NOT NULL, desOpcion VARCHAR(30) NOT NULL);");
            //acceso usuario
            db.execSQL("CREATE TABLE accesoUsuario (idOpcion VARCHAR(3) NOT NULL, idUsuario VARCHAR(2) NOT NULL, "+
                    "PRIMARY KEY(idOpcion,idUsuario), "+
                    "CONSTRAINT fk_accesoUsuario_opcionCrud FOREIGN KEY (idOpcion) REFERENCES opcionCrud(idOpcion) ON DELETE CASCADE, "+
                    "CONSTRAINT fk_accesoUsuario_usuario FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario) ON DELETE CASCADE);");


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
