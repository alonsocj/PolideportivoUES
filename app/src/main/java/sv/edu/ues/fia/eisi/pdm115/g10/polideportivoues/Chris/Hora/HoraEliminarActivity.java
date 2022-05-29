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

public class HoraEliminarActivity extends AppCompatActivity {

    TextInputEditText EditidHoraE;
    ControlBDChristian helper;
    Button btnEliminarHora, botonLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_eliminar);

        helper = new ControlBDChristian(this);
        EditidHoraE = findViewById(R.id.EditIdHoraE);
        btnEliminarHora = (Button) findViewById(R.id.botonEliminarHora);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        btnEliminarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registrosEliminados;
                String idHora = EditidHoraE.getText().toString();
                
                if(idHora.equals("")){
                    Toast.makeText(HoraEliminarActivity.this, "Debe de ingresar un idHora", Toast.LENGTH_SHORT).show();
                }else if(idHora.length() != 4) {
                    Toast.makeText(HoraEliminarActivity.this, "Debe de ingresar un idHora de 4 Caracteres", Toast.LENGTH_SHORT).show();
                }else{
                        Hora hora = new Hora();
                        hora.setIdHora(EditidHoraE.getText().toString());
                        helper.open();
                        registrosEliminados = helper.eliminarHora(hora);
                        helper.close();
                        Toast.makeText(HoraEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
                    }
                }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditidHoraE.setText("");
            }
        });

    }
}