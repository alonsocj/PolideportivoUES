package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoReservacionConsultarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdTipoReservacion;
    TextInputEditText editNombreTipoReservacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_consultar);
        helper = new ControlBDG10William(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
        editNombreTipoReservacion = findViewById(R.id.EditNombreTipoReservacion);
    }
    public void consultarTipoReservacion(View v){
        String idTipoReservacion=editIdTipoReservacion.getText().toString();
        if (idTipoReservacion.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            helper.open();
            TipoReservacion tipoReservacion = helper.consultarTipoReservacion(idTipoReservacion);
            helper.close();
            if(tipoReservacion == null){
                Toast.makeText(TipoReservacionConsultarActivity.this, "Tipo de reservacion con Id " + idTipoReservacion + " No encontrado", Toast.LENGTH_SHORT).show();
            }else{
                editNombreTipoReservacion.setText(tipoReservacion.getNomTipoR());
            }
        }
    }
    public void limpiar(View v){
        editIdTipoReservacion.setText("");
        editNombreTipoReservacion.setText("");
    }
}