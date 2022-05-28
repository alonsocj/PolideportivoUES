package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class TipoEventoEliminarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdTipoEvento;
    Button limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_eliminar);
        helper = new ControlBDG10Alonso(this);
        editIdTipoEvento = findViewById(R.id.editText_idTipoE);
        limpiar = findViewById(R.id.button_limpiar);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarTexto(v);
            }
        });
    }

    public void eliminarTipoEvento(View view) {
        try {
            String regEliminadas;
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoE(editIdTipoEvento.getText().toString());
            helper.open();
            regEliminadas = helper.eliminar(tipoEvento);
            helper.close();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al eliminar datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View view) {
        editIdTipoEvento.setText("");
    }
}