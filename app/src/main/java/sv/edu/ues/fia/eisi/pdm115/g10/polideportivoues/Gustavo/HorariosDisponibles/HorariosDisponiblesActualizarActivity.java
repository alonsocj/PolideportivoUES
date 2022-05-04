package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesActualizarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdHora;
    Button botonActualizar, botonVaciar;
    MaterialAutoCompleteTextView editHora,editDia;
    List<Hora> arrayHoras=new ArrayList<Hora>();
    List<String> arrayHorasString=new ArrayList<String>();
    List<Dia> arrayDias=new ArrayList<Dia>();
    List<String> arrayDiasString=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_actualizar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        editIdHora=findViewById(R.id.EditIdHorariosDisponibles);
        editDia=findViewById(R.id.SpinnerDia);
        editHora=findViewById(R.id.SpinnerHora);
        botonActualizar = (Button) findViewById(R.id.botonActualizarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        //Llenado del spinner de horas
        controlBDGustavo.open();
        arrayHoras=controlBDGustavo.consultarHoras();
        arrayHorasString=controlBDGustavo.consultarHorasString(arrayHoras);
        editHora.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayHorasString));

        //Llenado del spinner de dias
        controlBDGustavo.open();
        arrayDias=controlBDGustavo.consultarDias();
        arrayDiasString=controlBDGustavo.consultarDiasString(arrayDias);
        editDia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayDiasString));

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idHora = editIdHora.getText().toString();
                String dia = editDia.getText().toString();
                String hora = editHora.getText().toString();
                String insertarRegistros;

                if(idHora.isEmpty()||dia.isEmpty()||hora.isEmpty()){
                    Toast.makeText(HorariosDisponiblesActualizarActivity.this, "Debe completar los campos para registrar un horario!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idHora.length()!=6){
                        Toast.makeText(HorariosDisponiblesActualizarActivity.this, "El id debe de ser de 6 carácteres!", Toast.LENGTH_SHORT).show();
                    }else {
                        String idHoraSeleccionada=arrayHoras.get(arrayHorasString.indexOf(hora)).getIdHora();
                        String idDiaSeleccionado=arrayDias.get(arrayDiasString.indexOf(dia)).getNombreDia();

                        HorariosDisponibles horarioDisponible = new HorariosDisponibles();
                        horarioDisponible.setIdHorario(idHora);
                        horarioDisponible.setHora(idHoraSeleccionada);
                        horarioDisponible.setDia(idDiaSeleccionado);

                        controlBDGustavo.open();
                        insertarRegistros = controlBDGustavo.actualizarHorarioDisponible(horarioDisponible);
                        controlBDGustavo.close();
                        Toast.makeText(HorariosDisponiblesActualizarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                        if(insertarRegistros.equals("Registro duplicado!")){
                            //Limpiamos los campos
                            editIdHora.setText("");
                        }else{
                            //Limpiamos los campos
                            editIdHora.setText("");
                            editHora.setText("");
                            editDia.setText("");
                        }
                    }
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpiamos los campos
                editIdHora.setText("");
                editHora.setText("");
                editDia.setText("");
            }
        });
    }
}