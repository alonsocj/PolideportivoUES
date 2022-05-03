package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class CobroConsultarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdCobro, editTipoPago, editCantPersonas, editDuracion, editPrecio;
    Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro_consultar);
        helper = new ControlBDG10Alonso(this);
        editIdCobro = findViewById(R.id.editText_id_cobro);
        editCantPersonas = findViewById(R.id.editText_cantPersonas);
        editDuracion = findViewById(R.id.editText_duracion);
        editPrecio = findViewById(R.id.editText_precio);
        editTipoPago = findViewById(R.id.list_tipo_pago);
        btnConsultar = findViewById(R.id.button_guardar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}