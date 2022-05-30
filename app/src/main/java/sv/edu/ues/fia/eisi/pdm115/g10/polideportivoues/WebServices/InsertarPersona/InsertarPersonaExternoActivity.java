package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarPersona;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.Genero;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

@SuppressLint("NewApi")
public class InsertarPersonaExternoActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdPersona, editNombre, editApellido, editNacimiento, editDireccion, editEmail, editTelefono;
    MaterialAutoCompleteTextView editGenero, editNacionalidad;
    Button botonAgregar, botonVaciar;

    //Arrays
    List<Nacionalidad> arrayNacionalidad=new ArrayList<Nacionalidad>();
    List<String> arrayNacionalidadString=new ArrayList<String>();
    List<Genero> arrayGenero=new ArrayList<Genero>();
    List<String> arrayGeneroString=new ArrayList<String>();

    //Fecha de nacimiento
    Integer year,month, day;

    private final String urlHosting = "https://grupo10pdm2022.000webhostapp.com/ws_insert_persona.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_insertar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        //Fecha de nacimiento
        Calendar calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

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
        botonAgregar = (Button) findViewById(R.id.botonAgregarPersona);
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

        botonAgregar.setOnClickListener(new View.OnClickListener() {
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
                boolean verdadero=true;
                String insertarRegistros;
                String url="", urlDireccion="",urlNombre="",urlApellido="";
                JSONObject datosPersona = new JSONObject();
                JSONObject persona1= new JSONObject();

                if(nombre.isEmpty()||apellido.isEmpty()||genero.isEmpty()||nacimiento.isEmpty()||nacionalidad.isEmpty()||direccion.isEmpty()||email.isEmpty()||telefono.isEmpty()){
                    Toast.makeText(InsertarPersonaExternoActivity.this, "Debe completar los campos para registrar una persona!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idPersona.length()!=9){
                        Toast.makeText(InsertarPersonaExternoActivity.this, "El DUI debe contener 9 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else if(telefono.length()!=8){
                        Toast.makeText(InsertarPersonaExternoActivity.this, "El número de teléfono debe contener 8 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else{
                        if(!(validarEmail(email))){
                            Toast.makeText(InsertarPersonaExternoActivity.this, "El email no es válido!", Toast.LENGTH_SHORT).show();
                            verdadero=false;
                        }
                    }
                    //Prueba
                    if(verdadero){
                        String [] fecha=nacimiento.split("/");
                        int anio=Integer.parseInt(fecha[2]);
                        int edad=year-anio;
                        if(edad<18){
                            Toast.makeText(InsertarPersonaExternoActivity.this, "La persona a registrar debe ser mayor de edad!", Toast.LENGTH_SHORT).show();
                        }else{
                            Persona persona = new Persona();
                            persona.setIdPersona(idPersona);
                            persona.setNombre(nombre);
                            persona.setApellido(apellido);
                            persona.setGenero(arrayGenero.get(arrayGeneroString.indexOf(editGenero.getText().toString())).getIdGenero().toString());
                            persona.setNacimiento(nacimiento);
                            persona.setNacionalidad(arrayNacionalidad.get(arrayNacionalidadString.indexOf(editNacionalidad.getText().toString())).getCodNac());
                            persona.setDireccion(direccion);
                            persona.setEmail(email);
                            persona.setTelefono(telefono);

                            //Validando textos en la URL
                            try {
                                urlNombre=URLEncoder.encode(nombre,"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            try {
                                urlApellido=URLEncoder.encode(apellido,"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            try {
                                urlDireccion=URLEncoder.encode(direccion,"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            //Almacenando en la base de datos local
                            controlBDGustavo.open();
                            insertarRegistros = controlBDGustavo.insertarPersona(persona);
                            controlBDGustavo.close();
                            Toast.makeText(InsertarPersonaExternoActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                            //Almacenando en el webhosting
                            url = urlHosting+ "?IDPERSONA=" + persona.getIdPersona() + "&NOMBRE=" + urlNombre + "&APELLIDO=" + urlApellido + "&IDGENERO=" + persona.getGenero() +"&NACIMIENTO="+persona.getNacimiento()+"&CODNAC="+persona.getNacionalidad()+ "&DIRECCION="+urlDireccion+"&EMAIL="+persona.getEmail()+"&TELEFONO="+persona.getTelefono();
                            PersonaService.insertarPersonaExterno(url, InsertarPersonaExternoActivity.this);

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
                DatePickerDialog datePickerDialog=new DatePickerDialog(InsertarPersonaExternoActivity.this, new DatePickerDialog.OnDateSetListener() {
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