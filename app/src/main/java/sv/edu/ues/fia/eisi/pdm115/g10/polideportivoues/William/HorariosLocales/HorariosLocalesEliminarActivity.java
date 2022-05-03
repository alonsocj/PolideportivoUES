package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacionEliminarActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class HorariosLocalesEliminarActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_horarios_locales_eliminar);
        helper = new ControlBDG10William(this);
        SHoras = findViewById(R.id.list_id_horario);
        SLocales = findViewById(R.id.list_locales);
        helper.open();
        arrayHoras=helper.consultarHorarioDisponibles();
        arrayHorasString=helper.consultarHorarioDisponiblesString(arrayHoras);
        SHoras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayHorasString));
        helper.open();
        arrayLocales=helper.consultarLocales();
        arrayLocalesString=helper.consultarLocalesString(arrayLocales);
        SLocales.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayLocalesString));

    }
    public void eliminarHorariosLocales(View v){
        String idHorario = SHoras.getText().toString();
        String idLocal = SLocales.getText().toString();
        AlertDialog.Builder confirmacion=new AlertDialog.Builder(HorariosLocalesEliminarActivity.this);
        String registrosEliminados;
        HorariosLocales horarioEliminado = new HorariosLocales();
        if(idHorario.isEmpty()||idLocal.isEmpty()){
            Toast.makeText(HorariosLocalesEliminarActivity.this, "Debe completar los campos para consultar el horario!", Toast.LENGTH_SHORT).show();
        }else {
            int posidHorarioSeleccionado = arrayHorasString.indexOf((idHorario));
            String idHorarioSeleccionado = arrayHoras.get(posidHorarioSeleccionado).getIdHorario();
            int posidLocalSeleccionado = arrayLocalesString.indexOf((idLocal));
            String idLocalSeleccionado = arrayLocales.get(posidLocalSeleccionado).getIdLocal();
            horarioEliminado.setIdHorario(idHorarioSeleccionado);
            horarioEliminado.setIdLocal(idLocalSeleccionado);
            helper.open();
            registrosEliminados = helper.eliminarHorariosLocales(horarioEliminado,0);
            helper.close();
            if(registrosEliminados.equals("1")){
                confirmacion.setMessage("El  horario del local no puede ser eliminado porque existen registros de reservacion relacionados con este local. ¿Desea eliminarlos también?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                helper.open();
                                String registros = helper.eliminarHorariosLocales(horarioEliminado, 1);
                                helper.close();
                                Toast.makeText(HorariosLocalesEliminarActivity.this, registros, Toast.LENGTH_SHORT).show();
                                SHoras.setText("");
                                SLocales.setText("");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(HorariosLocalesEliminarActivity.this, "No se eliminaron los registros", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }else{
                Toast.makeText(HorariosLocalesEliminarActivity.this, registrosEliminados, Toast.LENGTH_SHORT).show();
            }
            SHoras.setText("");
            SLocales.setText("");
        }
    }
    public void limpiar(View v){
        SHoras.setText("");
        SLocales.setText("");
    }
}