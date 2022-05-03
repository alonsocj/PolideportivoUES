package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class HorariosDisponiblesEliminarActivity extends AppCompatActivity {

    ControlBDGustavo helper;
    TextInputEditText editIdHora;
    Button botonEliminar, botonVaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles_eliminar);

        //Control de la base de datos
        helper = new ControlBDGustavo(this);

        editIdHora = findViewById(R.id.EditIdHorariosDisponibles);
        botonEliminar = (Button) findViewById(R.id.botonEliminarHorarioDisponible);
        botonVaciar = (Button) findViewById(R.id.botonVaciarHorarioDisponible);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HorariosDisponibles horariosDisponibles = new HorariosDisponibles();
                horariosDisponibles.setIdHorario(editIdHora.getText().toString());

                AlertDialog.Builder confirmacion=new AlertDialog.Builder(HorariosDisponiblesEliminarActivity.this);
                final String[] registrosEliminadosCascada = new String[1];

                if(helper.verificarExiHorariosDisponibles(horariosDisponibles)){
                    if(helper.verificarHorariosDisponiblesCascada(horariosDisponibles)){
                        confirmacion.setMessage("Se han encontrado registros asociados a la disponibilidad en la tabla horario de locales. ¿Desea eliminarlos también?")
                                .setCancelable(false)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        helper.open();
                                        registrosEliminadosCascada[0] = helper.eliminarHorariosDisponiblesCascada(horariosDisponibles);
                                        helper.close();

                                        Toast.makeText(HorariosDisponiblesEliminarActivity.this, registrosEliminadosCascada[0], Toast.LENGTH_SHORT).show();
                                        editIdHora.setText("");
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(HorariosDisponiblesEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }else{
                        helper.open();
                        registrosEliminadosCascada[0] = helper.eliminarHorarioDisponible(horariosDisponibles);
                        helper.close();
                        Toast.makeText(HorariosDisponiblesEliminarActivity.this, registrosEliminadosCascada[0], Toast.LENGTH_SHORT).show();
                        editIdHora.setText("");
                    }
                }else{
                    Toast.makeText(HorariosDisponiblesEliminarActivity.this, "Registro no existe!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIdHora.setText("");
            }
        });
    }
}