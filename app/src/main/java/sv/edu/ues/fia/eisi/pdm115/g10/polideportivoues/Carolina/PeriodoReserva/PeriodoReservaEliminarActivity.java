package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PeriodoReservaEliminarActivity extends AppCompatActivity {
    ControlBDCarolina helper;
    EditText editPR;
    Button botonEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo_reserva_eliminar);
        helper = new ControlBDCarolina(this);

        editPR = (EditText) findViewById(R.id.idPeriodoReserva);
        botonEliminar = (Button) findViewById(R.id.botonEliminarPeriodoReserva);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idperiodoReserva=editPR.getText().toString();

                final String[] registrosEliminados = new String[1];
                PeriodoReserva periodo = new PeriodoReserva();
                periodo.setIdPeriodoReserva(editPR.getText().toString());
                AlertDialog.Builder confirmacion=new AlertDialog.Builder(PeriodoReservaEliminarActivity.this);

                if(idperiodoReserva.isEmpty()){
                    Toast.makeText(PeriodoReservaEliminarActivity.this, "Debe completar los campos para eliminar un periodo de reserva!", Toast.LENGTH_SHORT).show();
                }else if(idperiodoReserva.length()!=6){
                        Toast.makeText(PeriodoReservaEliminarActivity.this, "El id debe contener 6 carácteres", Toast.LENGTH_SHORT).show();
                    }else{
                    if(helper.verificarExisPeriodo(periodo)){
                        if(helper.verificarPeriodosReservaCascada(periodo)){

                            confirmacion.setMessage("Se han encontrado registros asociados al periodo de reserva en la tabla reservacion. ¿Desea eliminarlos también?")
                                    .setCancelable(false)
                                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            helper.open();
                                            registrosEliminados[0] = helper.eliminarPeriodoReservaCascada(periodo);
                                            helper.close();

                                            Toast.makeText(PeriodoReservaEliminarActivity.this, registrosEliminados[0], Toast.LENGTH_SHORT).show();
                                            editPR.setText("");
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(PeriodoReservaEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                        }else{
                            helper.open();
                            registrosEliminados[0] = helper.eliminarPeriodoReserva(periodo);
                            helper.close();

                            Toast.makeText(PeriodoReservaEliminarActivity.this, registrosEliminados[0], Toast.LENGTH_SHORT).show();
                            editPR.setText("");
                        }
                    }else{
                        Toast.makeText(PeriodoReservaEliminarActivity.this, "El período de reserva no existe!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void limpiar(View v) {
        editPR.setText("");
    }

}