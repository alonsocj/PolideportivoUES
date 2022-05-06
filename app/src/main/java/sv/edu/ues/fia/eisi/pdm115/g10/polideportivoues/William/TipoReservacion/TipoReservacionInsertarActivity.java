package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalConsultarActivity;

public class TipoReservacionInsertarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdTipoReservacion;
    TextInputEditText editNombreTipoReservacion;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_insertar);
        helper = new ControlBDG10William(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
        editNombreTipoReservacion = findViewById(R.id.EditNombreTipoReservacion);
        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }
    public void insertarTipoReservacion(View v){
        hideKeyboard(v);
        String idTipoReservacion=editIdTipoReservacion.getText().toString();
        String nombreTipoReservacion=editNombreTipoReservacion.getText().toString();
        if (idTipoReservacion.isEmpty()||nombreTipoReservacion.isEmpty()){
            String mensaje;
            if (idTipoReservacion.isEmpty()&&nombreTipoReservacion.isEmpty()) {
                mensaje = "Los campos estan vacios, por favor completelos";
            }else{
                if (idTipoReservacion.isEmpty()){
                    mensaje = "El tipo de reservacion no se puede ingresar, no se ha digitado el id";
                }else{
                    mensaje = "El tipo de reservacion no se puede ingresar, no se ha digitado el nombre";
                }
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else {
            String regInsertados;
            TipoReservacion tipoReservacion = new TipoReservacion();
            tipoReservacion.setIdTipoR(idTipoReservacion);
            tipoReservacion.setNomTipoR(nombreTipoReservacion);
            helper.open();
            regInsertados = helper.ingresarTipoReservacion(tipoReservacion);
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            editIdTipoReservacion.setText("");
            editNombreTipoReservacion.setText("");
        }
    }
    public void limpiar(View v){
        hideKeyboard(v);
        editIdTipoReservacion.setText("");
        editNombreTipoReservacion.setText("");
    }
    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(TipoReservacionInsertarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}