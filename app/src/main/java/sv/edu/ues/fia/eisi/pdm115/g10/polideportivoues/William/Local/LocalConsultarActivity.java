package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalConsultarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    EditText editIdLocal;
    EditText editnomLocal;
    EditText editcupoLocal;
    Button btnconsultarLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        editnomLocal = findViewById(R.id.EditNombreLocal);
        editcupoLocal = findViewById(R.id.EditCupoLocal);
        btnconsultarLocal = findViewById(R.id.botonConsultarLocal);
    }
    public void consultarLocal(View v){
        String[] cupo= {"Disponible","Ocupado"};
        String idLocal=editIdLocal.getText().toString();
        if (idLocal.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            helper.open();
            Local local = helper.consultarLocal(idLocal);
            helper.close();
            if(local == null){
                Toast.makeText(LocalConsultarActivity.this, "Local con Id " + idLocal + " No encontrado", Toast.LENGTH_SHORT).show();
            }else{
                editnomLocal.setText(local.getNomLocal());
                editcupoLocal.setText(cupo[local.getCupo()]);
            }
        }
    }
    public void limpiar(View v){
        editIdLocal.setText("");
        editnomLocal.setText("");
        editcupoLocal.setText("");
    }
}