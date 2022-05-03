package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoReservacionInsertarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    EditText editIdTipoReservacion;
    EditText editNombreTipoReservacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_insertar);
        helper = new ControlBDG10William(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
        editNombreTipoReservacion = findViewById(R.id.EditNombreTipoReservacion);
    }
    public void insertarTipoReservacion(View v){
        String idTipoReservacion=editIdTipoReservacion.getText().toString();
        String nombreTipoReservacion=editNombreTipoReservacion.getText().toString();
        if (idTipoReservacion.isEmpty()||nombreTipoReservacion.isEmpty()){
            String mensaje;
            if (idTipoReservacion.isEmpty()&&nombreTipoReservacion.isEmpty()) {
                mensaje = "Los campos estan vacios, por favor completelos";
            }else{
                if (idTipoReservacion.isEmpty()){
                    mensaje = "El tipo de reservacion no se puede ingresar, no se ha digitado el id";
                }else{
                    mensaje = "El tipo de reservacion no se puede ingresar, no se ha digitado el nombre";
                }
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else {
            String regInsertados;
            TipoReservacion tipoReservacion = new TipoReservacion();
            tipoReservacion.setIdTipoR(idTipoReservacion);
            tipoReservacion.setNomTipoR(nombreTipoReservacion);
            helper.open();
            regInsertados = helper.ingresarTipoReservacion(tipoReservacion);
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiar(View v){
        editIdTipoReservacion.setText("");
        editNombreTipoReservacion.setText("");
    }
}