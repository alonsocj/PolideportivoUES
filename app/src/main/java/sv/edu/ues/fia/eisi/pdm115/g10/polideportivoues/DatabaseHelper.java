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
            //tabla Dia
            db.execSQL("CREATE TABLE dia (nombreDia VARCHAR(10) PRIMARY KEY);");
            //tabla TipoEvento
            db.execSQL("CREATE TABLE tipoevento (idTipoE VARCHAR(1) PRIMARY KEY, nomTipoE VARCHAR(50));");
            //tabla TipoPago
            db.execSQL("CREATE TABLE tipopago(idPago VARCHAR(2) NOT NULL PRIMARY KEY, tipo VARCHAR(20) NOT NULL);");
            //Tabla Cobro
            db.execSQL("CREATE TABLE cobro (idCobro INTEGER NOT NULL PRIMARY KEY , idPago VARCHAR(2) NOT NULL, cantPersonas INTEGER, duracion FLOAT, precio FLOAT," +
                    " CONSTRAINT fk_cobro_tipopago FOREIGN KEY (idPago) REFERENCES tipopago(idPago) ON DELETE RESTRICT);");
            //Tabla Hora
            db.execSQL("CREATE TABLE hora (idHora VARCHAR(4) NOT NULL PRIMARY KEY, horaInicio VARCHAR(25) NOT NULL , horaFin VARCHAR(25) NOT NULL);");
            //Tabla Local
            db.execSQL("CREATE TABLE local (idLocal VARCHAR(5) NOT NULL PRIMARY KEY, nomLocal VARCHAR(50) NOT NULL, cupo INTEGER NOT NULL);");
            //Tabla TipoReservacion
            db.execSQL("CREATE TABLE tipoReservacion (idTipoR VARCHAR(1) NOT NULL PRIMARY KEY, nomTipoR VARCHAR(10) NOT NULL);");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
