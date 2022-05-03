package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalActualizarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdLocal;
    TextInputEditText editnomLocal;
    TextInputEditText editCantLocal;
    Button btnagregarLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        editnomLocal = findViewById(R.id.EditNombreLocal);
        editCantLocal = findViewById(R.id.EditCantidadPersonas);
    }
    public void actualizarLocal(View v) {
        String idLocal = editIdLocal.getText().toString();
        String nombreLocal = editnomLocal.getText().toString();
        String cantPS = editCantLocal.getText().toString();
        int cantP;
        if (idLocal.isEmpty() || nombreLocal.isEmpty() || cantPS.isEmpty()) {
            String mensaje;
            if (idLocal.isEmpty() && nombreLocal.isEmpty() && cantPS.isEmpty()) {
                mensaje = "Los campos estan vacios, por favor completelos";
            } else {
                if (idLocal.isEmpty()) {
                    mensaje = "El local no se puede actualizar, no se ha digitado el id del local";
                } else {
                    if (nombreLocal.isEmpty()) {
                        mensaje = "El local no se puede actualizar, no se ha digitado el nombre del local";
                    } else {
                        mensaje = "El local no se puede actualizar, no se ha digitado la cantidad de personas permitidas en el local";
                    }
                }
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        } else {
            cantP = Integer.parseInt(cantPS);
            if (cantP <= 0) {
                String mensaje1 = "Digite una cantidad de personas mayor a 0";
                Toast.makeText(this, mensaje1, Toast.LENGTH_SHORT).show();
            } else {
                String regInsertados;
                Local local = new Local();
                local.setIdLocal(idLocal);
                local.setNomLocal(nombreLocal);
                local.setCantidadPersonas(cantP);
                helper.open();
                regInsertados = helper.actualizarLocal(local);
                helper.close();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
                editIdLocal.setText("");
                editnomLocal.setText("");
                editCantLocal.setText("");
            }
        }
    }
    public void limpiar(View v){
        editIdLocal.setText("");
        editnomLocal.setText("");
        editCantLocal.setText("");
    }

}