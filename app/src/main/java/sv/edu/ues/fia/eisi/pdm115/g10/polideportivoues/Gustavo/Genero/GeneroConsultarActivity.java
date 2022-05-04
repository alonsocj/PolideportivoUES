package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero;

import androidx.appcompat.app.AppCompatActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class GeneroConsultarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdGenero, editGenero;
    Button botonConsultar, botonVaciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_consultar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);
        //Genero
        editIdGenero= findViewById(R.id.EditIdGenero);
        editGenero = findViewById(R.id.EditGenero);
        botonConsultar = (Button) findViewById(R.id.botonConsultarGenero);
        botonVaciar = (Button) findViewById(R.id.botonVaciarGenero);

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String generoString=editIdGenero.getText().toString();

                if(generoString.isEmpty()) {
                    Toast.makeText(GeneroConsultarActivity.this, "Debe completar los campos para consultar una género!", Toast.LENGTH_SHORT).show();
                }else{
                    controlBDGustavo.open();
                    Genero genero = controlBDGustavo.consultarGenero(editIdGenero.getText().toString());
                    controlBDGustavo.close();

                    /*Verificación que exista la Persona*/
                    if(genero == null){
                        Toast.makeText(GeneroConsultarActivity.this, "Registro no encontrado!", Toast.LENGTH_SHORT).show();
                        //Limpiamos los campos
                        editIdGenero.setText("");
                        editGenero.setText("");
                    }else{
                        editGenero.setText(genero.getGenero());
                    }
                }
            }
        });
        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpiamos los campos
                editIdGenero.setText("");
                editGenero.setText("");
            }
        });
    }
}