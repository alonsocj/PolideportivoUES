package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesEliminarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    EditText editIdHora;
    Button botonEliminar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_eliminar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        editIdHora = (EditText) findViewById(R.id.EditIdHorariosDisponibles);
        botonEliminar = (Button) findViewById(R.id.botonEliminarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registrosEliminados;

                HorariosDisponibles horariosDisponibles = new HorariosDisponibles();
                horariosDisponibles.setIdHorario(editIdHora.getText().toString());
                controlBDGustavo.open();
                registrosEliminados = controlBDGustavo.eliminarHorarioDisponible(horariosDisponibles);
                controlBDGustavo.close();
                Toast.makeText(HorariosDisponiblesEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
                editIdHora.setText("");
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdHora.setText("");
            }
        });
    }
}