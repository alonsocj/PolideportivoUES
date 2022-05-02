package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoPagoActualizarActivity extends AppCompatActivity {

    ControlBDChristian controlBDChristian;
    EditText editIdTPag , editTipoPag;
    Button actualizarTipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_actualizar);

        controlBDChristian = new ControlBDChristian(this);
        editIdTPag = findViewById(R.id.EditIdPagoActu);
        editTipoPag = findViewById(R.id.EditTipoActu);
        actualizarTipoPago = findViewById(R.id.botonActualizarTipoP);

        actualizarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String idtipopago = editIdTPag.getText().toString();
               String nomtippag =  editTipoPag.getText().toString();

                if(idtipopago.isEmpty() || nomtippag.isEmpty()){
                    Toast.makeText(TipoPagoActualizarActivity.this, "Debe de completar todos los campos para registrar un tipo de pago", Toast.LENGTH_SHORT).show();
                }else {
                    if (idtipopago.length() != 2) {
                        Toast.makeText(TipoPagoActualizarActivity.this, "El id debe de contener dos caracteres", Toast.LENGTH_SHORT).show();
                    } else {
                        TipoPago tipoPago = new TipoPago();
                        tipoPago.setIdPago(idtipopago);
                        tipoPago.setTipo(nomtippag);

                        controlBDChristian.open();
                        String estado = controlBDChristian.actualizarTipoPago(tipoPago);
                        controlBDChristian.close();
                        Toast.makeText(TipoPagoActualizarActivity.this, estado, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}