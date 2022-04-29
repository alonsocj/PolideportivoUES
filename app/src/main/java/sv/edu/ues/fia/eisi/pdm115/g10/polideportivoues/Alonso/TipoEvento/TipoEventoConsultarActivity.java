package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoConsultarActivity extends AppCompatActivity {

    ControlBDG10 helper;
    EditText editIdTipoEvento;
    EditText editNombreTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_consultar);
        helper = new ControlBDG10(this);
        editIdTipoEvento = (EditText) findViewById(R.id.editText_id);
        editNombreTipoEvento = (EditText) findViewById(R.id.editText_nombre);
    }

    public void consultarTipoEvento(View view){
        helper.open();
        TipoEvento tipoEvento = helper.consultar(editIdTipoEvento.getText().toString());
        helper.close();
        if(tipoEvento == null){
            Toast.makeText(this, "Tipo de evento con id '"+editIdTipoEvento.getText().toString()+ "' no encontrado", Toast.LENGTH_SHORT).show();
        }else{
            editNombreTipoEvento.setText(tipoEvento.getNombreTipoE());
        }
    }
}