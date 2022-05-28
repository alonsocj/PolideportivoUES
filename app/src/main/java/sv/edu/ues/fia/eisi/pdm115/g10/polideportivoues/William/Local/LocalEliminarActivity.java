package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

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
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalEliminarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    TextInputEditText editIdLocal;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        helper = new ControlBDG10William(this);
        editIdLocal = findViewById(R.id.EditIdLocal);
        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }
    public void eliminarLocal(View v){
        hideKeyboard(v);
        String registrosEliminados;
        String idLocal = editIdLocal.getText().toString();
        AlertDialog.Builder confirmacion=new AlertDialog.Builder(LocalEliminarActivity.this);
        if (idLocal.isEmpty()){
            String mensaje;
            mensaje = "Los campos estan vacios, por favor completelos";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            Local local = new Local();
            local.setIdLocal(editIdLocal.getText().toString());
            helper.open();
            registrosEliminados = helper.eliminarLocal(local,0);
            helper.close();
            if(registrosEliminados.equals("2")){
                confirmacion.setMessage("El local no puede ser eliminado porque existen registros de horarios con este local. ¿Desea eliminarlos también?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                helper.open();
                                String registros = helper.eliminarLocal(local, 1);
                                helper.close();
                                Toast.makeText(LocalEliminarActivity.this, registros, Toast.LENGTH_SHORT).show();
                                editIdLocal.setText("");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(LocalEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }else{
                if(registrosEliminados.equals("3")) {
                    confirmacion.setMessage("El local no puede ser eliminado porque existen registros de horarios y reservaciones asociados con este local. ¿Desea eliminarlos también?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    helper.open();
                                    String registros = helper.eliminarLocal(local, 2);
                                    helper.close();
                                    Toast.makeText(LocalEliminarActivity.this, registros, Toast.LENGTH_SHORT).show();
                                    editIdLocal.setText("");
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(LocalEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                }
                else{
                    Toast.makeText(LocalEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
                }
            }
            editIdLocal.setText("");
        }
    }
    public void limpiar(View v){
        hideKeyboard(v);
        editIdLocal.setText("");
    }

    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(LocalEliminarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}