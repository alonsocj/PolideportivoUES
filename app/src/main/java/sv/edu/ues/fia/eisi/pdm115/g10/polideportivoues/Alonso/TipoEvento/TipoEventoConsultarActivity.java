package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoConsultarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdTipoEvento;
    TextInputEditText editNombreTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_consultar);
        helper = new ControlBDG10Alonso(this);
        editIdTipoEvento = findViewById(R.id.editText_idTipoE);
        editNombreTipoEvento = findViewById(R.id.EditTipoE);
    }

    public void consultarTipoEvento(View view) {
        try {
            helper.open();
            TipoEvento tipoEvento = helper.consultarTipoEvento(editIdTipoEvento.getText().toString());
            helper.close();
            if (tipoEvento == null) {
                Toast.makeText(this, "Tipo de evento con id '" + editIdTipoEvento.getText().toString() + "' no encontrado", Toast.LENGTH_SHORT).show();
            } else {
                editNombreTipoEvento.setText(tipoEvento.getNombreTipoE());
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al consultar el tipo de evento", Toast.LENGTH_SHORT).show();
        }
    }
}