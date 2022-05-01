package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaConsultarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    EditText editIdPersona, editNombre, editApellido, editGenero, editNacimiento, editDireccion, editEmail, editTelefono;
    Button botonConsultar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_consultar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        //Persona
        editIdPersona= (EditText) findViewById(R.id.EditDUIPersona);
        editNombre = (EditText) findViewById(R.id.EditNombrePersona);
        editApellido = (EditText) findViewById(R.id.EditApellidoPersona);
        editGenero = (EditText) findViewById(R.id.EditGeneroPersona);
        editNacimiento = (EditText) findViewById(R.id.EditNacimientoPersona);
        editDireccion = (EditText) findViewById(R.id.EditDireccionPersona);
        editEmail = (EditText) findViewById(R.id.EditEmailPersona);
        editTelefono = (EditText) findViewById(R.id.EditTelefonoPersona);
        botonConsultar = (Button) findViewById(R.id.botonConsultarPersona);
        botonVaciar = (Button) findViewById(R.id.botonVaciarPersona);

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlBDGustavo.open();
                Persona persona = controlBDGustavo.consultarPersona(editIdPersona.getText().toString());
                controlBDGustavo.close();

                /*Verificación que exista la Persona*/
                if(persona == null){
                    Toast.makeText(PersonaConsultarActivity.this, "Registro no encontrado!", Toast.LENGTH_SHORT).show();
                    editNombre.setText("");
                    editApellido.setText("");
                    editGenero.setText("");
                    editNacimiento.setText("");
                    editDireccion.setText("");
                    editEmail.setText("");
                    editTelefono.setText("");
                }else{
                    editNombre.setText(persona.getNombre());
                    editApellido.setText(persona.getApellido());
                    editGenero.setText(persona.getGenero());
                    editNacimiento.setText(persona.getNacimiento());
                    editDireccion.setText(persona.getDireccion());
                    editEmail.setText(persona.getEmail());
                    editTelefono.setText(persona.getTelefono());
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdPersona.setText("");
                editNombre.setText("");
                editApellido.setText("");
                editGenero.setText("");
                editNacimiento.setText("");
                editDireccion.setText("");
                editEmail.setText("");
                editTelefono.setText("");
            }
        });
    }
}