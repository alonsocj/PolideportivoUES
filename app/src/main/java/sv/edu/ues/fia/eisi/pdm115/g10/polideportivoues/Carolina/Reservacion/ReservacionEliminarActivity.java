package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.Nacionalidad;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.NacionalidadEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class ReservacionEliminarActivity extends AppCompatActivity {
    TextInputEditText editIdReservacion;
    ControlBDCarolina helper;
    Button botonEliminar;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_eliminar);
        helper = new ControlBDCarolina(this);

        editIdReservacion=(TextInputEditText) findViewById(R.id.idReservacion);
        botonEliminar = (Button) findViewById(R.id.botonEliminarReservacion);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idReservacion=editIdReservacion.getText().toString();

                final String[] registrosEliminados = new String[1];
                Reservacion re = new Reservacion();
                re.setIdReservacion(editIdReservacion.getText().toString());
                AlertDialog.Builder confirmacion=new AlertDialog.Builder(ReservacionEliminarActivity.this);

                if(idReservacion.isEmpty()){
                    Toast.makeText(ReservacionEliminarActivity.this, "Debe completar los campos para eliminar una reservación!", Toast.LENGTH_SHORT).show();
                }else if(idReservacion.length()!=6){
                    Toast.makeText(ReservacionEliminarActivity.this, "El id debe contener 6 carácteres", Toast.LENGTH_SHORT).show();
                }else{
                    if(helper.verificarExisReservacion(re)){
                        confirmacion.setMessage("¿Desea eliminar la reservación?")
                                .setTitle("Advertencia")
                                .setCancelable(false)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        helper.open();
                                        registrosEliminados[0] = helper.eliminarReservacion(re);
                                        helper.close();

                                        Toast.makeText(ReservacionEliminarActivity.this, registrosEliminados[0], Toast.LENGTH_SHORT).show();
                                        editIdReservacion.setText("");
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(ReservacionEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "El id de reservación no existe!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void limpiar(View v) {editIdReservacion.setText("");}
}