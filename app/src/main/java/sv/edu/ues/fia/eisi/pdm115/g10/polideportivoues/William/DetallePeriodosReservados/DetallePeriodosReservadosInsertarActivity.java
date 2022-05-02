package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
public class DetallePeriodosReservadosInsertarActivity extends AppCompatActivity {
    EditText EditIdPeriodoReservaD;
    EditText EditIdHorariosDisponiblesD;
    EditText EditFechaDetalle;
    DatePickerDialog.OnDateSetListener setListener;
    ControlBDG10 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_periodos_reservados_insertar);
        helper = new ControlBDG10(this);
        EditIdPeriodoReservaD = findViewById(R.id.EditIdPeriodoReservaD);
        EditIdHorariosDisponiblesD = findViewById(R.id.EditIdHorariosDisponiblesD);
        EditFechaDetalle = findViewById(R.id.EditFechaDetalle);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        EditFechaDetalle.setText(date);
        EditFechaDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(DetallePeriodosReservadosInsertarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        EditFechaDetalle.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
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

    public void insertarDetallePeriodosR(View v){

    }
    public void limpiar(View v){
        EditIdPeriodoReservaD.setText("");
        EditIdHorariosDisponiblesD.setText("");
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        EditFechaDetalle.setText(date);
    }
}