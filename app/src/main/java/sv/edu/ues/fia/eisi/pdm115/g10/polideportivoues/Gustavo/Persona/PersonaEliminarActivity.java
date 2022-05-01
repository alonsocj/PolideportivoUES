package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaEliminarActivity extends AppCompatActivity {

    ControlBDGustavo helper;
    EditText editIdPersona;
    Button botonEliminar, botonVaciar;;
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
                helper.open();
                registrosEliminados = helper.eliminarPersona(persona);
                helper.close();
                Toast.makeText(PersonaEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
                editIdPersona.setText("");
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