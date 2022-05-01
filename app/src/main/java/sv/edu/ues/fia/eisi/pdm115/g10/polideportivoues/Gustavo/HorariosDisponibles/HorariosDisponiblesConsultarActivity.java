package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesConsultarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    EditText editIdHora,editDia, editHora;
    Button botonConsultar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_consultar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);

        //Horario Disponible
        editIdHora=(EditText) findViewById(R.id.EditIdHorariosDisponibles);
        editDia=(EditText) findViewById(R.id.EditDia);
        editHora=(EditText) findViewById(R.id.EditHora);
        botonConsultar = (Button) findViewById(R.id.botonConsultarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlBDGustavo.open();
                HorariosDisponibles horariosDisponibles = controlBDGustavo.consultarHorarioDisponible(editIdHora.getText().toString());
                controlBDGustavo.close();

                /*Verificación que exista la Disponibilidad de Horario*/
                if(horariosDisponibles == null){
                    Toast.makeText(HorariosDisponiblesConsultarActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                    editHora.setText("");
                    editDia.setText("");
                }else{
                    editDia.setText(horariosDisponibles.getDia());
                    editHora.setText(horariosDisponibles.getHora());
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdHora.setText("");
                editHora.setText("");
                editDia.setText("");
            }
        });
    }
}