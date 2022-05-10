package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class DiaActualizarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editOldDia, editNewDia;
    Button btnActualizar, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_actualizar);
        helper = new ControlBDG10Alonso(this);
        editOldDia = (TextInputEditText) findViewById(R.id.editText_nom_dia_actual);
        editNewDia = (TextInputEditText) findViewById(R.id.editText_nom_dia_nuevo);
        btnActualizar = (Button) findViewById(R.id.button_actualizar);
        limpiar = findViewById(R.id.button_limpiar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Dia oldDia = new Dia();
                    Dia newDia = new Dia();
                    oldDia.setNombreDia(editOldDia.getText().toString());
                    newDia.setNombreDia(editNewDia.getText().toString());
                    helper.open();
                    String estado = helper.actualizar(oldDia, newDia);
                    helper.close();
                    Toast.makeText(getApplicationContext(), estado, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DiaActualizarActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarTexto(v);
            }
        });
    }

    public void limpiarTexto(View v) {
        editOldDia.setText("");
        editNewDia.setText("");
    }
}