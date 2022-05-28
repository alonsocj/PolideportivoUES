package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login;

import android.database.Cursor;
import android.os.AsyncTask;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.MainActivity;

public class ProcesarDatos extends AsyncTask<Object, Void, Boolean> {
    private Comunicacion comunicacion;

    public ProcesarDatos(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
    }

    @Override
    protected void onPreExecute() {
        comunicacion.toggleProgressBar(true);
    }

    @Override
    protected void onPostExecute(Boolean status) {
        if (status) {
            this.comunicacion.lanzarActividad(MainActivity.class);
            this.comunicacion.showMessage("Login correcto");

        } else {
            this.comunicacion.toggleProgressBar(false);
            this.comunicacion.showMessage("Datos incorrectos");
        }
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        try {
            Thread.sleep((int) objects[2]);
            String user = (String) objects[0];
            String clave = (String) objects[1];
            Cursor cursor = (Cursor) objects[3];
            for(int i = 0; i < cursor.getCount(); i++) {
                if (user.equals(cursor.getString(1)) && clave.equals(cursor.getString(2))) {
                    return true;
                }
                cursor.moveToNext();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return false;
    }

}
