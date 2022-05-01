package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaInsertarActivity extends AppCompatActivity {

    ControlBDG10 controlBDG10;
    EditText editIdPersona, editNombre, editApellido, editNacimiento, editDireccion, editEmail, editTelefono;
    Spinner editGenero;
    Button botonAgregar, botonVaciar;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_insertar);

        //Control de la base de datos
        controlBDG10 = new ControlBDG10(this);

        //Fecha de nacimiento
        Calendar calendar= Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        //Persona
        editIdPersona= (EditText) findViewById(R.id.EditDUIPersona);
        editNombre = (EditText) findViewById(R.id.EditNombrePersona);
        editApellido = (EditText) findViewById(R.id.EditApellidoPersona);
        editGenero = (Spinner) findViewById(R.id.EditGeneroPersona);
        editNacimiento = (EditText) findViewById(R.id.EditNacimientoPersona);
        editDireccion = (EditText) findViewById(R.id.EditDireccionPersona);
        editEmail = (EditText) findViewById(R.id.EditEmailPersona);
        editTelefono = (EditText) findViewById(R.id.EditTelefonoPersona);
        botonAgregar = (Button) findViewById(R.id.botonAgregarPersona);
        botonVaciar = (Button) findViewById(R.id.botonVaciarPersona);

        //Cargamos los géneros
       ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.GeneroPersona, android.R.layout.simple_spinner_item);
       editGenero.setAdapter(adapter);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPersona = editIdPersona.getText().toString();
                String nombre = editNombre.getText().toString();
                String apellido = editApellido.getText().toString();
                String genero = editGenero.getSelectedItem().toString();
                String nacimiento = editNacimiento.getText().toString();
                String direccion = editDireccion.getText().toString();
                String email = editEmail.getText().toString();
                String telefono = editTelefono.getText().toString();
                String insertarRegistros;

                if(nombre.isEmpty()||apellido.isEmpty()||genero.isEmpty()||nacimiento.isEmpty()||direccion.isEmpty()||email.isEmpty()||telefono.isEmpty()){
                    Toast.makeText(PersonaInsertarActivity.this, "Debe completar los campos para registrar una persona!", Toast.LENGTH_SHORT).show();
                }else{
                    String [] fecha=nacimiento.split("/");
                    int anio=Integer.parseInt(fecha[2]);
                    int edad=year-anio;
                    if(edad<18){
                        Toast.makeText(PersonaInsertarActivity.this, "La persona a registrar debe ser mayor de edad!", Toast.LENGTH_SHORT).show();
                    }else{
                        Persona persona = new Persona();
                        persona.setIdPersona(idPersona);
                        persona.setNombre(nombre);
                        persona.setApellido(apellido);
                        persona.setGenero(genero);
                        persona.setNacimiento(nacimiento);
                        persona.setDireccion(direccion);
                        persona.setEmail(email);
                        persona.setTelefono(telefono);
                        controlBDG10.open();
                        insertarRegistros = controlBDG10.insertarPersona(persona);
                        controlBDG10.close();
                        Toast.makeText(PersonaInsertarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                        //Limpiamos los campos
                        editIdPersona.setText("");
                        editNombre.setText("");
                        editApellido.setText("");
                        editNacimiento.setText("");
                        editDireccion.setText("");
                        editEmail.setText("");
                        editTelefono.setText("");
                    }
                }
            }
        });
        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdPersona.setText("");
                editNombre.setText("");
                editApellido.setText("");
                editNacimiento.setText("");
                editDireccion.setText("");
                editEmail.setText("");
                editTelefono.setText("");
            }
        });

        editNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(PersonaInsertarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        editNacimiento.setText(date);
                    }
                },year-18,month,day);
                datePickerDialog.show();
            }
        });
    }
}