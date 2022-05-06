package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalConsultarActivity;

public class HoariosLocalesActualizarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    MaterialAutoCompleteTextView SHoras, SLocales, SDisponibilidad;
    List<HorariosDisponibles> arrayHoras=new ArrayList<HorariosDisponibles>();
    List<String> arrayHorasString=new ArrayList<String>();
    List<Local> arrayLocales=new ArrayList<Local>();
    List<String> arrayLocalesString=new ArrayList<String>();
    List<String> values =new ArrayList<>();
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_locales_actualizar);
        helper = new ControlBDG10William(this);
        SHoras = findViewById(R.id.list_id_horario);
        SLocales = findViewById(R.id.list_locales);
        SDisponibilidad = findViewById(R.id.list_disponibiliad);
        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
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

    public void actualizarHorariosLocales(View v){
        hideKeyboard(v);
        String idHorario = SHoras.getText().toString();
        String idLocal = SLocales.getText().toString();
        String disponibilidad = SDisponibilidad.getText().toString();
        String insertarRegistros;
        if(idHorario.isEmpty()||idLocal.isEmpty()||disponibilidad.isEmpty()){
            Toast.makeText(HoariosLocalesActualizarActivity.this, "Debe completar los campos para actualizar un horario!", Toast.LENGTH_SHORT).show();
        }else{

            HorariosLocales horariolocal = new HorariosLocales();
            int posidHorarioSeleccionado= arrayHorasString.indexOf((idHorario));
            String idHorarioSeleccionado= arrayHoras.get(posidHorarioSeleccionado).getIdHorario();
            int posidLocalSeleccionado= arrayLocalesString.indexOf((idLocal));
            String idLocalSeleccionado= arrayLocales.get(posidLocalSeleccionado).getIdLocal();
            horariolocal.setIdHorario(idHorarioSeleccionado);
            horariolocal.setIdLocal(idLocalSeleccionado);
            if(disponibilidad.equals("Disponible")){
                horariolocal.setDisponibilidad(0);
            }else{
                horariolocal.setDisponibilidad(1);
            }
            helper.open();
            insertarRegistros = helper.actualizarHorariosLocales(horariolocal);
            helper.close();
            Toast.makeText(HoariosLocalesActualizarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();
            //Limpiamos los campos
            SHoras.setText("");
            SLocales.setText("");
            SDisponibilidad.setText("");

        }
    }
    public void limpiar(View v){
        hideKeyboard(v);
        SHoras.setText("");
        SLocales.setText("");
        SDisponibilidad.setText("");
    }
    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(HoariosLocalesActualizarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}