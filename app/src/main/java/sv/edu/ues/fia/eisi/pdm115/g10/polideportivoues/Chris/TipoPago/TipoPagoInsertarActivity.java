package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoPagoInsertarActivity extends AppCompatActivity {

    EditText editIdPago, editTipoPago;
    Button agregarTipoPago;
    ControlBDG10 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_insertar);

        helper = new ControlBDG10(this);
        editIdPago = (EditText) findViewById(R.id.EditIdPago);
        editTipoPago = (EditText) findViewById(R.id.EditTipo);
        agregarTipoPago = (Button) findViewById(R.id.botonAgregarTipoP);

        agregarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPago = editIdPago.getText().toString();
                String tipoPago = editTipoPago.getText().toString();
                String pagosRegistrados;

                    TipoPago tipoPago1 = new TipoPago();
                    tipoPago1.setIdPago(idPago);
                    tipoPago1.setTipo(tipoPago);
                    helper.open();
                    pagosRegistrados = helper.insertarTipoPago(tipoPago1);
                    helper.close();
                Toast.makeText(TipoPagoInsertarActivity.this, pagosRegistrados, Toast.LENGTH_SHORT).show();
            }
        });


    }
}