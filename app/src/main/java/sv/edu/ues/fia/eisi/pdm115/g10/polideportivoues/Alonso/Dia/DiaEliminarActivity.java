package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class DiaEliminarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editNomDia;
    Button btnEliminar, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_eliminar);
        helper = new ControlBDG10Alonso(this);
        editNomDia = (TextInputEditText) findViewById(R.id.editText_nom_dia);
        btnEliminar = (Button) findViewById(R.id.button_eliminar);
        limpiar = findViewById(R.id.button_limpiar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    String regEliminado;
                    Dia dia = new Dia();
                    dia.setNombreDia(editNomDia.getText().toString());
                    helper.open();
                    regEliminado = helper.eliminar(dia);
                    helper.close();
                    Toast.makeText(getApplicationContext(), regEliminado, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DiaEliminarActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
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
        editNomDia.setText("");
    }
}