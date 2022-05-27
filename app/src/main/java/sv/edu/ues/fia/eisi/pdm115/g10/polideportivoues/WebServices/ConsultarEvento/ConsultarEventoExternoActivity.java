package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarEvento;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class ConsultarEventoExternoActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    static List<Evento> listaEventos;
    static List<String> nombreEventos; //Todos los datos
    static List<TipoEvento> listaTipoEventos;

    EditText idEventoText;
    ListView listViewEventos; //Desplegara todos los resultados
    Button consultarEventosServices;
    Button limpiarEvento;


    //Cambiar direccion ip porque es local
    private final String urlLocal = "http://192.168.1.9/WSPolideportivoUES/ws_evento_query.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_evento_externo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Usamos la base de datos
        controlBDChristian = new ControlBDChristian(this);

        //Solicitamos datos
        listaEventos = new ArrayList<Evento>();
        listaTipoEventos = new ArrayList<TipoEvento>();
        nombreEventos = new ArrayList<String>();
        idEventoText = findViewById(R.id.EditIdNumeroEventoConsulta);
        listViewEventos =  (ListView) findViewById(R.id.listConsultaEventoServices);
        consultarEventosServices = findViewById(R.id.botonConsultarEventService);
        limpiarEvento = findViewById(R.id.botonLimpiarServices);

        consultarEventosServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validacion que no se ingrese un ideventoVacio
                if (idEventoText.getText().toString().equals("")) {
                    Toast.makeText(ConsultarEventoExternoActivity.this, "Debe de ingresar un idEvento", Toast.LENGTH_SHORT).show();
                } else {
                    String url = "";

                    //Obtenemos los eventosdelservidor
                    url = urlLocal + "?idevento=" + idEventoText.getText().toString();
                    String eventosExternos = EventoService.obtenerRespuestaPeticion(url, ConsultarEventoExternoActivity.this);

                    try {
                        listaTipoEventos.addAll(EventoService.obtenerTipoEventosExternos(eventosExternos, ConsultarEventoExternoActivity.this));
                        listaEventos.addAll(EventoService.obtenerEventosExternos(eventosExternos, ConsultarEventoExternoActivity.this));
                        actualizarListViewEventos();
                        Toast.makeText(ConsultarEventoExternoActivity.this, "Registros encontrados: " + listaEventos.size() + "", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            });


        limpiarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idEventoText.setText("");
                listaEventos.removeAll(listaEventos);
                actualizarListViewEventos();
            }
        });


    }

    private void actualizarListViewEventos(){
        String dato = "";
        nombreEventos.clear();

        String nombre = "";

        for (int i=0; i< listaTipoEventos.size(); i++){
            nombre = listaTipoEventos.get(i).getNombreTipoE();
        }

        for(int i=0 ; i<listaEventos.size(); i++){
                dato = " id evento: " + listaEventos.get(i).getIdEvento() +
                        //"\n Tipo de evento: " + listaEventos.get(i).getIdTipoE() +
                        "\n Tipo de evento: " + nombre +
                        "\n Nombre del evento: " + listaEventos.get(i).getNomEvento()+
                        "\n Costo del evento: " + listaEventos.get(i).getCostoEvento() +
                        "\n Cantidad autorizada: " + listaEventos.get(i).getCantidadAutorizada();
                nombreEventos.add(dato);
        }
        eliminarEventosDuplicados();

        //Para que se pinten los registros en el listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreEventos);
        listViewEventos.setAdapter(arrayAdapter);
    }

    private void eliminarEventosDuplicados() {
        HashSet<Evento> conjuntoEvento = new HashSet<Evento>();
        conjuntoEvento.addAll(listaEventos);
        listaEventos.clear();
        listaEventos.addAll(conjuntoEvento);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreEventos);
        nombreEventos.clear();
        nombreEventos.addAll(conjuntoNombre);
    }

}

