package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.Genero;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaConsultarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdPersona, editNombre, editApellido, editGenero, editNacimiento, editNacionalidad, editDireccion, editEmail, editTelefono;
    Button botonConsultar, botonVaciar;

    //Arrays
    List<Nacionalidad> arrayNacionalidad=new ArrayList<Nacionalidad>();
    List<String> arrayNacionalidadString=new ArrayList<String>();
    List<Genero> arrayGenero=new ArrayList<Genero>();
    List<String> arrayGeneroString=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_consultar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        //Persona
        editIdPersona= findViewById(R.id.EditDUIPersona);
        editNombre = findViewById(R.id.EditNombrePersona);
        editApellido = findViewById(R.id.EditApellidoPersona);
        editGenero =  findViewById(R.id.EditGeneroPersona);
        editNacimiento = findViewById(R.id.EditNacimientoPersona);
        editNacionalidad = findViewById(R.id.EditNacionalidadPersona);
        editDireccion = findViewById(R.id.EditDireccionPersona);
        editEmail = findViewById(R.id.EditEmailPersona);
        editTelefono = findViewById(R.id.EditTelefonoPersona);
        botonConsultar = (Button) findViewById(R.id.botonConsultarPersona);
        botonVaciar = (Button) findViewById(R.id.botonVaciarPersona);

        //Llenado del spinner de nacionalidad
        controlBDGustavo.open();
        arrayNacionalidad=controlBDGustavo.consultarNacionalidad();
        arrayNacionalidadString=controlBDGustavo.consultarNacionalidadString(arrayNacionalidad);

        //Llenado del spinner de genero
        controlBDGustavo.open();
        arrayGenero=controlBDGustavo.consultarGeneros();
        arrayGeneroString=controlBDGustavo.consultarGeneroString(arrayGenero);

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
                    editNacionalidad.setText("");
                    editDireccion.setText("");
                    editEmail.setText("");
                    editTelefono.setText("");
                }else{


                    for(int i=0;i<arrayGenero.size();i++){
                        if(arrayGenero.get(i).getIdGenero().equals(persona.getGenero())){
                            editGenero.setText(arrayGenero.get(i).getGenero());
                        }
                    }
                    for(int i=0;i<arrayNacionalidad.size();i++){
                        if(arrayNacionalidad.get(i).getCodNac().equals(persona.getNacionalidad())){
                            editNacionalidad.setText(arrayNacionalidad.get(i).getNacionalidad());
                        }
                    }
                    editNombre.setText(persona.getNombre());
                    editApellido.setText(persona.getApellido());
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
                editNacionalidad.setText("");
                editDireccion.setText("");
                editEmail.setText("");
                editTelefono.setText("");
            }
        });
    }
}