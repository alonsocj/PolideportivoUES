package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class NacionalidadInsertarActivity extends AppCompatActivity {
    TextInputEditText editIdNac, editNacionalidad;
    ControlBDCarolina helper;
    Button botonAgregar;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacionalidad_insertar);
        helper = new ControlBDCarolina(this);

        editIdNac = (TextInputEditText) findViewById(R.id.idNacionalidad);
        editNacionalidad = (TextInputEditText) findViewById(R.id.nacionalidad);
        botonAgregar = (Button) findViewById(R.id.botonAgregarNacionalidad);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idNacionalidad=editIdNac.getText().toString();
                String nac=editNacionalidad.getText().toString();

                boolean verdadero=true;
                String regInsertados;

                if(idNacionalidad.isEmpty()||nac.isEmpty()){
                    Toast.makeText(NacionalidadInsertarActivity.this, "Debe completar los campos para ingresar una nacionalidad!", Toast.LENGTH_SHORT).show();
                }else {
                    if(idNacionalidad.length()!=2){
                        Toast.makeText(NacionalidadInsertarActivity.this, "El código debe contener 2 caracteres", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else{
                        Nacionalidad nacionalidad=new Nacionalidad();
                        nacionalidad.setCodNac(idNacionalidad);
                        nacionalidad.setNacionalidad(nac);

                        helper.open();
                        regInsertados=helper.insertarNacionalidad(nacionalidad);
                        helper.close();
                        Toast.makeText(NacionalidadInsertarActivity.this, regInsertados, Toast.LENGTH_SHORT).show();

                        //Limpiamos los campos
                        editIdNac.setText("");
                        editNacionalidad.setText("");
                    }
                }
            }
        });
    }
    public void limpiar(View v) {
        editIdNac.setText("");
        editNacionalidad.setText("");
    }
}