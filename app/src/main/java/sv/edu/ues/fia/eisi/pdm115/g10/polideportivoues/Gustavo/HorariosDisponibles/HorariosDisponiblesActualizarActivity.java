package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesActualizarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    EditText editIdHora;
    Button botonActualizar, botonVaciar;
    Spinner editHora,editDia;
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

        editIdHora=(EditText) findViewById(R.id.EditIdHorariosDisponibles);
        editDia=(Spinner) findViewById(R.id.SpinnerDia);
        editHora=(Spinner) findViewById(R.id.SpinnerHora);
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
                String dia = editDia.getSelectedItem().toString();
                String hora = editHora.getSelectedItem().toString();
                String insertarRegistros;

                if(idHora.isEmpty()||dia.equals("Seleccione un día")||hora.equals("Seleccione una hora")){
                    Toast.makeText(HorariosDisponiblesActualizarActivity.this, "Debe completar los campos para registrar un horario!", Toast.LENGTH_SHORT).show();
                }else{
                    String idHoraSeleccionada=arrayHoras.get(editHora.getSelectedItemPosition()-1).getIdHora();
                    String idDiaSeleccionado=arrayDias.get(editDia.getSelectedItemPosition()-1).getNombreDia();

                    HorariosDisponibles horarioDisponible = new HorariosDisponibles();
                    horarioDisponible.setIdHorario(idHora);
                    horarioDisponible.setHora(idDiaSeleccionado);
                    horarioDisponible.setDia(dia);

                    controlBDGustavo.open();
                    insertarRegistros = controlBDGustavo.actualizarHorarioDisponible(horarioDisponible);
                    controlBDGustavo.close();
                    Toast.makeText(HorariosDisponiblesActualizarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                    //Limpiamos los campos
                    editIdHora.setText("");
                    editHora.setSelection(0);
                    editDia.setSelection(0);
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpiamos los campos
                editIdHora.setText("");
                editHora.setSelection(0);
                editDia.setSelection(0);
            }
        });
    }
}