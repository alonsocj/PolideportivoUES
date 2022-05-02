package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetallePeriodosReservadosConsultarActivity extends AppCompatActivity {
    EditText EditIdPeriodoReservaD;
    EditText EditIdHorariosDisponiblesD;
    EditText EditFechaDetalle;
    DatePickerDialog.OnDateSetListener setListener;
    ControlBDG10 helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_periodos_reservados_consultar);
        helper = new ControlBDG10(this);
        EditIdPeriodoReservaD = findViewById(R.id.EditIdPeriodoReservaD);
        EditIdHorariosDisponiblesD = findViewById(R.id.EditIdHorariosDisponiblesD);
        EditFechaDetalle = findViewById(R.id.EditFechaDetalle);
    }

    public Date formato(String fecha){
        Date fechaFormateada = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            fechaFormateada= format.parse(fecha);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaFormateada;
    }

    public void consultarDetallePeriodosR(View v){

    }
    public void limpiar(View v){
        EditIdPeriodoReservaD.setText("");
        EditIdHorariosDisponiblesD.setText("");
        EditFechaDetalle.setText("");
    }
}