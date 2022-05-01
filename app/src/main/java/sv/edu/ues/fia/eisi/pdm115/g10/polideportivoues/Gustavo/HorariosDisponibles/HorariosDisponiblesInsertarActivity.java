package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesInsertarActivity extends AppCompatActivity {

    EditText editIdHora;
    Spinner editDia, editHora;
    Button botonAgregar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_insertar);

        editIdHora=(EditText) findViewById(R.id.EditIdHorariosDisponibles);
        editDia=(Spinner) findViewById(R.id.EditDia);
        editHora=(Spinner) findViewById(R.id.EditHora);
        botonAgregar = (Button) findViewById(R.id.botonAgregarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        //Cargamos los días
        ArrayAdapter<CharSequence> adapterDia=ArrayAdapter.createFromResource(this,R.array.arrayDias, android.R.layout.simple_spinner_item);
        editDia.setAdapter(adapterDia);

        //Cargamos las horas
        ArrayAdapter<CharSequence> adapterHora=ArrayAdapter.createFromResource(this,R.array.arrayHoras, android.R.layout.simple_spinner_item);
        editDia.setAdapter(adapterHora);

    }

}