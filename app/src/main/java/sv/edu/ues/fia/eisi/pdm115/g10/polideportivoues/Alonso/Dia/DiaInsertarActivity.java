package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class DiaInsertarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editIdDia;
    Button btnInsertar, limpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_insertar);
        helper = new ControlBDG10Alonso(this);
        editIdDia = findViewById(R.id.editText_nom_dia);
        btnInsertar = findViewById(R.id.button_insertar);
        limpiar = findViewById(R.id.button_limpiar);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String idDia = editIdDia.getText().toString();
                    if (!idDia.isEmpty()) {
                        Dia dia = new Dia();
                        dia.setNombreDia(idDia);
                        helper.open();
                        helper.insertar(dia);
                        helper.close();
                        Toast.makeText(getApplicationContext(), "Se ha insertado un nuevo dia", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DiaInsertarActivity.this, "Error al insertar Dia", Toast.LENGTH_SHORT).show();
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
        editIdDia.setText("");
    }
}