package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HoraConsultarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    EditText editIdHora, editHoraI, editHoraF;
    Button consultarHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_consultar);

        controlBDChristian = new ControlBDChristian(this);
        editIdHora = (EditText) findViewById(R.id.EditIdHoraC);
        editHoraI = (EditText) findViewById(R.id.EditHoraInicioC);
        editHoraF = (EditText) findViewById(R.id.EditHoraFinC);
        consultarHora = (Button) findViewById(R.id.botonBuscarHora);

        consultarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlBDChristian.open();
                Hora hora = controlBDChristian.ConsultarHora(editIdHora.getText().toString());
                controlBDChristian.close();

                /*Verificación que exista la Hora*/
                if(hora == null){
                    Toast.makeText(HoraConsultarActivity.this, "Horario con Hora " + editIdHora.getText().toString() + " No encontrado", Toast.LENGTH_SHORT).show();
                }else{
                    editHoraI.setText(hora.getHoraInicio());
                    editHoraF.setText(hora.getHoraFin());
                }
            }
        });
    }
}