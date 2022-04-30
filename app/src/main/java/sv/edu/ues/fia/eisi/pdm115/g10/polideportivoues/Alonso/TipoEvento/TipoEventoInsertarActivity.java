package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoInsertarActivity extends AppCompatActivity {

    ControlBDG10 helper;
    EditText editIdTipoEvento;
    EditText editNombreTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_insertar);
        helper = new ControlBDG10(this);
        editIdTipoEvento = (EditText) findViewById(R.id.editText_id);
        editNombreTipoEvento = (EditText) findViewById(R.id.editText_nombre);
    }

    public void insertarTipoEvento(View view){
        String idTipoEvento = editIdTipoEvento.getText().toString();
        String nombreTipoEvento = editNombreTipoEvento.getText().toString();
        String regInsertados;
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setIdTipoE(idTipoEvento);
        tipoEvento.setNombreTipoE(nombreTipoEvento);
        helper.open();
        regInsertados = helper.insertar(tipoEvento);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}