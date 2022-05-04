package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class GeneroEliminarActivity extends AppCompatActivity {

    ControlBDGustavo helper;
    TextInputEditText editIdGenero;
    Button botonEliminar, botonVaciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_eliminar);

        //Control de la base de datos
        helper = new ControlBDGustavo(this);
        //Genero
        editIdGenero= findViewById(R.id.EditIdGenero);
        botonEliminar = (Button) findViewById(R.id.botonEliminarGenero);
        botonVaciar = (Button) findViewById(R.id.botonVaciarGenero);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registrosEliminados;
                Genero genero = new Genero();
                genero.setIdGenero(editIdGenero.getText().toString());
                final String[] registrosEliminadosCascada = new String[1];

                helper.open();
                registrosEliminadosCascada[0] = helper.eliminarGenero(genero);
                helper.close();
                Toast.makeText(GeneroEliminarActivity.this, registrosEliminadosCascada[0], Toast.LENGTH_SHORT).show();
                editIdGenero.setText("");
            }
        });
        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpiamos los campos
                editIdGenero.setText("");
            }
        });
    }
}