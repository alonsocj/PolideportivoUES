package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaActualizarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    EditText editIdPersona, editNombre, editApellido, editNacimiento, editDireccion, editEmail, editTelefono;
    RadioButton editGeneroM,editGeneroF;
    Button botonActualizar, botonVaciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_actualizar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        //Fecha de nacimiento
        Calendar calendar= Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        //Persona
        editIdPersona= (EditText) findViewById(R.id.EditDUIPersona);
        editNombre = (EditText) findViewById(R.id.EditNombrePersona);
        editApellido = (EditText) findViewById(R.id.EditApellidoPersona);
        editGeneroM = (RadioButton) findViewById(R.id.rbMasculino);
        editGeneroF = (RadioButton) findViewById(R.id.rbFemenino);
        editNacimiento = (EditText) findViewById(R.id.EditNacimientoPersona);
        editDireccion = (EditText) findViewById(R.id.EditDireccionPersona);
        editEmail = (EditText) findViewById(R.id.EditEmailPersona);
        editTelefono = (EditText) findViewById(R.id.EditTelefonoPersona);
        botonActualizar = (Button) findViewById(R.id.botonActualizarPersona);
        botonVaciar = (Button) findViewById(R.id.botonVaciarPersona);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String generoM = editGeneroM.getText().toString();
                String generoF = editGeneroF.getText().toString();
                String insertarRegistros;
                boolean verdadero=true;

                String idPersona = editIdPersona.getText().toString();
                String nombre = editNombre.getText().toString();
                String apellido = editApellido.getText().toString();
                String genero = generoM+generoF;
                String nacimiento = editNacimiento.getText().toString();
                String direccion = editDireccion.getText().toString();
                String email = editEmail.getText().toString();
                String telefono = editTelefono.getText().toString();

                if(nombre.isEmpty()||apellido.isEmpty()||genero.isEmpty()||nacimiento.isEmpty()||direccion.isEmpty()||email.isEmpty()||telefono.isEmpty()){
                    Toast.makeText(PersonaActualizarActivity.this, "Debe completar los campos para registrar una persona!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idPersona.length()<9){
                        Toast.makeText(PersonaActualizarActivity.this, "El DUI debe contener 9 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else if(telefono.length()<8){
                        Toast.makeText(PersonaActualizarActivity.this, "El número de teléfono debe contener 8 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }
                    if(verdadero){
                        String [] fecha=nacimiento.split("/");
                        int anio=Integer.parseInt(fecha[2]);
                        int edad=year-anio;
                        if(edad<18){
                            Toast.makeText(PersonaActualizarActivity.this, "La persona a registrar debe ser mayor de edad!", Toast.LENGTH_SHORT).show();
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

                            controlBDGustavo.open();
                            insertarRegistros = controlBDGustavo.actualizarPersona(persona);
                            controlBDGustavo.close();
                            Toast.makeText(PersonaActualizarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

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
                DatePickerDialog datePickerDialog=new DatePickerDialog(PersonaActualizarActivity.this, new DatePickerDialog.OnDateSetListener() {
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