package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class NacionalidadEliminarActivity extends AppCompatActivity {
    TextInputEditText editIdNac;
    ControlBDCarolina helper;
    Button botonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacionalidad_eliminar);
        helper = new ControlBDCarolina(this);

        editIdNac = (TextInputEditText) findViewById(R.id.idNacionalidad);
        botonEliminar = (Button) findViewById(R.id.botonEliminarNacionalidad);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNacionalidad=editIdNac.getText().toString();

                final String[] registrosEliminados = new String[1];
                Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setCodNac(editIdNac.getText().toString());
                AlertDialog.Builder confirmacion=new AlertDialog.Builder(NacionalidadEliminarActivity.this);

                if(idNacionalidad.isEmpty()){
                    Toast.makeText(NacionalidadEliminarActivity.this, "Debe completar los campos para eliminar una nacionalidad!", Toast.LENGTH_SHORT).show();
                }else if(idNacionalidad.length()!=2){
                    Toast.makeText(NacionalidadEliminarActivity.this, "El c??digo debe contener 2 car??cteres", Toast.LENGTH_SHORT).show();
                }else{
                    if(helper.verificarExisNacionalidad(nacionalidad)){
                        if(helper.verificarNacionalidadCascada(nacionalidad)){
                            confirmacion.setMessage("No se puede eliminar la nacionalidad. Se han encontrado nacionalidades asociadas con la tabla de personas.")
                                    .setTitle("Advertencia")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create();
                            confirmacion.show();
                        }else{
                            helper.open();
                            registrosEliminados[0] = helper.eliminarNacionalidad(nacionalidad); //Elimino solo a nacionalidad
                            helper.close();

                            Toast.makeText(NacionalidadEliminarActivity.this, registrosEliminados[0], Toast.LENGTH_SHORT).show();
                            editIdNac.setText("");
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "El c??digo de nacionalidad no existe!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void limpiar(View v) {
        editIdNac.setText("");
    }
}