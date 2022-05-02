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

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalActualizarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ControlBDG10 helper;
    EditText editIdLocal;
    EditText editnomLocal;
    Spinner spinnercupoLocal;
    Button btnactualizarLocal;
    ArrayAdapter<String> arrayS;
    String [] arreglo = new String[]{"Disponible", "ocupado"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);
        helper = new ControlBDG10(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        editnomLocal = findViewById(R.id.EditNombreLocal);
        spinnercupoLocal = findViewById(R.id.SpinnerCupoLocal);
        btnactualizarLocal = findViewById(R.id.botonActualizarLocal);
        spinnercupoLocal.setOnItemSelectedListener(this);
        arrayS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arreglo);
        spinnercupoLocal.setAdapter(arrayS);
    }
    public void actualizarLocal(View v){
        String idLocal=editIdLocal.getText().toString();
        String nombreLocal=editnomLocal.getText().toString();
        int indiceCupo = spinnercupoLocal.getSelectedItemPosition();
        String regInsertados;
        Local local = new Local();
        local.setIdLocal(idLocal);
        local.setNomLocal(nombreLocal);
        local.setCupo(indiceCupo);
        helper.open();
        regInsertados = helper.actualizarLocal(local);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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