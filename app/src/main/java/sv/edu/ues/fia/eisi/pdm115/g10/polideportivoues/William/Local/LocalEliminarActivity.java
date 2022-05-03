package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalEliminarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    EditText editIdLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
    }
    public void eliminarLocal(View v){
        String registrosEliminados;
        String idLocal = editIdLocal.getText().toString();
        if (idLocal.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            Local local = new Local();
            local.setIdLocal(editIdLocal.getText().toString());
            helper.open();
            registrosEliminados = helper.eliminarLocal(local);
            helper.close();
            Toast.makeText(LocalEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
            editIdLocal.setText("");
        }
    }
    public void limpiar(View v){
        editIdLocal.setText("");
    }
}