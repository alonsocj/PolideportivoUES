package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.CobroInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalConsultarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdLocal;
    TextInputEditText editnomLocal;
    TextInputEditText editCantidadPersonas;
    Button btnconsultarLocal;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        editnomLocal = findViewById(R.id.EditNombreLocal);
        editCantidadPersonas = findViewById(R.id.EditCantidadPersonas);
        btnconsultarLocal = findViewById(R.id.botonConsultarLocal);
        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }
    public void consultarLocal(View v){
        hideKeyboard(v);
        String idLocal=editIdLocal.getText().toString();
        if (idLocal.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            helper.open();
            Local local = helper.consultarLocal(idLocal);
            helper.close();
            if(local == null){
                Toast.makeText(LocalConsultarActivity.this, "Local con Id " + idLocal + " No encontrado", Toast.LENGTH_SHORT).show();
            }else{
                editnomLocal.setText(local.getNomLocal());
                editCantidadPersonas.setText(String.valueOf(local.getCantidadPersonas()));
            }
        }
    }
    public void limpiar(View v){
        hideKeyboard(v);
        editIdLocal.setText("");
        editnomLocal.setText("");
        editCantidadPersonas.setText("");
    }
    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(LocalConsultarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}