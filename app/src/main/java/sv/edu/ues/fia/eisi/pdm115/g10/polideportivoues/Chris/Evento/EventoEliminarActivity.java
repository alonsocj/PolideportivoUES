package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDChristian;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class EventoEliminarActivity extends AppCompatActivity {

    ControlBDChristian helper;
    Button eliminarevent, botonLimpiar;
    TextInputEditText editIdEv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_eliminar);

        helper = new ControlBDChristian(this);
        editIdEv = findViewById(R.id.EditIdNumeroEventoEliminar);
        eliminarevent = findViewById(R.id.botonEliminarEvent);
        botonLimpiar = findViewById(R.id.botonLimpiar);

        eliminarevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Eliminacion con cascada
                /*String idEvento = editIdEv.getText().toString();
                final String[] registrosEliminados = new String[1];
                Evento evento = new Evento();
                evento.setIdEvento(idEvento);
                AlertDialog.Builder confirmarEliminarEvento = new AlertDialog.Builder(EventoEliminarActivity.this);

                if(idEvento.isEmpty()){
                    Toast.makeText(EventoEliminarActivity.this, "Debe de ingresar un id para eliminar un evento", Toast.LENGTH_SHORT).show();
                }else if(idEvento.length() != 6){
                    Toast.makeText(EventoEliminarActivity.this, "El id del evento debe de tener 6 caracteres", Toast.LENGTH_SHORT).show();
                }else{
                  if(helper.verificarExisEvento(evento)){
                      //En si si elimina en cascada pero si se confirma
                      if(helper.verificarEventosCascada(evento)){
                          confirmarEliminarEvento.setMessage("Se han encontado registros asociados al evento en la tabla de reservacion, ¿Desea eliminarlos en la tabla de reservacion tambien?")
                                  .setCancelable(false)
                                  .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialogInterface, int i) {
                                          helper.open();
                                          registrosEliminados[0] = helper.eliminarEventosCascada(evento);
                                          helper.close();
                                      }
                                  })
                                  .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialogInterface, int i) {
                                          Toast.makeText(EventoEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                                      }
                                  }).show();
                      }else{
                          helper.open();
                          registrosEliminados[0] = helper.eliminarEvento(evento);
                          helper.close();

                          Toast.makeText(EventoEliminarActivity.this, registrosEliminados[0], Toast.LENGTH_SHORT).show();
                          editIdEv.setText("");
                      }
                  }else{
                      Toast.makeText(EventoEliminarActivity.this, "No existe este id de evento", Toast.LENGTH_SHORT).show();
                  }
                }*/

                /*Sin cascada*/
                String registroseliminados;
                Evento evento = new Evento();
                evento.setIdEvento(editIdEv.getText().toString());

                if (editIdEv.getText().toString().equals("")) {
                    Toast.makeText(EventoEliminarActivity.this, "Debes de ingresar un idEvento!", Toast.LENGTH_SHORT).show();
                } else if (editIdEv.getText().toString().length() != 6) {
                    Toast.makeText(EventoEliminarActivity.this, "Debes de ingresar un idEvento de 6 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    helper.open();
                    registroseliminados = helper.eliminarEvento(evento);
                    helper.close();
                    Toast.makeText(EventoEliminarActivity.this, registroseliminados, Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdEv.setText("");
            }
        });

    }
}