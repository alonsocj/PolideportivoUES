package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

public class HorariosLocalesInsertarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    Spinner SHoras, SLocales, SDisponibilidad;
    List<HorariosDisponibles> arrayHoras=new ArrayList<HorariosDisponibles>();
    List<String> arrayHorasString=new ArrayList<String>();
    List<Local> arrayLocales=new ArrayList<Local>();
    List<String> arrayLocalesString=new ArrayList<String>();
    List<String> values =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_locales_insertar);
        helper = new ControlBDG10William(this);
        SHoras = findViewById(R.id.SpinnerHoras);
        SLocales = findViewById(R.id.SpinnerLocales);
        SDisponibilidad = findViewById(R.id.SpinnerDisponibilidad);

        helper.open();
        arrayHoras=helper.consultarHorarioDisponibles();
        arrayHorasString=helper.consultarHorarioDisponiblesString(arrayHoras);
        SHoras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayHorasString));
        helper.open();
        arrayLocales=helper.consultarLocales();
        arrayLocalesString=helper.consultarLocalesString(arrayLocales);
        SLocales.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayLocalesString));
        values.add(getString(R.string.disponible));
        values.add(getString(R.string.ocupado));
        SDisponibilidad.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values));
    }



    public void insertarHorariosLocales(View v){

    }
    public void limpiar(View v){
        SHoras.setSelection(0);
        SLocales.setSelection(0);
        SDisponibilidad.setSelection(0);
    }
}