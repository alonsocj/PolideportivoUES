package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;

public class ControlBDG10Alonso {

    private static final String[] camposTipoEvento = new String[]{"idTipoE","nomTipoE"};
    private static final String[] camposCobro = new String[]{"idCobro","idPago","cantPersonas","duracion","precio"};

    private final DatabaseHelper DBhelper; /*Esta es la clase que contiene todas las instrucciones SQL*/
    private SQLiteDatabase db;

    public ControlBDG10Alonso(Context ctx) {
        DBhelper = new DatabaseHelper(ctx);
    }

    public void open() throws SQLException {
        db = DBhelper.getWritableDatabase();
        return;
    }

    public void close() {
        DBhelper.close();
    }


    /*
     * Inicio de funcionalidades de TIPO EVENTO
     */

    public String insertar(TipoEvento tipoEvento){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues values = new ContentValues();
        values.put("idTipoE", tipoEvento.getIdTipoE());
        values.put("nomTipoE", tipoEvento.getNombreTipoE());
        contador = db.insert("tipoevento",null,values);
        if(contador==-1 || contador==0){
            regInsertados="Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public String actualizar(TipoEvento tipoEvento){
        if (verificarIntegridadDeDatos(tipoEvento,1)) {
            String[] id = {tipoEvento.getIdTipoE()};
            ContentValues values = new ContentValues();
            values.put("nomTipoE", tipoEvento.getNombreTipoE());
            db.update("tipoevento", values, "idTipoE=?", id);
            return "Registro de Tipo Evento actualizado correctamente";
        }else{
            return "Registro de Tipo Evento inexistente";
        }
    }

    public String eliminar(TipoEvento tipoEvento) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where = "idTipoE = '"+tipoEvento.getIdTipoE()+"'";
        contador+=db.delete("tipoevento", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public TipoEvento consultarTipoEvento(String idTipoE){
        String[] id = {idTipoE};
        Cursor cursor = db.query("tipoevento", camposTipoEvento, "idTipoE = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoE(cursor.getString(0));
            tipoEvento.setNombreTipoE(cursor.getString(1));
            return tipoEvento;
        }else {
            return null;
        }
    }

    /*
     * FINAL de las funcionalidades de TIPO EVENTO
     */

    /*
        * INICIO DE FUNCIONALIDADES DE COBRO
     */

    public String insertar(Cobro cobro){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues values = new ContentValues();
        values.put("idCobro", cobro.getIdCobro());
        values.put("idPago", cobro.getIdPago());
        values.put("cantPersonas", cobro.getCantPersonas());
        values.put("duracion", cobro.getDuracion());
        values.put("precio", cobro.getPrecio());

        contador = db.insert("cobro",null,values);
        if(contador==-1 || contador==0){
            regInsertados="Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public Cobro consultarCobro(String idCobro){
        String[] id = {idCobro};
        Cursor cursor = db.query("cobro", camposCobro, "idCobro = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Cobro cobro = new Cobro();
            cobro.setIdCobro(cursor.getInt(0));
            cobro.setIdPago(cursor.getString(1));
            cobro.setCantPersonas(cursor.getInt(2));
            cobro.setDuracion(cursor.getFloat(3));
            cobro.setPrecio(cursor.getFloat(4));
            return cobro;
        }else {
            return null;
        }
    }

    public String actualizar(Cobro cobro){
        try {


            int id = cobro.getIdCobro();
            ContentValues values = new ContentValues();
            values.put("idPago", cobro.getIdPago());
            values.put("cantPersonas", cobro.getCantPersonas());
            values.put("duracion", cobro.getDuracion());
            values.put("precio", cobro.getPrecio());
            db.update("cobro", values, "idCobro=" + id, null);
            return "Registro de Cobro actualizado correctamente";
        }catch (Exception e){
            return "Registro de Cobro inexistente";
        }
    }

    public String eliminar(Cobro cobro) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where = "idCobro = "+cobro.getIdCobro();
        contador+=db.delete("cobro", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    /*
     * FIN DE FUNCIONALIDADES DE COBRO
     */

    private boolean verificarIntegridadDeDatos(Object valor, int relacion) throws SQLException {
        switch (relacion) {
            //Verifica que existe el tipo evento
            case 1: {
                TipoEvento tipoEventoV = (TipoEvento) valor;
                String[] id = {tipoEventoV.getIdTipoE()};
                open();
                Cursor tev = db.query("tipoevento",camposTipoEvento,"idTipoE = ?", id,null,null,null);
                return tev.moveToFirst();
            }
            default:
                return false;
        }
    }
}
