package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class DiaConsultarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    TextInputEditText editDia;
    Button btnConsultar, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_consultar);
        helper = new ControlBDG10Alonso(this);
        editDia = findViewById(R.id.editText_nom_dia);
        btnConsultar = findViewById(R.id.button_consultar);
        limpiar = findViewById(R.id.button_limpiar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    helper.open();
                    Dia dia = helper.consultarDia(editDia.getText().toString());
                    helper.close();
                    if (dia == null) {
                        Toast.makeText(DiaConsultarActivity.this, "No se ha encontrado el dia solicitado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DiaConsultarActivity.this, "El dia: " + dia.getNombreDia() + " si se encuentra en la base de datos", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(DiaConsultarActivity.this, "Error al consultar, llene el campo correspodiente", Toast.LENGTH_SHORT).show();
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
        editDia.setText("");
    }
}