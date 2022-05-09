package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

public class LocalInsertarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdLocal;
    TextInputEditText editnomLocal;
    TextInputEditText editCantLocal;
    Button btnagregarLocal;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        editnomLocal = findViewById(R.id.EditNombreLocal);
        editCantLocal = findViewById(R.id.EditCantidadPersonas);
        btnagregarLocal = findViewById(R.id.botonAgregarLocal);
        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }

    public void insertarLocal(View v){
        hideKeyboard(v);
        String idLocal=editIdLocal.getText().toString();
        String nombreLocal=editnomLocal.getText().toString();
        String cantPS = editCantLocal.getText().toString();
        int cantP;
        if (idLocal.isEmpty()||nombreLocal.isEmpty()||cantPS.isEmpty()){
            String mensaje;
            if (idLocal.isEmpty()&&nombreLocal.isEmpty()&&cantPS.isEmpty()) {
                mensaje = "Los campos estan vacios, por favor completelos";
            }else{
                if (idLocal.isEmpty()){
                    mensaje = "El local no se puede ingresar, no se ha digitado el id del local";
                }else{
                    if (nombreLocal.isEmpty()){
                        mensaje = "El local no se puede ingresar, no se ha digitado el nombre del local";
                    }else{
                            mensaje = "El local no se puede ingresar, no se ha digitado la cantidad de personas permitidas en el local";
                    }
                }
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            cantP = Integer.parseInt(cantPS);
            if(cantP<=0) {
                String mensaje1 = "Digite una cantidad de personas mayor a 0";
                Toast.makeText(this, mensaje1, Toast.LENGTH_SHORT).show();
            }
            else{
                String regInsertados;
                Local local = new Local();
                local.setIdLocal(idLocal);
                local.setNomLocal(nombreLocal);
                local.setCantidadPersonas(cantP);
                helper.open();
                regInsertados = helper.ingresarLocal(local);
                helper.close();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
                editIdLocal.setText("");
                editnomLocal.setText("");
                editCantLocal.setText("");
            }

        }
    }

    public void limpiar(View v){
        hideKeyboard(v);
        editIdLocal.setText("");
        editnomLocal.setText("");
        editCantLocal.setText("");
    }

    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(LocalInsertarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}