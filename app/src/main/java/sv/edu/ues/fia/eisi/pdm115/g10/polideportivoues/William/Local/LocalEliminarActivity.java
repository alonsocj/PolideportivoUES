package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.HoraEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalEliminarActivity extends AppCompatActivity {
    ControlBDG10 helper;
    EditText editIdLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        helper = new ControlBDG10(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
    }
    public void eliminarLocal(View v){
        String registrosEliminados;
        Local local = new Local();
        local.setIdLocal(editIdLocal.getText().toString());
        helper.open();
        registrosEliminados = helper.eliminarLocal(local);
        helper.close();
        Toast.makeText(LocalEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editIdLocal.setText("");
    }
}