package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoEliminarActivity extends AppCompatActivity {

    ControlBDG10 helper;
    EditText editIdTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_eliminar);
        helper = new ControlBDG10(this);
        editIdTipoEvento = (EditText) findViewById(R.id.editText_id);
    }
    public void eliminarTipoEvento(View view){
        String regEliminadas;
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setIdTipoE(editIdTipoEvento.getText().toString());
        helper.open();
        regEliminadas = helper.eliminar(tipoEvento);
        helper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}