package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarEvento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URLEncoder;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.EventoInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class InsertarEventoExternoActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    TextInputEditText IdEvento, NombreEvento, CostoEvento, cantAutorizada;
    Button agregarEvento, botonLimpiar;
    MaterialAutoCompleteTextView spinnerTiposEventos;
    SQLiteDatabase db;
    String arrayidTE[];
    String arrayTiposdeEventos[];

    private final String urlService = "https://grupo10pdm2022.000webhostapp.com/ws_evento_insert.php";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_evento_externo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        controlBDChristian = new ControlBDChristian(this);
        IdEvento = findViewById(R.id.EditIdNumeroEvento);

        NombreEvento = findViewById(R.id.EditNombreEvento);
        CostoEvento = findViewById(R.id.EditCostoEvento);
        cantAutorizada = findViewById(R.id.EditCantAutorEvento);
        agregarEvento = findViewById(R.id.botonAgregarNuevoEvento);
        spinnerTiposEventos = findViewById(R.id.spinnerTipoEvento);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        /*Obtención de datos de la base de datos y traerlos al spinner*/
        db = openOrCreateDatabase("polideportivo.s3db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor cursor = db.rawQuery("select * from tipoevento", null);
        arrayidTE = new String[cursor.getCount()];
        arrayTiposdeEventos = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < arrayidTE.length; i++) {
            arrayidTE[i] = cursor.getString(0); //Seria el id del nombre del evento
            arrayTiposdeEventos[i] = cursor.getString(1); //Obtengo los nombres del evento
            cursor.moveToNext();
        }
        //Definición del adapter que almacena los datos del spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayTiposdeEventos);
        spinnerTiposEventos.setAdapter(arrayAdapter);

        //Boton Agregar evento
        agregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventosRegistrados;
                String idEvento = IdEvento.getText().toString();
                String nombreEvento = NombreEvento.getText().toString();
                try {
                    nombreEvento = URLEncoder.encode(nombreEvento, "UTF-8");

                } catch (Exception e) {

                }
                String costoString = CostoEvento.getText().toString();
                String cantidadString = cantAutorizada.getText().toString();
                String idtipoE = "";

                /*Validaciones*/
                if (idEvento.isEmpty() || nombreEvento.isEmpty() || costoString.isEmpty() || cantidadString.isEmpty() || spinnerTiposEventos.getText().toString().isEmpty()) {
                    Toast.makeText(InsertarEventoExternoActivity.this, "Debe de llenar todos los campos para poder ingresar un evento", Toast.LENGTH_SHORT).show();
                } else {
                    Float costo = Float.valueOf(costoString);
                    Integer cantAuto = Integer.valueOf(cantidadString);
                    if (costo <= 0) {
                        Toast.makeText(InsertarEventoExternoActivity.this, "Debe de ingresar un costo mayor a 0", Toast.LENGTH_SHORT).show();
                    } else if (cantAuto <= 0) {
                        Toast.makeText(InsertarEventoExternoActivity.this, "La cantidad de personas debe ser mayor a 0", Toast.LENGTH_SHORT).show();
                    } else {
                        Evento event = new Evento();
                        event.setIdEvento(idEvento);
                        for (int i = 0; i < arrayidTE.length; i++) {
                            if (spinnerTiposEventos.getText().toString().equals(arrayTiposdeEventos[i])) {
                                event.setIdTipoE(arrayidTE[i]);
                                idtipoE = arrayidTE[i];
                            }
                        }
                        event.setNomEvento(nombreEvento);
                        event.setCostoEvento(costo);
                        event.setCantidadAutorizada(cantAuto);
                        controlBDChristian.open();
                        eventosRegistrados = controlBDChristian.agregarEvento(event);
                        controlBDChristian.close();
                        sendData(idEvento, idtipoE, nombreEvento, costoString, cantidadString);
                        Toast.makeText(InsertarEventoExternoActivity.this, eventosRegistrados, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IdEvento.setText("");
                NombreEvento.setText("");
                CostoEvento.setText("");
                cantAutorizada.setText("");
                spinnerTiposEventos.setText("");
            }
        });
    }

    public void sendData(String idEvento, String idTipoEvento, String nombreEvento, String costoEvento, String cantidadAutorizada) {
        String url = urlService + "?idevento=" + idEvento + "&idtipoe=" + idTipoEvento + "&nomevento=" + nombreEvento + "&costoevento=" + costoEvento + "&cantidadautorizada=" + cantidadAutorizada;
        EventoService.insertarEventoExterno(url, this);
    }
}