package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HorariosLocalesConsultarActivity extends AppCompatActivity {
    ControlBDG10William helper;
    MaterialAutoCompleteTextView SHoras, SLocales;
    TextInputEditText SDisponibilidad;
    List<HorariosDisponibles> arrayHoras=new ArrayList<HorariosDisponibles>();
    List<String> arrayHorasString=new ArrayList<String>();
    List<Local> arrayLocales=new ArrayList<Local>();
    List<String> arrayLocalesString=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_locales_consultar);
        helper = new ControlBDG10William(this);
        SHoras = findViewById(R.id.list_id_horario);
        SLocales = findViewById(R.id.list_locales);
        SDisponibilidad = findViewById(R.id.list_disponibiliad);

        helper.open();
        arrayHoras=helper.consultarHorarioDisponibles();
        arrayHorasString=helper.consultarHorarioDisponiblesString(arrayHoras);
        SHoras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayHorasString));
        helper.open();
        arrayLocales=helper.consultarLocales();
        arrayLocalesString=helper.consultarLocalesString(arrayLocales);
        SLocales.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayLocalesString));
    }



    public void consultarHorariosLocales(View v){
        String idHorario = SHoras.getText().toString();
        String idLocal = SLocales.getText().toString();
        HorariosLocales horarioConsultado;
        if(idHorario.isEmpty()||idLocal.isEmpty()){
            Toast.makeText(HorariosLocalesConsultarActivity.this, "Debe completar los campos para consultar el horario!", Toast.LENGTH_SHORT).show();
        }else {
            int posidHorarioSeleccionado = arrayHorasString.indexOf((idHorario));
            String idHorarioSeleccionado = arrayHoras.get(posidHorarioSeleccionado).getIdHorario();
            int posidLocalSeleccionado = arrayLocalesString.indexOf((idLocal));
            String idLocalSeleccionado = arrayLocales.get(posidLocalSeleccionado).getIdLocal();
            helper.open();
            horarioConsultado = helper.consultarHorariosLocales(idHorarioSeleccionado,idLocalSeleccionado);
            if(horarioConsultado == null){
                Toast.makeText(HorariosLocalesConsultarActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                SHoras.setText("");
                SLocales.setText("");
            }else{
                if(horarioConsultado.getDisponibilidad() == 0 ){
                    SDisponibilidad.setText("Disponible");
                }else{
                    SDisponibilidad.setText("Ocupado");
                }
            }

        }

    }
    public void limpiar(View v){
        SHoras.setText("");
        SLocales.setText("");
        SDisponibilidad.setText("");
    }
}