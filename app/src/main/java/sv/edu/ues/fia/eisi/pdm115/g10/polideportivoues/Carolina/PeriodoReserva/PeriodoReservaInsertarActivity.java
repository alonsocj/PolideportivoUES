package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PeriodoReservaInsertarActivity extends AppCompatActivity {

    EditText et_FechaI;
    EditText et_FechaF;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo_reserva_insertar);

        //Mostrar Calendario

        et_FechaI=findViewById(R.id.et_FechaI);
        et_FechaF=findViewById(R.id.et_FechaF);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        et_FechaI.setText(date);
        et_FechaF.setText(date);

        et_FechaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(PeriodoReservaInsertarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        et_FechaI.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        et_FechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(PeriodoReservaInsertarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        et_FechaF.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        //Fin calendario
    }
}