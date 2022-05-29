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

public class TipoPagoConsultarActivity extends AppCompatActivity {

    ControlBDChristian helper;
    TextInputEditText editIdPago, editTipo;
    Button consultarTipoPago, botonLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_consultar);

        helper = new ControlBDChristian(this);
        editIdPago = findViewById(R.id.EditIdPagoConsultar);
        editTipo = findViewById(R.id.EditTipoConsultar);
        consultarTipoPago = findViewById(R.id.consultar);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        consultarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.open();
                TipoPago tipoPago = helper.consultarTipoPago(editIdPago.getText().toString());
                helper.close();

                if(editIdPago.getText().toString().equals("")){
                    Toast.makeText(TipoPagoConsultarActivity.this, "Debes de ingresar un idPago!", Toast.LENGTH_SHORT).show();
                }else if(editIdPago.getText().toString().length() != 2){
                    Toast.makeText(TipoPagoConsultarActivity.this, "Debes de ingresar un idPago de 2 caracteres", Toast.LENGTH_SHORT).show();
                }else if(tipoPago == null){
                    Toast.makeText(TipoPagoConsultarActivity.this, "Tipo de pago con codigo: " +editIdPago.getText().toString() +" no existe", Toast.LENGTH_SHORT).show();
                }else{
                    editTipo.setText(tipoPago.getTipo());
                }
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdPago.setText("");
                editTipo.setText("");
            }
        });

    }
}