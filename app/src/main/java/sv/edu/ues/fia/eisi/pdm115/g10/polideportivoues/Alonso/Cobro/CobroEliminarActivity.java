package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class CobroEliminarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdCobro;
    Button btnEliminar, limpiar;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro_eliminar);
        helper = new ControlBDG10Alonso(this);
        editIdCobro = (TextInputEditText) findViewById(R.id.editText_id);
        btnEliminar = (Button) findViewById(R.id.button_eliminar);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        limpiar = (Button) findViewById(R.id.button_limpiar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String regEliminados;
                    Cobro cobro = new Cobro();
                    cobro.setIdCobro(Integer.parseInt(Objects.requireNonNull(editIdCobro.getText()).toString()));
                    helper.open();
                    regEliminados = helper.eliminar(cobro);
                    helper.close();
                    editIdCobro.setText("");
                    Toast.makeText(CobroEliminarActivity.this, regEliminados, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(CobroEliminarActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
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
    }

    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(CobroInsertarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}