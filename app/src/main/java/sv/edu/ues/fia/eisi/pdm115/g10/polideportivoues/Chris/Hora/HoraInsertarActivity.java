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

public class HoraInsertarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    TextInputEditText editId, editInicio, editFin;
    Button botonAgregar, botonLimpiar, botonHoradeInicio, botonHoraFinalizacion;
    int horas, minutos;
    String duracion, duracionfin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_insertar);

        controlBDChristian = new ControlBDChristian(this);
        editId = findViewById(R.id.EditIdHora);
        editInicio = findViewById(R.id.EditHoraInicio);
        editFin = findViewById(R.id.EditHoraFin);
        botonAgregar =  findViewById(R.id.botonAgregarHora);
        botonLimpiar = findViewById(R.id.botonLimpiar);
        /*botonHoradeInicio = (Button) findViewById(R.id.horaInicioPicker);
        botonHoraFinalizacion = (Button) findViewById(R.id.horaFinalizarPicker);*/


        /*
        editInicio.setOnClickListener(new View.OnClickListener() {
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


        editFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int seleccionarHoraFin, int seleccionarMinutoFin) {
                        //Reutilizo las variables para que inicie desde el horario seleccionado anteriormente
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
*/

        //Actualizacion de estado del reloj

        //Hora de inicio
        editInicio.setOnClickListener(new View.OnClickListener() {
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
                                editInicio.setText(duracion);
                            }else{
                                duracion = "0" + timePicker.getHour() + ":" + timePicker.getMinute();
                                editInicio.setText(duracion);
                            }
                        }else{
                            if (timePicker.getMinute() < 10){
                                duracion = timePicker.getHour() + ":0" + timePicker.getMinute();
                                editInicio.setText(duracion);
                            }else{
                                duracion = timePicker.getHour() + ":" + timePicker.getMinute();
                                editInicio.setText(duracion);
                            }
                        }

                    }
                });
            }
        });

        //Hora de finalizacion

                editFin.setOnClickListener(new View.OnClickListener() {
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
                                        editFin.setText(duracionfin);
                                    }else{
                                        duracionfin = "0" + timePicker.getHour() + ":" + timePicker.getMinute();
                                        editFin.setText(duracionfin);
                                    }
                                }else{
                                    if(timePicker.getMinute()<10){
                                        duracionfin = timePicker.getHour() + ":0" + timePicker.getMinute();
                                        editFin.setText(duracionfin);
                                    }else{
                                        duracionfin = timePicker.getHour() + ":" + timePicker.getMinute();
                                        editFin.setText(duracionfin);
                                    }
                                }
                            }
                        });
                    }
                });

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                String inicio = editInicio.getText().toString();
                String fin = editFin.getText().toString();
                String insertarRegistros;
                boolean verdadero=true;

                if(id.isEmpty() || inicio.isEmpty() || fin.isEmpty()){
                    Toast.makeText(HoraInsertarActivity.this, "Para agregar una hora debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else if(id.length() != 4){
                    Toast.makeText(HoraInsertarActivity.this, "El numero de hora debe tener 4 caracteres", Toast.LENGTH_SHORT).show();
                }else {
                        if (inicio.equals(fin)) {
                            Toast.makeText(HoraInsertarActivity.this, "Debe de ingresar horas diferentes!", Toast.LENGTH_SHORT).show();
                        } else if (inicio.compareTo(fin) > 0) {
                            Toast.makeText(HoraInsertarActivity.this, "La hora de inicio debe ser anterior a la hora de finalizacion", Toast.LENGTH_SHORT).show();
                            verdadero = false;
                        } else {
                            Hora hora = new Hora();
                            hora.setIdHora(id);
                            hora.setHoraInicio(inicio);
                            hora.setHoraFin(fin);
                            controlBDChristian.open();
                            insertarRegistros = controlBDChristian.insertarHora(hora);
                            controlBDChristian.close();
                            Toast.makeText(HoraInsertarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editId.setText("");
                editInicio.setText("");
                editFin.setText("");
            }
        });

    }
}