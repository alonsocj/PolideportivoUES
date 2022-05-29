package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.HorariosLocalesUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ActualizarEvento.ActualizarEventoExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ActualizarEvento.EventoActualizarService;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;

@SuppressLint("NewApi")
public class UpdateHorarioLocalActivity extends AppCompatActivity {

    ControlBDG10William helper;
    static List<HorariosLocales> listaHorariosLocales;
    static List<String> nombresHorariosLocales;
    TextInputEditText fechaTxt;
    ListView listViewHorariosLocales;
    Button servicioPHP, guardar, limpiar;
    private final String UrlLocal = "http://192.168.1.9/WSPolideportivoUES/ws_local_update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_horario_local);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDG10William(this);
        listaHorariosLocales = new ArrayList<HorariosLocales>();
        nombresHorariosLocales = new ArrayList<String>();
        fechaTxt = (TextInputEditText) findViewById(R.id.editFechaHorarioLocal);

        //Calenndario
        Calendar calendario = Calendar.getInstance();
        final int year = calendario.get(Calendar.YEAR);
        final int month = calendario.get(Calendar.MONTH);
        final int day = calendario.get(Calendar.DAY_OF_MONTH);
        String fecha = day + "/" + (month + 1) + "/" + year;

        fechaTxt.setText(fecha);

        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateHorarioLocalActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        fechaTxt.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        listViewHorariosLocales = (ListView) findViewById(R.id.listView1);
        servicioPHP = (Button) findViewById(R.id.botonserviciophpHL);
        guardar = findViewById(R.id.botonActualizarServicesHL);
        limpiar = findViewById(R.id.botonLimpiarServices);

        servicioPHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((fechaTxt.getText().toString().equals(""))) {
                    Toast.makeText(UpdateHorarioLocalActivity.this, "Debe de ingresar una fecha", Toast.LENGTH_SHORT).show();
                } else {
                    String[] fecha = fechaTxt.getText().toString().split("/");
                    String url = "";
                    url = UrlLocal + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                    String horariosLocalesExternos = HorarioLocalService.obtenerRespuestaPeticion(url, UpdateHorarioLocalActivity.this);

                    try {
                        listaHorariosLocales.addAll(Objects.requireNonNull(HorarioLocalService.obtenerMateriasExterno(horariosLocalesExternos, UpdateHorarioLocalActivity.this)));
                        actualizarListViewHorarioLocales();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.open();
                for (int i = 0; i < listaHorariosLocales.size(); i++) {
                    Log.v("Guardar", helper.ingresarHorariosLocales(listaHorariosLocales.get(i)));
                }
                helper.close();
                Toast.makeText(UpdateHorarioLocalActivity.this, "Horario de los Locales Guardados con exito", Toast.LENGTH_SHORT).show();
                listaHorariosLocales.removeAll(listaHorariosLocales);
                actualizarListViewHorarioLocales();
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fechaTxt.setText("");
                listaHorariosLocales.removeAll(listaHorariosLocales);
                actualizarListViewHorarioLocales();
            }
        });

    }

    private void actualizarListViewHorarioLocales() {
        String dato = "";
        nombresHorariosLocales.clear();

        for (int i = 0; i < listaHorariosLocales.size(); i++) {
            String estado = "";
            if (listaHorariosLocales.get(i).getDisponibilidad() == 0) {
                estado = "Disponible";
            } else {
                estado = "No Disponible";
            }
            dato = " ID de el Horario: " + listaHorariosLocales.get(i).getIdHorario() +
                    "\n ID de el Local: " + listaHorariosLocales.get(i).getIdLocal() +
                    "\n Estado: " + estado;
            nombresHorariosLocales.add(dato);
        }
        eliminarElementosDeEventoDuplicado();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresHorariosLocales);
        listViewHorariosLocales.setAdapter(adapter);
    }

    private void eliminarElementosDeEventoDuplicado() {

        HashSet<HorariosLocales> conjuntoHorariosLocales = new HashSet<HorariosLocales>();
        conjuntoHorariosLocales.addAll(listaHorariosLocales);
        listaHorariosLocales.clear();
        listaHorariosLocales.addAll(conjuntoHorariosLocales);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombresHorariosLocales);
        nombresHorariosLocales.clear();
        nombresHorariosLocales.addAll(conjuntoNombre);
    }
}