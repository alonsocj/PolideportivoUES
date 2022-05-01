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

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoInsertarActivity extends AppCompatActivity {

    ControlBDG10 controlBDG10;
    EditText IdEvento, NombreEvento, CostoEvento;
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

        controlBDG10 = new ControlBDG10(this);
        IdEvento = findViewById(R.id.EditIdNumeroEvento);

        NombreEvento = findViewById(R.id.EditNombreEvento);
        CostoEvento = findViewById(R.id.EditCostoEvento);
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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayidTE);
        spinnerTiposEventos.setAdapter(arrayAdapter);

        //Boton Agregar evento
        agregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventosRegistrados, posicion;
                String idEvento = IdEvento.getText().toString();
                String idTipoEvento = spinnerTiposEventos.getSelectedItem().toString() ;
                String nombreEvento = NombreEvento.getText().toString();
                Float costo = Float.valueOf(CostoEvento.getText().toString());

                Evento event = new Evento();
                event.setIdEvento(idEvento);
                event.setIdTipoE(idTipoEvento);
                event.setNomEvento(nombreEvento);
                event.setCostoEvento(costo);
                controlBDG10.open();
                eventosRegistrados = controlBDG10.agregarEvento(event);
                controlBDG10.close();
                Toast.makeText(EventoInsertarActivity.this, eventosRegistrados, Toast.LENGTH_SHORT).show();

            }
        });

    }
}