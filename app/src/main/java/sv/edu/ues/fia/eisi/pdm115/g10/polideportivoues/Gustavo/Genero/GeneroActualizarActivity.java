package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class GeneroActualizarActivity extends AppCompatActivity {

    ControlBDGustavo controlBDGustavo;
    TextInputEditText editIdGenero, editGenero;
    Button botonActualizar, botonVaciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_actualizar);

        //Control de la base de datos
        controlBDGustavo = new ControlBDGustavo(this);
        //Genero
        editIdGenero= findViewById(R.id.EditIdGenero);
        editGenero = findViewById(R.id.EditGenero);
        botonActualizar = (Button) findViewById(R.id.botonActualizarGenero);
        botonVaciar = (Button) findViewById(R.id.botonVaciarGenero);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idGenero = editIdGenero.getText().toString();
                String genero = editGenero.getText().toString();
                boolean verdadero=true;
                String insertarRegistros;
                if(idGenero.isEmpty()||genero.isEmpty()){
                    Toast.makeText(GeneroActualizarActivity.this, "Debe completar los campos para registrar un género!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idGenero.length()!=6){
                        Toast.makeText(GeneroActualizarActivity.this, "El Id Género debe contener 6 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }
                    if(verdadero){
                        Genero generoObj = new Genero();
                        generoObj.setIdGenero(idGenero);
                        generoObj.setGenero(genero);
                        controlBDGustavo.open();
                        insertarRegistros = controlBDGustavo.actualizarGenero(generoObj);
                        controlBDGustavo.close();
                        Toast.makeText(GeneroActualizarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                        if(insertarRegistros=="Registro duplicado!"){
                            //Limpiamos los campos
                            editIdGenero.setText("");
                        }else{
                            //Limpiamos los campos
                            editIdGenero.setText("");
                            editGenero.setText("");
                        }
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