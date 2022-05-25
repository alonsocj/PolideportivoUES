package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarReservacion;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.Reservacion;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarCobro.CobroService;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class ConsultarReservacionExternoActivity extends AppCompatActivity {
    TextInputEditText etFechaR;
    static List<Reservacion> listaReservacion;
    static List<String> nombreReservacion;
    ListView listViewReservacion;

    private final String urlService = "http://192.168.0.21/WSPolideportivoUES/ws_reservacion_query.php";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reservacion_externo);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        etFechaR=findViewById(R.id.et_FechaR);
        listaReservacion = new ArrayList<Reservacion>();
        nombreReservacion = new ArrayList<String>();
        listViewReservacion= findViewById(R.id.listViewReservacion);

        //Mostrar Calendario
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        etFechaR.setText(date);

        etFechaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ConsultarReservacionExternoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        etFechaR.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }
    public void servicioPHP(View v) {
        String[] fecha = etFechaR.getText().toString().split("/");
        String url = "";
        url = urlService + "?year=" + fecha[2] + "&month="+ fecha[1] + "&day=" + fecha[0];
        String reservacionExternos = ReservacionService.obtenerRespuestaPeticion(url, this);
        try {
            listaReservacion.addAll(ReservacionService.obtenerReservacionExterno(reservacionExternos, this));
            actualizarListView();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreReservacion.clear();
        for (int i = 0; i < listaReservacion.size(); i++) {
            dato = listaReservacion.get(i).getIdReservacion() + "    " + listaReservacion.get(i).getIdPersona();
            nombreReservacion.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreReservacion);
        listViewReservacion.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Reservacion> conjuntoReservacion = new HashSet<Reservacion>();
        conjuntoReservacion.addAll(listaReservacion);
        listaReservacion.clear();
        listaReservacion.addAll(conjuntoReservacion);
        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreReservacion);
        nombreReservacion.clear();
        nombreReservacion.addAll(conjuntoNombre);
    }

}