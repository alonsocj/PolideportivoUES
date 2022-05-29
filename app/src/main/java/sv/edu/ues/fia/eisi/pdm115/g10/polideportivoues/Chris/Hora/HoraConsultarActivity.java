package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora;

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

public class HoraConsultarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    TextInputEditText editIdHora, editHoraI, editHoraF;
    Button consultarHora, botonLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_consultar);

        controlBDChristian = new ControlBDChristian(this);
        editIdHora =  findViewById(R.id.EditIdHoraC);
        editHoraI = findViewById(R.id.EditHoraInicioC);
        editHoraF = findViewById(R.id.EditHoraFinC);
        botonLimpiar = findViewById(R.id.botonLimpiar);
        consultarHora = (Button) findViewById(R.id.botonBuscarHora);

        consultarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlBDChristian.open();
                Hora hora = controlBDChristian.ConsultarHora(editIdHora.getText().toString());
                controlBDChristian.close();

                if(editIdHora.getText().toString().equals("")){
                    Toast.makeText(HoraConsultarActivity.this, "Ingrese un idHora!", Toast.LENGTH_SHORT).show();
                }else if(editIdHora.getText().toString().length() != 4) {
                    Toast.makeText(HoraConsultarActivity.this, "Ingrese un idHora con 4 Caracteres", Toast.LENGTH_SHORT).show();
                }else{
                        /*Verificación que exista la Hora*/
                        if(hora == null){
                            Toast.makeText(HoraConsultarActivity.this, "Hora con Numero de hora " + editIdHora.getText().toString() + " No encontrado", Toast.LENGTH_SHORT).show();
                        }else{
                            editHoraI.setText(hora.getHoraInicio());
                            editHoraF.setText(hora.getHoraFin());
                        }
                    }
                }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdHora.setText("");
                editHoraI.setText("");
                editHoraF.setText("");
            }
        });

    }
}