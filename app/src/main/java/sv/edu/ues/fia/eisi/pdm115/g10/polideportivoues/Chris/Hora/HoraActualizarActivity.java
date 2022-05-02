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

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HoraActualizarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    EditText editHoraid, editHoraInicio, editHoraFin;
    Button botonActualizarHora, botonActualizarHoradeInicio, getBotonActualizarHoradeFin;
    int horas, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_actualizar);
        controlBDChristian = new ControlBDChristian(this);

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

                String id = editHoraid.getText().toString();
                String inicio = editHoraInicio.getText().toString();
                String fin = editHoraFin.getText().toString();

                boolean verdadero=true;
                if(id.isEmpty() || inicio.isEmpty() || fin.isEmpty()){
                    Toast.makeText(HoraActualizarActivity.this, "Para agregar una hora debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    if (inicio.equals(fin)) {
                        Toast.makeText(HoraActualizarActivity.this, "Debe de ingresar horas diferentes!", Toast.LENGTH_SHORT).show();
                    } else if (inicio.compareTo(fin) > 0) {
                        Toast.makeText(HoraActualizarActivity.this, "La hora de inicio debe ser anterior a la hora de finalizacion", Toast.LENGTH_SHORT).show();
                        verdadero = false;
                    } else {
                        hora.setIdHora(id);
                        hora.setHoraInicio(inicio);
                        hora.setHoraFin(fin);

                        controlBDChristian.open();
                        String actuEstado = controlBDChristian.modificarHora(hora);
                        controlBDChristian.close();

                        Toast.makeText(HoraActualizarActivity.this, actuEstado, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}