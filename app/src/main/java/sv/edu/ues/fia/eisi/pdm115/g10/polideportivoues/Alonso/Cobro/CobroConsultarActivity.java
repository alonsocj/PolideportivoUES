package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class CobroConsultarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdCobro, editTipoPago, editCantPersonas, editDuracion, editPrecio;
    Button btnConsultar, limpiar;
    LinearLayout linearLayout;
    SQLiteDatabase db;

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
        btnConsultar = findViewById(R.id.button_consultar);
        linearLayout = findViewById(R.id.linearLayout);
        limpiar = findViewById(R.id.button_limpiar);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                helper.open();
                Cobro cobro = helper.consultarCobro(editIdCobro.getText().toString());
                helper.close();


                if (cobro == null) {
                    Toast.makeText(CobroConsultarActivity.this, "Cobro no encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    db = openOrCreateDatabase("polideportivo.s3db", MODE_PRIVATE, null);
                    Cursor cursor = db.rawQuery("SELECT * FROM tipopago WHERE idPago = '" + cobro.getIdPago() + "'", null);
                    cursor.moveToFirst();
                    String tipoPago = cursor.getString(1);
                    editCantPersonas.setText(String.valueOf(cobro.getCantPersonas()));
                    editDuracion.setText(String.valueOf(cobro.getDuracionTexto()));
                    editPrecio.setText(String.valueOf(cobro.getPrecio()));
                    editTipoPago.setText(tipoPago);
                }
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarTexto(v);
            }
        });
    }

    public void limpiarTexto(View v) {
        editIdCobro.setText("");
        editTipoPago.setText("");
        editCantPersonas.setText("");
        editDuracion.setText("");
        editPrecio.setText("");
    }

    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(CobroInsertarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}