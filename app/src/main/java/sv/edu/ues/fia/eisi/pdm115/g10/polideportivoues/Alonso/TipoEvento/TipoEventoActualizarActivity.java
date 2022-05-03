package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoActualizarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    EditText editIdTipoEvento;
    EditText editNombreTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_actualizar);
        helper = new ControlBDG10Alonso(this);
        editIdTipoEvento = (EditText) findViewById(R.id.editText_id);
        editNombreTipoEvento = (EditText) findViewById(R.id.editText_nombre);
    }

    public void actualizarTipoEvento(View view) {
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setIdTipoE(editIdTipoEvento.getText().toString());
        tipoEvento.setNombreTipoE(editNombreTipoEvento.getText().toString());
        helper.open();
        String estado = helper.actualizar(tipoEvento);
        helper.close();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
}