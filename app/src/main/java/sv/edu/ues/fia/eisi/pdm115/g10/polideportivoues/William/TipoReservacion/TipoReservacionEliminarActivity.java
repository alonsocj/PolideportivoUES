package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;


public class TipoReservacionEliminarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdTipoReservacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_eliminar);
        helper = new ControlBDG10William(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
    }
    public void eliminarTipoReservacion(View v){
        String idTipoR = editIdTipoReservacion.getText().toString();
        if (idTipoR.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else {
            String registrosEliminados;
            TipoReservacion tipoReservacion = new TipoReservacion();
            tipoReservacion.setIdTipoR(editIdTipoReservacion.getText().toString());
            helper.open();
            registrosEliminados = helper.eliminarTipoReservacion(tipoReservacion);
            helper.close();
            Toast.makeText(TipoReservacionEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
            editIdTipoReservacion.setText("");
        }
    }
    public void limpiar(View v){
        editIdTipoReservacion.setText("");
    }
}