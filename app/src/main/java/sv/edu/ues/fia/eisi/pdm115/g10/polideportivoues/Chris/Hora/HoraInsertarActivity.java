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

public class HoraInsertarActivity extends AppCompatActivity {

    ControlBDG10 controlBDG10;
    EditText editId, editInicio, editFin;
    Button botonAgregar, botonHoradeInicio, botonHoraFinalizacion;
    int horas, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_insertar);

        controlBDG10 = new ControlBDG10(this);
        editId = (EditText) findViewById(R.id.EditIdHora);
        editInicio = (EditText) findViewById(R.id.EditHoraInicio);
        editFin = (EditText) findViewById(R.id.EditHoraFin);
        botonAgregar = (Button) findViewById(R.id.botonAgregarHora);
        botonHoradeInicio = (Button) findViewById(R.id.horaInicioPicker);
        botonHoraFinalizacion = (Button) findViewById(R.id.horaFinalizarPicker);

        botonHoradeInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int seleccionarHora, int seleccionarMinutos) {
                        horas = seleccionarHora;
                        minutos = seleccionarMinutos;
                        editInicio.setText(String.format(Locale.getDefault(),"%02d:%02d",horas,minutos));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(HoraInsertarActivity.this,style,onTimeSetListener,horas,minutos,true);
                timePickerDialog.show();
            }
        });


        botonHoraFinalizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int seleccionarHoraFin, int seleccionarMinutoFin) {
                        /*Reutilizo las variables para que inicie desde el horario seleccionado anteriormente*/
                        horas = seleccionarHoraFin;
                        minutos = seleccionarMinutoFin;
                        editFin.setText(String.format(Locale.getDefault(),"%02d:%02d",horas,minutos));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(HoraInsertarActivity.this,style,onTimeSetListener,horas,minutos,true);
                timePickerDialog.show();
            }
        });



        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                String inicio = editInicio.getText().toString();
                String fin = editFin.getText().toString();
                String insertarRegistros;

                Hora hora = new Hora();
                hora.setIdHora(id);
                hora.setHoraInicio(inicio);
                hora.setHoraFin(fin);
                controlBDG10.open();
                insertarRegistros = controlBDG10.insertarHora(hora);
                controlBDG10.close();
                Toast.makeText(HoraInsertarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();
            }
        });


    }
}