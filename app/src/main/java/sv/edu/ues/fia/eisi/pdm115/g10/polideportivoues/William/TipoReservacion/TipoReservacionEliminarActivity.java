package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalEliminarActivity;


public class TipoReservacionEliminarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdTipoReservacion;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reservacion_eliminar);
        helper = new ControlBDG10William(this);
        editIdTipoReservacion = findViewById(R.id.EditIdTipoReservacion);
        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }
    public void eliminarTipoReservacion(View v){
        hideKeyboard(v);
        String idTipoR = editIdTipoReservacion.getText().toString();
        AlertDialog.Builder confirmacion=new AlertDialog.Builder(TipoReservacionEliminarActivity.this);
        if (idTipoR.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else {
            String registrosEliminados;
            TipoReservacion tipoReservacion = new TipoReservacion();
            tipoReservacion.setIdTipoR(editIdTipoReservacion.getText().toString());
            helper.open();
            registrosEliminados = helper.eliminarTipoReservacion(tipoReservacion,0);
            helper.close();
            if(registrosEliminados.equals("1")){
                confirmacion.setMessage("El tipo de reservacion no puede ser eliminado porque existen registros de reservacion con este tipo. ¿Desea eliminarlos también?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                helper.open();
                                String registros = helper.eliminarTipoReservacion(tipoReservacion, 1);
                                helper.close();
                                Toast.makeText(TipoReservacionEliminarActivity.this, registros, Toast.LENGTH_SHORT).show();
                                editIdTipoReservacion.setText("");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(TipoReservacionEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }else{
                Toast.makeText(TipoReservacionEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
            }
            editIdTipoReservacion.setText("");
        }
    }
    public void limpiar(View v){
        hideKeyboard(v);
        editIdTipoReservacion.setText("");
    }

    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(TipoReservacionEliminarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}