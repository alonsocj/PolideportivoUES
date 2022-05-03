package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago;

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

public class TipoPagoInsertarActivity extends AppCompatActivity {

    TextInputEditText editIdPago, editTipoPago;
    Button agregarTipoPago, botonLimpiar;
    ControlBDChristian helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_insertar);

        helper = new ControlBDChristian(this);
        editIdPago =  findViewById(R.id.EditIdPago);
        editTipoPago = findViewById(R.id.EditTipo);
        agregarTipoPago = findViewById(R.id.botonAgregarTipoP);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        agregarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPago = editIdPago.getText().toString();
                String tipoPago = editTipoPago.getText().toString();
                String pagosRegistrados;

                if(idPago.isEmpty() || tipoPago.isEmpty()){
                    Toast.makeText(TipoPagoInsertarActivity.this, "Debe de completar todos los campos para registrar un tipo de pago", Toast.LENGTH_SHORT).show();
                }else{
                    if(idPago.length() != 2){
                        Toast.makeText(TipoPagoInsertarActivity.this, "El id debe de contener dos caracteres", Toast.LENGTH_SHORT).show();
                    }else{
                        TipoPago tipoPago1 = new TipoPago();
                        tipoPago1.setIdPago(idPago);
                        tipoPago1.setTipo(tipoPago);
                        helper.open();
                        pagosRegistrados = helper.insertarTipoPago(tipoPago1);
                        helper.close();
                        Toast.makeText(TipoPagoInsertarActivity.this, pagosRegistrados, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdPago.setText("");
                editTipoPago.setText("");
            }
        });

    }
}