package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoActualizarActivity extends AppCompatActivity {

    ControlBDChristian helper;
    EditText editIdEve, editNom, editCost, editCantAutorizada;
    Button actualizareventos;
    Spinner spinner;
    SQLiteDatabase db;
    String arrayidTE[];
    String arrayTiposdeEventos[];

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_actualizar);

        helper = new ControlBDChristian(this);
        editIdEve = findViewById(R.id.EditIdNumeroEventoActu);
        editNom = findViewById(R.id.EditNombreEventoActu);
        editCost = findViewById(R.id.EditCostoEventoActu);
        actualizareventos = findViewById(R.id.botonActualizarEvent);
        editCantAutorizada = findViewById(R.id.EditCantAutorEventoActu);
        spinner = findViewById(R.id.spinnerTipoEventoActu);


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
        spinner.setAdapter(arrayAdapter);

        actualizareventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Evento event = new Evento();
                event.setIdEvento(editIdEve.getText().toString());

                for (int i=0; i< arrayidTE.length; i++){
                    if(spinner.getSelectedItem().toString() == arrayTiposdeEventos[i]){
                        event.setIdTipoE(arrayidTE[i]);
                    }
                }
                event.setNomEvento(editNom.getText().toString());
                event.setCostoEvento(Float.valueOf(editCost.getText().toString()));
                event.setCantidadAutorizada(Integer.valueOf(editCantAutorizada.getText().toString()));
                helper.open();
                String actu = helper.actualizarEvento(event);
                helper.close();
                Toast.makeText(EventoActualizarActivity.this, actu, Toast.LENGTH_SHORT).show();
            }
        });

    }
}