package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class NacionalidadConsultarActivity extends AppCompatActivity {

    TextInputEditText editIdNac, editNacionalidad;
    ControlBDCarolina helper;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacionalidad_consultar);
        helper = new ControlBDCarolina(this);
        editIdNac = (TextInputEditText) findViewById(R.id.idNacionalidad);
        editNacionalidad = (TextInputEditText) findViewById(R.id.nacionalidad);
    }
    public void consultarNacionalidades(View v) {
        helper.open();
        Nacionalidad nac =helper.consultarNacionalidad(editIdNac.getText().toString());
        helper.close();
        if (editIdNac.getText().toString().isEmpty()) {
            Toast.makeText(NacionalidadConsultarActivity.this, "Ingrese un código de nacionalidad para hacer la consulta ", Toast.LENGTH_LONG).show();
        }else{
            if(nac == null)
                Toast.makeText(NacionalidadConsultarActivity.this, "Nacionalidad con código " + editIdNac.getText().toString() +" no encontrado", Toast.LENGTH_LONG).show();
            else{
                editNacionalidad.setText(nac.getNacionalidad());
            }
        }
    }
    public void limpiar(View v) {
        editIdNac.setText("");
        editNacionalidad.setText("");
    }

}