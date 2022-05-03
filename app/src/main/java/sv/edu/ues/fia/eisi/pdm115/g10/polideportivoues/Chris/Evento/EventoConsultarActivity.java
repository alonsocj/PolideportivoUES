package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoConsultarActivity extends AppCompatActivity {

    TextInputEditText editIdEven, editIdTE, editNom, editCost, editCantAuto;
    Button consultarEvent;
    ControlBDChristian helper;
    private SQLiteDatabase db;
    String nombreTipoEvento[];
    String idEvento[];

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_consultar);
        helper = new ControlBDChristian(this);
        editIdEven = findViewById(R.id.EditIdNumeroEventoConsulta);
        editIdTE = findViewById(R.id.EditTipoEventoConsulta);
        editNom = findViewById(R.id.EditNombreEventoConsulta);
        editCost = findViewById(R.id.EditCostoEventoConsulta);
        editCantAuto = findViewById(R.id.EditCantAutorEventoConsulta);

        consultarEvent = findViewById(R.id.botonConsultarEvent);

        /*Obtención de datos de la base de datos*/
        db = openOrCreateDatabase("polideportivo.s3db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor cursor = db.rawQuery("SELECT * FROM evento INNER JOIN tipoevento ON evento.idTipoE == tipoevento.idTipoE",null);
        nombreTipoEvento = new String[cursor.getCount()];
        idEvento = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i=0; i<nombreTipoEvento.length; i++){
            idEvento[i] = cursor.getString(0); //Id de los eventos (Tabla evento)
            nombreTipoEvento[i] = cursor.getString(6); //Nombre Evento (Tabla tipoevento)
            cursor.moveToNext();
        }

        consultarEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idEvent = editIdEven.getText().toString();
                helper.open();
                Evento evento = helper.consultarEvento(idEvent);
                helper.close();
                if(evento == null){
                    Toast.makeText(EventoConsultarActivity.this, "No existe el evento", Toast.LENGTH_SHORT).show();
                }else{
                   for (int i=0; i< idEvento.length; i++){
                        if(idEvent.equals(idEvento[i])){
                            editIdTE.setText(nombreTipoEvento[i]);
                        }
                    }
                    /*editIdTE.setText(evento.getIdTipoE());*/
                    editNom.setText(evento.getNomEvento());
                    editCost.setText(String.valueOf(evento.getCostoEvento()));
                    editCantAuto.setText(String.valueOf(evento.getCantidadAutorizada()));
                }
            }
        });

    }
}