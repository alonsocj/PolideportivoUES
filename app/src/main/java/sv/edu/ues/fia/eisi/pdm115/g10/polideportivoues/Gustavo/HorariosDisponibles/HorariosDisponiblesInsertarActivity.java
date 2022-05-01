package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesInsertarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    EditText editIdHora,editDia, editHora;
    Button botonAgregar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_insertar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        editIdHora=(EditText) findViewById(R.id.EditIdHorariosDisponibles);
        editDia=(EditText) findViewById(R.id.EditDia);
        editHora=(EditText) findViewById(R.id.EditHora);
        botonAgregar = (Button) findViewById(R.id.botonAgregarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idHora = editIdHora.getText().toString();
                String dia = editDia.getText().toString();
                String hora = editHora.getText().toString();
                boolean verdadero=true;
                String insertarRegistros;

                if(idHora.isEmpty()||dia.isEmpty()||hora.isEmpty()){
                    Toast.makeText(HorariosDisponiblesInsertarActivity.this, "Debe completar los campos para registrar un horario!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idHora.length()!=2){
                        Toast.makeText(HorariosDisponiblesInsertarActivity.this, "El id de la disponibilidad debe contener 2 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else if(hora.length()!=4){
                        Toast.makeText(HorariosDisponiblesInsertarActivity.this, "El id de la hora debe contener 4 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }
                    if(verdadero){
                        HorariosDisponibles horarioDisponible = new HorariosDisponibles();
                        horarioDisponible.setIdHorario(idHora);
                        horarioDisponible.setHora(hora);
                        horarioDisponible.setDia(dia);
                        controlBDGustavo.open();
                        insertarRegistros = controlBDGustavo.insertarHorarioDisponible(horarioDisponible);
                        controlBDGustavo.close();
                        Toast.makeText(HorariosDisponiblesInsertarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                        //Limpiamos los campos
                        editIdHora.setText("");
                        editHora.setText("");
                        editDia.setText("");
                    }
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdHora.setText("");
                editHora.setText("");
                editDia.setText("");
            }
        });

    }

}