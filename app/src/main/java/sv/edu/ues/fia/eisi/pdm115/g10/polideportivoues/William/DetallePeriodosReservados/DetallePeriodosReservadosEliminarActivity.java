package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetallePeriodosReservadosEliminarActivity extends AppCompatActivity {
    EditText EditIdPeriodoReservaD;
    EditText EditIdHorariosDisponiblesD;
    ControlBDG10 helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_periodos_reservados_eliminar);
        helper = new ControlBDG10(this);
        EditIdPeriodoReservaD = findViewById(R.id.EditIdPeriodoReservaD);
        EditIdHorariosDisponiblesD = findViewById(R.id.EditIdHorariosDisponiblesD);
    }
    public void eliminarDetallePeriodosR(View v){

    }
    public void limpiar(View v){
        EditIdPeriodoReservaD.setText("");
        EditIdHorariosDisponiblesD.setText("");
    }
}