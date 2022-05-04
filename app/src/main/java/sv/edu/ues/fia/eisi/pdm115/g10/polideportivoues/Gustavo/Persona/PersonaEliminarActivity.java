package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaEliminarActivity extends AppCompatActivity {

    ControlBDGustavo helper;
    TextInputEditText editIdPersona;
    Button botonEliminar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_eliminar);
        helper = new ControlBDGustavo(this);
        editIdPersona = findViewById(R.id.EditDUIPersona);
        botonEliminar = findViewById(R.id.botonEliminarPersona);
        botonVaciar = findViewById(R.id.botonVaciarPersona);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registrosEliminados;
                Persona persona = new Persona();
                persona.setIdPersona(editIdPersona.getText().toString());
                AlertDialog.Builder confirmacion=new AlertDialog.Builder(PersonaEliminarActivity.this);
                final String[] registrosEliminadosCascada = new String[1];

                if(helper.verificarExisPersona(persona)){
                    if(helper.verificarPersonasCascada(persona)){

                        confirmacion.setMessage("Se han encontrado registros asociados a la persona en la tabla reservación. ¿Desea eliminarlos también?")
                                .setCancelable(false)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        helper.open();
                                        registrosEliminadosCascada[0] = helper.eliminarPersonasCascada(persona);
                                        helper.close();

                                        Toast.makeText(PersonaEliminarActivity.this, registrosEliminadosCascada[0], Toast.LENGTH_SHORT).show();
                                        editIdPersona.setText("");
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(PersonaEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }else{
                        helper.open();
                        registrosEliminadosCascada[0] = helper.eliminarPersona(persona);
                        helper.close();
                        Toast.makeText(PersonaEliminarActivity.this, registrosEliminadosCascada[0], Toast.LENGTH_SHORT).show();
                        editIdPersona.setText("");
                    }
                }else{
                    Toast.makeText(PersonaEliminarActivity.this, "Registro no existe!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdPersona.setText("");
            }
        });
    }
}