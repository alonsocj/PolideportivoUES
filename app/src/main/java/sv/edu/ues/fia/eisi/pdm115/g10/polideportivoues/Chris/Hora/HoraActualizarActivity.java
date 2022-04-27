package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HoraActualizarActivity extends AppCompatActivity {

    ControlBDG10 controlBDG10;
    EditText editHoraid, editHoraInicio, editHoraFin;
    Button botonActualizarHora, botonActualizarHoradeInicio, getBotonActualizarHoradeFin;
    int horas, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_actualizar);
        controlBDG10 = new ControlBDG10(this);

        editHoraid = (EditText) findViewById(R.id.EditIdHoraActualizar);
        editHoraInicio = (EditText) findViewById(R.id.EditHoraInicioActualizar);
        editHoraFin = (EditText) findViewById(R.id.EditHoraFinActualizar);
        botonActualizarHora = (Button) findViewById(R.id.botonActualizarHora);
        botonActualizarHoradeInicio = (Button) findViewById(R.id.horaInicioPickerActualizar);
        getBotonActualizarHoradeFin = (Button) findViewById(R.id.horaFinalizarPickerActualizar);

        botonActualizarHoradeInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int seleccionarHora, int seleccionarMinutos) {
                        horas = seleccionarHora;
                        minutos = seleccionarMinutos;
                        editHoraInicio.setText(String.format(Locale.getDefault(),"%02d:%02d",horas,minutos));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(HoraActualizarActivity.this,style,onTimeSetListener,horas,minutos,true);
                timePickerDialog.show();
            }
        });

        getBotonActualizarHoradeFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int seleccionarHoraFin, int seleccionarMinutoFin) {
                        /*Reutilizo las variables para que inicie desde el horario seleccionado anteriormente*/
                        horas = seleccionarHoraFin;
                        minutos = seleccionarMinutoFin;
                        editHoraFin.setText(String.format(Locale.getDefault(),"%02d:%02d",horas,minutos));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(HoraActualizarActivity.this,style,onTimeSetListener,horas,minutos,true);
                timePickerDialog.show();
            }
        });

        /*Funcionalidad de modificar*/

        botonActualizarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hora hora = new Hora();
                hora.setIdHora(editHoraid.getText().toString());
                hora.setHoraInicio(editHoraInicio.getText().toString());
                hora.setHoraFin(editHoraFin.getText().toString());

                controlBDG10.open();
                String actuEstado = controlBDG10.modificarHora(hora);
                controlBDG10.close();

                Toast.makeText(HoraActualizarActivity.this, actuEstado, Toast.LENGTH_SHORT).show();

            }
        });



    }
}