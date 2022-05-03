package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoPagoEliminarActivity extends AppCompatActivity {

    ControlBDChristian helper;
    TextInputEditText editIdTipoPago;
    Button eliminarTipoPago, botonLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_eliminar);

        helper = new ControlBDChristian(this);
        editIdTipoPago = findViewById(R.id.EditIdPagoEliminar);
        eliminarTipoPago = findViewById(R.id.botonEliminarTipoP);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        eliminarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registrosEliminados;
                TipoPago tipoPago = new TipoPago();
                tipoPago.setIdPago(editIdTipoPago.getText().toString());
                helper.open();
                registrosEliminados = helper.eliminarTipoPago(tipoPago);
                helper.close();
                Toast.makeText(TipoPagoEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdTipoPago.setText("");
            }
        });

    }
}