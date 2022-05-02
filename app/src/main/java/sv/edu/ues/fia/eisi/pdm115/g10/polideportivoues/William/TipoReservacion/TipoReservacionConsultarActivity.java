package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalConsultarActivity;

public class TipoReservacionConsultarActivity extends AppCompatActivity {
    ControlBDG10 helper;
    EditText editIdTipoReservacion;
    EditText editNombreTipoReservacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_consultar);
        helper = new ControlBDG10(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
        editNombreTipoReservacion = findViewById(R.id.EditNombreTipoReservacion);
    }
    public void consultarTipoReservacion(View v){
        String idTipoReservacion=editIdTipoReservacion.getText().toString();
        helper.open();
        TipoReservacion tipoReservacion = helper.consultarTipoReservacion(idTipoReservacion);
        helper.close();
        if(tipoReservacion == null){
            Toast.makeText(TipoReservacionConsultarActivity.this, "Tipo de reservacion con Id " + idTipoReservacion + " No encontrado", Toast.LENGTH_SHORT).show();
        }else{
            editNombreTipoReservacion.setText(tipoReservacion.getNomTipoR());
        }
    }
    public void limpiar(View v){
        editIdTipoReservacion.setText("");
        editNombreTipoReservacion.setText("");
    }
}