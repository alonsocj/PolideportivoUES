package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoPagoConsultarActivity extends AppCompatActivity {

    ControlBDG10 helper;
    EditText editIdPago, editTipo;
    Button consultarTipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_consultar);

        helper = new ControlBDG10(this);
        editIdPago = findViewById(R.id.EditIdPagoConsultar);
        editTipo = findViewById(R.id.EditTipoConsultar);
        consultarTipoPago = findViewById(R.id.consultar);

        consultarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.open();
                TipoPago tipoPago = helper.consultarTipoPago(editIdPago.getText().toString());
                helper.close();
                if(tipoPago == null){
                    Toast.makeText(TipoPagoConsultarActivity.this, "Tipo de pago con codigo: " +editIdPago.getText().toString() +" no existe", Toast.LENGTH_SHORT).show();
                }else{
                    editTipo.setText(tipoPago.getTipo());
                }
            }
        });

    }
}