package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

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

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesConsultarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdHora,editDia, editHora;
    Button botonConsultar, botonVaciar;
    List<Hora> arrayHoras=new ArrayList<Hora>();
    List<String> arrayHorasString=new ArrayList<String>();
    List<Dia> arrayDias=new ArrayList<Dia>();
    List<String> arrayDiasString=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_consultar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        //Horario Disponible
        editIdHora=findViewById(R.id.EditIdHorariosDisponibles);
        editDia=findViewById(R.id.SpinnerDia);
        editHora= findViewById(R.id.SpinnerHora);
        botonConsultar = (Button) findViewById(R.id.botonConsultarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        //Llenado del spinner de horas
        controlBDGustavo.open();
        arrayHoras=controlBDGustavo.consultarHoras();
        arrayHorasString=controlBDGustavo.consultarHorasString(arrayHoras);

        //Llenado del spinner de dias
        controlBDGustavo.open();
        arrayDias=controlBDGustavo.consultarDias();
        arrayDiasString=controlBDGustavo.consultarDiasString(arrayDias);

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editIdHora.getText().toString().isEmpty()){
                    Toast.makeText(HorariosDisponiblesConsultarActivity.this, "Debe completar los campos para consultar una disponibilidad!", Toast.LENGTH_SHORT).show();
                }else {
                    controlBDGustavo.open();
                    HorariosDisponibles horariosDisponibles = controlBDGustavo.consultarHorarioDisponible(editIdHora.getText().toString());
                    controlBDGustavo.close();

                    /*Verificación que exista la Disponibilidad de Horario*/
                    if (horariosDisponibles == null) {
                        Toast.makeText(HorariosDisponiblesConsultarActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                        editHora.setText("");
                        editDia.setText("");
                    } else {
                        //Recuperamos la hora
                        for (int i = 0; i < arrayHoras.size(); i++) {
                            Hora horasArray;
                            horasArray = arrayHoras.get(i);
                            if (horasArray.getIdHora().equals(horariosDisponibles.getHora())) {
                                String horario = horasArray.getHoraInicio() + " - " + horasArray.getHoraFin();
                                editHora.setText(horario);
                                editDia.setText(horariosDisponibles.getDia());
                            }
                        }
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