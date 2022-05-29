package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarReservacion;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.Reservacion;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarCobro.CobroService;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

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
    ControlBDCarolina db;

    private final String urlService = "https://grupo10pdm2022.000webhostapp.com/ws_reservacion_query.php";

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
        db = new ControlBDCarolina(this);
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
        if((etFechaR.getText().toString()).equals("")){
            Toast.makeText(this, "Debe ingresar una fecha!", Toast.LENGTH_LONG).show();
        }else {
            String[] fecha = etFechaR.getText().toString().split("/");
            String url = "";
            url = urlService + "?year=" + fecha[2] + "&month=" + fecha[1] + "&day=" + fecha[0];
            String reservacionExternos = ReservacionService.obtenerRespuestaPeticion(url, this);
            try {
                listaReservacion.addAll(ReservacionService.obtenerReservacionExterno(reservacionExternos, this));
                actualizarListView();
                Toast.makeText(this, "Registros encontrados: " + listaReservacion.size() + "", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void limpiar(View v){
        etFechaR.setText("");
        listaReservacion.removeAll(listaReservacion);
        actualizarListView();
    }
    public void guardar(View v) {
        String mensaje = "";
        if((etFechaR.getText().toString()).equals("")){
            Toast.makeText(this, "Debe ingresar una fecha!", Toast.LENGTH_LONG).show();
        }else{
            db.open();
            for(int i=0; i < listaReservacion.size();i++){
                mensaje = db.insertarReservacion(listaReservacion.get(i));
            }
            db.close();
            if(mensaje == ""){
                Toast.makeText(this, "No se encontro ningun registro para su insercion", Toast.LENGTH_LONG).show();
            }else{
                if(mensaje == "Registro duplicado!"){
                    Toast.makeText(this, "Error al realizar insercion, puede que existan registros duplicados o registros asociados a la reservacion que no existan en la base de datos", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show();
                }
            }
            listaReservacion.removeAll(listaReservacion);
            actualizarListView();
            etFechaR.setText("");
        }
    }

    private void actualizarListView() {
        String dato = "";
        nombreReservacion.clear();
        for (int i = 0; i < listaReservacion.size(); i++) {
            dato = "Id: "+listaReservacion.get(i).getIdReservacion() + ", Persona: " + listaReservacion.get(i).getIdPersona()+", Fecha de registro: "+listaReservacion.get(i).getFechaRegistro();
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