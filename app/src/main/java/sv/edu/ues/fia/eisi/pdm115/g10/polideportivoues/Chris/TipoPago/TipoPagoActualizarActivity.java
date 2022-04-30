package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoPagoActualizarActivity extends AppCompatActivity {

    ControlBDG10 controlBDG10;
    EditText editIdTPag , editTipoPag;
    Button actualizarTipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_actualizar);

        controlBDG10 = new ControlBDG10(this);
        editIdTPag = findViewById(R.id.EditIdPagoActu);
        editTipoPag = findViewById(R.id.EditTipoActu);
        actualizarTipoPago = findViewById(R.id.botonActualizarTipoP);

        actualizarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TipoPago tipoPago = new TipoPago();
                tipoPago.setIdPago(editIdTPag.getText().toString());
                tipoPago.setTipo(editTipoPag.getText().toString());

                controlBDG10.open();
                String estado = controlBDG10.actualizarTipoPago(tipoPago);
                controlBDG10.close();
                Toast.makeText(TipoPagoActualizarActivity.this, estado, Toast.LENGTH_SHORT).show();
            }
        });


    }
}