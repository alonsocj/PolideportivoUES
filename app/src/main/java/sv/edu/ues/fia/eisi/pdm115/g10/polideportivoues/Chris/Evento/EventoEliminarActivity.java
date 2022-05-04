package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoEliminarActivity extends AppCompatActivity {

    ControlBDChristian helper;
    Button eliminarevent, botonLimpiar;
    TextInputEditText editIdEv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_eliminar);

        helper = new ControlBDChristian(this);
        editIdEv = findViewById(R.id.EditIdNumeroEventoEliminar);
        eliminarevent = findViewById(R.id.botonEliminarEvent);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        eliminarevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registroseliminados;
                Evento evento = new Evento();
                evento.setIdEvento(editIdEv.getText().toString());
                helper.open();
                registroseliminados = helper.eliminarEvento(evento);
                helper.close();
                Toast.makeText(EventoEliminarActivity.this, registroseliminados, Toast.LENGTH_SHORT).show();
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdEv.setText("");
            }
        });

    }
}