package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoInsertarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdTipoEvento;
    TextInputEditText editNombreTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_insertar);
        helper = new ControlBDG10Alonso(this);
        editIdTipoEvento = findViewById(R.id.editText_idTipoE);
        editNombreTipoEvento = findViewById(R.id.EditTipoE);
    }

    public void insertarTipoEvento(View view) {
        try {
            String idTipoEvento = editIdTipoEvento.getText().toString();
            if(!idTipoEvento.isEmpty()) {
                String nombreTipoEvento = editNombreTipoEvento.getText().toString();
                String regInsertados;
                TipoEvento tipoEvento = new TipoEvento();
                tipoEvento.setIdTipoE(idTipoEvento);
                tipoEvento.setNombreTipoE(nombreTipoEvento);
                helper.open();
                regInsertados = helper.insertar(tipoEvento);
                helper.close();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al insertar el tipo de evento", Toast.LENGTH_SHORT).show();
        }
    }
}