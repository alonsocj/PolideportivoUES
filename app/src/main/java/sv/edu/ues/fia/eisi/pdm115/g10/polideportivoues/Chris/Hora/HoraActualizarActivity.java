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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HoraActualizarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    TextInputEditText editHoraid, editHoraInicio, editHoraFin;
    Button botonActualizarHora,botonLimpiar, botonActualizarHoradeInicio, getBotonActualizarHoradeFin;
    int horas, minutos;
    String duracion, duracionfin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_actualizar);
        controlBDChristian = new ControlBDChristian(this);

        editHoraid =  findViewById(R.id.EditIdHoraActualizar);
        editHoraInicio = findViewById(R.id.EditHoraInicioActualizar);
        editHoraFin = findViewById(R.id.EditHoraFinActualizar);
        botonActualizarHora = (Button) findViewById(R.id.botonActualizarHora);
        botonLimpiar = findViewById(R.id.botonLimpiar);
        /*botonActualizarHoradeInicio = (Button) findViewById(R.id.horaInicioPickerActualizar);
        getBotonActualizarHoradeFin = (Button) findViewById(R.id.horaFinalizarPickerActualizar);*/


        /*
        editHoraInicio.setOnClickListener(new View.OnClickListener() {
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

        editHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int seleccionarHoraFin, int seleccionarMinutoFin) {
                        //Reutilizo las variables para que inicie desde el horario seleccionado anteriormente
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
        */


        //Actualizacion de la vista del Reloj

        //Hora de inicio
        editHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(1)
                        .setMinute(0)
                        .setTitleText("Seleccione la hora de inicio")
                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                        .build();

                timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //String duracionH;

                        if(timePicker.getHour() <10){
                            if(timePicker.getMinute() <10){
                                duracion = "0" + timePicker.getHour() + ":0" + timePicker.getMinute();
                                editHoraInicio.setText(duracion);
                            }else{
                                duracion = "0" + timePicker.getHour() + ":" + timePicker.getMinute();
                                editHoraInicio.setText(duracion);
                            }
                        }else{
                            if (timePicker.getMinute() < 10){
                                duracion = timePicker.getHour() + ":0" + timePicker.getMinute();
                                editHoraInicio.setText(duracion);
                            }else{
                                duracion = timePicker.getHour() + ":" + timePicker.getMinute();
                                editHoraInicio.setText(duracion);
                            }
                        }

                    }
                });
            }
        });

        //Hora de finalizacion

        editHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(1)
                        .setMinute(0)
                        .setTitleText("Seleccione la hora de finalizacion")
                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                        .build();
                timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(timePicker.getHour() < 10){
                            if(timePicker.getMinute() <10){
                                duracionfin = "0" + timePicker.getHour() + ":0" +timePicker.getMinute();
                                editHoraFin.setText(duracionfin);
                            }else{
                                duracionfin = "0" + timePicker.getHour() + ":" + timePicker.getMinute();
                                editHoraFin.setText(duracionfin);
                            }
                        }else{
                            if(timePicker.getMinute()<10){
                                duracionfin = timePicker.getHour() + ":0" + timePicker.getMinute();
                                editHoraFin.setText(duracionfin);
                            }else{
                                duracionfin = timePicker.getHour() + ":" + timePicker.getMinute();
                                editHoraFin.setText(duracionfin);
                            }
                        }
                    }
                });
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
                }else if(id.length() !=4){
                    Toast.makeText(HoraActualizarActivity.this, "El numero de hora debe de tener 4 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                        if (inicio.equals(fin)) {
                            Toast.makeText(HoraActualizarActivity.this, "Debe de ingresar horas diferentes!", Toast.LENGTH_SHORT).show();
                        } else if (inicio.compareTo(fin) > 0) {
                            Toast.makeText(HoraActualizarActivity.this, "La hora de inicio debe ser anterior a la hora de finalizacion", Toast.LENGTH_SHORT).show();
                            verdadero = false;
                        } else {
                            hora.setIdHora(id.replace(" ",""));
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

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editHoraid.setText("");
                editHoraInicio.setText("");
                editHoraFin.setText("");
            }
        });


    }
}