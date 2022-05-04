package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.Genero;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PersonaActualizarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdPersona, editNombre, editApellido, editNacimiento, editDireccion, editEmail, editTelefono;
    MaterialAutoCompleteTextView editGenero, editNacionalidad;
    Button botonActualizar, botonVaciar;

    List<Nacionalidad> arrayNacionalidad=new ArrayList<Nacionalidad>();
    List<String> arrayNacionalidadString=new ArrayList<String>();
    List<Genero> arrayGenero=new ArrayList<Genero>();
    List<String> arrayGeneroString=new ArrayList<String>();

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
        editIdPersona= findViewById(R.id.EditDUIPersona);
        editNombre = findViewById(R.id.EditNombrePersona);
        editApellido = findViewById(R.id.EditApellidoPersona);
        editGenero = findViewById(R.id.EditGeneroPersona);
        editNacimiento = findViewById(R.id.EditNacimientoPersona);
        editNacionalidad = findViewById(R.id.EditNacionalidadPersona);
        editDireccion = findViewById(R.id.EditDireccionPersona);
        editEmail = findViewById(R.id.EditEmailPersona);
        editTelefono = findViewById(R.id.EditTelefonoPersona);
        botonActualizar = (Button) findViewById(R.id.botonActualizarPersona);
        botonVaciar = (Button) findViewById(R.id.botonVaciarPersona);

        //Llenado del spinner de nacionalidad
        controlBDGustavo.open();
        arrayNacionalidad=controlBDGustavo.consultarNacionalidad();
        arrayNacionalidadString=controlBDGustavo.consultarNacionalidadString(arrayNacionalidad);
        editNacionalidad.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayNacionalidadString));

        //Llenado del spinner de genero
        controlBDGustavo.open();
        arrayGenero=controlBDGustavo.consultarGeneros();
        arrayGeneroString=controlBDGustavo.consultarGeneroString(arrayGenero);
        editGenero.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayGeneroString));

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPersona = editIdPersona.getText().toString();
                String nombre = editNombre.getText().toString();
                String apellido = editApellido.getText().toString();
                String genero = editGenero.getText().toString();
                String nacimiento = editNacimiento.getText().toString();
                String nacionalidad = editNacionalidad.getText().toString();
                String direccion = editDireccion.getText().toString();
                String email = editEmail.getText().toString();
                String telefono = editTelefono.getText().toString();
                String insertarRegistros;
                boolean verdadero=true;
                if(nombre.isEmpty()||apellido.isEmpty()||genero.isEmpty()||nacimiento.isEmpty()||nacionalidad.isEmpty()||direccion.isEmpty()||email.isEmpty()||telefono.isEmpty()){
                    Toast.makeText(PersonaActualizarActivity.this, "Debe completar los campos para registrar una persona!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idPersona.length()<9){
                        Toast.makeText(PersonaActualizarActivity.this, "El DUI debe contener 9 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else if(telefono.length()<8){
                        Toast.makeText(PersonaActualizarActivity.this, "El número de teléfono debe contener 8 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else{
                        if(!(validarEmail(email))){
                            Toast.makeText(PersonaActualizarActivity.this, "El email no es válido!", Toast.LENGTH_SHORT).show();
                            verdadero=false;
                        }
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
                            persona.setGenero(arrayGenero.get(arrayGeneroString.indexOf(editGenero.getText().toString())).getIdGenero());
                            persona.setNacimiento(nacimiento);
                            persona.setNacionalidad(arrayNacionalidad.get(arrayNacionalidadString.indexOf(editNacionalidad.getText().toString())).getCodNac());
                            persona.setDireccion(direccion);
                            persona.setEmail(email);
                            persona.setTelefono(telefono);

                            controlBDGustavo.open();
                            insertarRegistros = controlBDGustavo.actualizarPersona(persona);
                            controlBDGustavo.close();
                            Toast.makeText(PersonaActualizarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                            if(insertarRegistros=="Registro duplicado!"){
                                //Limpiamos los campos
                                editIdPersona.setText("");
                            }else{
                                //Limpiamos los campos
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
                editGenero.setText("");
                editNacimiento.setText("");
                editNacionalidad.setText("");
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
    private boolean validarEmail(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}