package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PeriodoReservaActualizarActivity extends AppCompatActivity {
    EditText et_FechaI;
    EditText et_FechaF;
    DatePickerDialog.OnDateSetListener setListener;
    ControlBDCarolina helper;
    EditText editPR, editFI, editFF;
    Button botonActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo_reserva_actualizar);
        helper = new ControlBDCarolina(this);

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
                DatePickerDialog datePickerDialog=new DatePickerDialog(PeriodoReservaActualizarActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog=new DatePickerDialog(PeriodoReservaActualizarActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        editPR = (EditText) findViewById(R.id.idPeriodoReserva);
        editFI = (EditText) findViewById(R.id.et_FechaI);
        editFF = (EditText) findViewById(R.id.et_FechaF);
        botonActualizar = (Button) findViewById(R.id.botonActualizarPeriodoReserva);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idperiodoReserva=editPR.getText().toString();
                String fechaI=editFI.getText().toString();
                String fechaF=editFF.getText().toString();

                //Convertir fecha
                Date fechaIni=formato(fechaI);
                Date fechaFin=formato(fechaF);

                boolean verdadero=true;
                String regInsertados;

                if(idperiodoReserva.isEmpty()||fechaI.isEmpty()||fechaF.isEmpty()){
                    Toast.makeText(PeriodoReservaActualizarActivity.this, "Debe completar los campos para actualizar un periodo de reserva", Toast.LENGTH_SHORT).show();
                }else {
                    if(idperiodoReserva.length()!=6){
                        Toast.makeText(PeriodoReservaActualizarActivity.this, "El id debe contener 6 dígitos", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else if(fechaIni.compareTo(fechaFin)>0){
                        Toast.makeText(PeriodoReservaActualizarActivity.this, "La fecha de inicio debe ser anterior a la fecha fin del periodo de reserva", Toast.LENGTH_SHORT).show();
                        verdadero=false;
                    }else{
                        PeriodoReserva periodoReserva=new PeriodoReserva();
                        periodoReserva.setIdPeriodoReserva(idperiodoReserva);
                        periodoReserva.setFechaInicio(fechaI);
                        periodoReserva.setFechaFin(fechaF);

                        helper.open();
                        regInsertados=helper.actualizarPeriodoReserva(periodoReserva);
                        helper.close();
                        Toast.makeText(PeriodoReservaActualizarActivity.this, regInsertados, Toast.LENGTH_SHORT).show();

                        //Limpiamos los campos
                        editPR.setText("");
                        editFI.setText("");
                        editFF.setText("");
                    }

                }

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
    public void limpiar(View v) {
        editPR.setText("");
        editFI.setText("");
        editFF.setText("");
    }
}
