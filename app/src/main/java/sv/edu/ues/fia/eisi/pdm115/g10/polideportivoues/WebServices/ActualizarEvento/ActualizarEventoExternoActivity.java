package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ActualizarEvento;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class ActualizarEventoExternoActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    static List<Evento> listaDeEventos;
    static List<String> nombreDeEventos;
    EditText fechaTxt;
    ListView listaViewEventos;
    Button servicioPHP, guardar, limpiar;

    //De manera Local
    //private final String UrlLocal = "http://192.168.1.9/WSPolideportivoUES/ws_evento_update.php";

    //ServidorGratuito
    private final String UrlServidorGratuito = "https://grupo10pdm2022.000webhostapp.com/ws_evento_update.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_evento_externo_service);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        controlBDChristian = new ControlBDChristian(this);
        listaDeEventos = new ArrayList<Evento>();
        nombreDeEventos = new ArrayList<String>();
        fechaTxt = findViewById(R.id.EditIdFechaEvento);

        //Mostrar calendario en Input
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dia = day+"/"+(month+1)+"/"+year;

        fechaTxt.setText(dia);

        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActualizarEventoExternoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        fechaTxt.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        listaViewEventos = findViewById(R.id.listActualizarEventoServices);
        servicioPHP = findViewById(R.id.botonserviciophp);
        guardar = findViewById(R.id.botonActualizarServices);
        limpiar = findViewById(R.id.botonLimpiarServices);

        servicioPHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((fechaTxt.getText().toString().equals(""))){
                    Toast.makeText(ActualizarEventoExternoActivity.this, "Debe de ingresar una fecha", Toast.LENGTH_SHORT).show();
                }else{
                String[] fecha = fechaTxt.getText().toString().split("/");
                String url = "";
                //url = UrlLocal + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                url = UrlServidorGratuito + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                String eventosExternos = EventoActualizarService.obtenerRespuestaPeticion(url, ActualizarEventoExternoActivity.this);

                try{
                    listaDeEventos.addAll(EventoActualizarService.obtenerEventosExternos(eventosExternos, ActualizarEventoExternoActivity.this));
                    actualizarListViewDeEventos();
                }catch (Exception e){
                    e.printStackTrace();
                }
                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlBDChristian.open();
                for(int i=0; i< listaDeEventos.size(); i++){
                    Log.v("Guardar", controlBDChristian.agregarEvento(listaDeEventos.get(i)));
                }
                controlBDChristian.close();
                Toast.makeText(ActualizarEventoExternoActivity.this, "Eventos Guardados con exito", Toast.LENGTH_SHORT).show();
                listaDeEventos.removeAll(listaDeEventos);
                actualizarListViewDeEventos();
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fechaTxt.setText("");
                listaDeEventos.removeAll(listaDeEventos);
                actualizarListViewDeEventos();
            }
        });

    }

    private void actualizarListViewDeEventos() {
        String dato = "";
        nombreDeEventos.clear();

        for(int i = 0; i < listaDeEventos.size(); i++){
            dato = " id evento: " + listaDeEventos.get(i).getIdEvento() +
                    "\n Tipo de evento: " + listaDeEventos.get(i).getIdTipoE() +
                    "\n Nombre del evento: " + listaDeEventos.get(i).getNomEvento()+
                    "\n Costo del evento: " + listaDeEventos.get(i).getCostoEvento() +
                    "\n Cantidad autorizada: " + listaDeEventos.get(i).getCantidadAutorizada();
            nombreDeEventos.add(dato);
        }
        eliminarElementosDeEventoDuplicado();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreDeEventos);
        listaViewEventos.setAdapter(adapter);
    }

    private void eliminarElementosDeEventoDuplicado() {

        HashSet<Evento> conjuntoEvento = new HashSet<Evento>();
        conjuntoEvento.addAll(listaDeEventos);
        listaDeEventos.clear();
        listaDeEventos.addAll(conjuntoEvento);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreDeEventos);
        nombreDeEventos.clear();
        nombreDeEventos.addAll(conjuntoNombre);
    }

}