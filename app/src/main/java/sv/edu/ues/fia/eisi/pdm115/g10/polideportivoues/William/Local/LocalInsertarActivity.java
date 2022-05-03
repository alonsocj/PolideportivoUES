package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

public class LocalInsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ControlBDG10William helper;
    EditText editIdLocal;
    EditText editnomLocal;
    Spinner spinnercupoLocal;
    Button btnagregarLocal;
    ArrayAdapter<String> arrayS;
    String [] arreglo = new String[]{"Disponible", "ocupado"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        editnomLocal = findViewById(R.id.EditNombreLocal);
        spinnercupoLocal = findViewById(R.id.SpinnerCupoLocal);
        btnagregarLocal = findViewById(R.id.botonAgregarLocal);
        spinnercupoLocal.setOnItemSelectedListener(this);
        arrayS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arreglo);
        spinnercupoLocal.setAdapter(arrayS);
    }

    public void insertarLocal(View v){
        String idLocal=editIdLocal.getText().toString();
        String nombreLocal=editnomLocal.getText().toString();
        int indiceCupo = spinnercupoLocal.getSelectedItemPosition();
        if (idLocal.isEmpty()||nombreLocal.isEmpty()){
            String mensaje;
            if (idLocal.isEmpty()&&nombreLocal.isEmpty()) {
                mensaje = "Los campos estan vacios, por favor completelos";
            }else{
                if (idLocal.isEmpty()){
                    mensaje = "El local no se puede ingresar, no se ha digitado el id del local";
                }else{
                    mensaje = "El local no se puede ingresar, no se ha digitado el nombre del local";
                }
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            String regInsertados;
            Local local = new Local();
            local.setIdLocal(idLocal);
            local.setNomLocal(nombreLocal);
            local.setCantidadPersonas(indiceCupo);
            helper.open();
            regInsertados = helper.ingresarLocal(local);
            helper.close();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiar(View v){
        editIdLocal.setText("");
        editnomLocal.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}