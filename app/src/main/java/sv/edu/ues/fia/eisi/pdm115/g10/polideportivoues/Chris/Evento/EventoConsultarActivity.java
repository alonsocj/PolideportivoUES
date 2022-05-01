package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoConsultarActivity extends AppCompatActivity {

    EditText editIdEven, editIdTE, editNom, editCost;
    Button consultarEvent;
    ControlBDG10 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_consultar);
        helper = new ControlBDG10(this);
        editIdEven = findViewById(R.id.EditIdNumeroEventoConsulta);
        editIdTE = findViewById(R.id.EditTipoEventoConsulta);
        editNom = findViewById(R.id.EditNombreEventoConsulta);
        editCost = findViewById(R.id.EditCostoEventoConsulta);

        consultarEvent = findViewById(R.id.botonConsultarEvent);

        consultarEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.open();
                Evento evento = helper.consultarEvento(editIdEven.getText().toString());
                helper.close();
                if(evento == null){
                    Toast.makeText(EventoConsultarActivity.this, "No existe el evento", Toast.LENGTH_SHORT).show();
                }else{
                    editIdTE.setText(evento.getIdTipoE());
                    editNom.setText(evento.getNomEvento());
                    editCost.setText(String.valueOf(evento.getCostoEvento()));
                }
            }
        });

    }
}