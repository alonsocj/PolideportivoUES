package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalEliminarActivity;

public class TipoReservacionEliminarActivity extends AppCompatActivity {
    ControlBDG10 helper;
    EditText editIdTipoReservacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_eliminar);
        helper = new ControlBDG10(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
    }
    public void eliminarTipoReservacion(View v){
        String registrosEliminados;
        TipoReservacion tipoReservacion = new TipoReservacion();
        tipoReservacion.setIdTipoR(editIdTipoReservacion.getText().toString());
        helper.open();
        registrosEliminados = helper.eliminarTipoReservacion(tipoReservacion);
        helper.close();
        Toast.makeText(TipoReservacionEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editIdTipoReservacion.setText("");
    }
}