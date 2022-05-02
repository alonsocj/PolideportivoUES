package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoInsertarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    EditText IdEvento, NombreEvento, CostoEvento, cantAutorizada;
    Button agregarEvento;
    Spinner spinnerTiposEventos;
    SQLiteDatabase db;
    String arrayidTE[];
    String arrayTiposdeEventos[];

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_insertar);

        controlBDChristian = new ControlBDChristian(this);
        IdEvento = findViewById(R.id.EditIdNumeroEvento);

        NombreEvento = findViewById(R.id.EditNombreEvento);
        CostoEvento = findViewById(R.id.EditCostoEvento);
        cantAutorizada = findViewById(R.id.EditCantAutorEvento);
        agregarEvento = findViewById(R.id.botonAgregarNuevoEvento);
        spinnerTiposEventos = findViewById(R.id.spinnerTipoEvento);

        /*Obtención de datos de la base de datos y traerlos al spinner*/
        db = openOrCreateDatabase("polideportivo.s3db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor cursor = db.rawQuery("select * from tipoevento",null);
        arrayidTE = new String[cursor.getCount()];
        arrayTiposdeEventos = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i<arrayidTE.length; i++){
            arrayidTE[i] = cursor.getString(0); //Seria el id del nombre del evento
            arrayTiposdeEventos[i] = cursor.getString(1); //Obtengo los nombres del evento
            cursor.moveToNext();
        }
        //Definición del adapter que almacena los datos del spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayTiposdeEventos);
        spinnerTiposEventos.setAdapter(arrayAdapter);

        //Boton Agregar evento
        agregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventosRegistrados;
                String idEvento = IdEvento.getText().toString();
                String nombreEvento = NombreEvento.getText().toString();
                Float costo = Float.valueOf(CostoEvento.getText().toString());
                Integer cantAuto = Integer.valueOf(cantAutorizada.getText().toString());

                Evento event = new Evento();
                event.setIdEvento(idEvento);
                for (int i=0; i< arrayidTE.length; i++){
                    if(spinnerTiposEventos.getSelectedItem().toString() == arrayTiposdeEventos[i]){
                        event.setIdTipoE(arrayidTE[i]);
                    }
                }
                event.setNomEvento(nombreEvento);
                event.setCostoEvento(costo);
                event.setCantidadAutorizada(cantAuto);
                controlBDChristian.open();
                eventosRegistrados = controlBDChristian.agregarEvento(event);
                controlBDChristian.close();
                Toast.makeText(EventoInsertarActivity.this, eventosRegistrados, Toast.LENGTH_SHORT).show();

            }
        });

    }
}