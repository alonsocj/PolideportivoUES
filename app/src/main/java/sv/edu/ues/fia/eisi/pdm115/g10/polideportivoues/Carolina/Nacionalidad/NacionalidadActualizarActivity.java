package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class NacionalidadActualizarActivity extends AppCompatActivity {
    TextInputEditText editIdNac, editNacionalidad;
    ControlBDCarolina helper;
    Button botonActualizar;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacionalidad_actualizar);
        helper = new ControlBDCarolina(this);

        editIdNac = (TextInputEditText) findViewById(R.id.idNacionalidad);
        editNacionalidad = (TextInputEditText) findViewById(R.id.nacionalidad);
        botonActualizar = (Button) findViewById(R.id.botonActualizarNacionalidad);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idNacionalidad=editIdNac.getText().toString();
                String nac=editNacionalidad.getText().toString();

                boolean verdadero=true;
                String regInsertados;

                if(idNacionalidad.isEmpty()||nac.isEmpty()){
                    Toast.makeText(NacionalidadActualizarActivity.this, "Debe completar los campos para actualizar una nacionalidad!", Toast.LENGTH_SHORT).show();
                }else {
                    if(idNacionalidad.length()!=2){
                        Toast.makeText(NacionalidadActualizarActivity.this, "El código debe contener 2 caracteres", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else{
                        Nacionalidad nacionalidad=new Nacionalidad();
                        nacionalidad.setCodNac(idNacionalidad);
                        nacionalidad.setNacionalidad(nac);

                        helper.open();
                        regInsertados=helper.actualizarNacionalidad(nacionalidad);
                        helper.close();
                        Toast.makeText(NacionalidadActualizarActivity.this, regInsertados, Toast.LENGTH_SHORT).show();

                        //Limpiamos los campos
                        editIdNac.setText("");
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
