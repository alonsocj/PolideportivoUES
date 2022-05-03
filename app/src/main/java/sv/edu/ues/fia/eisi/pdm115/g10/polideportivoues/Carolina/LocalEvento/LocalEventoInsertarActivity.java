package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.LocalEvento;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalEventoInsertarActivity extends AppCompatActivity {
    ControlBDCarolina helper;
    EditText editIdLE,editCA;
    Spinner spE,spL;
    List<Evento> arrayEventos=new ArrayList<Evento>();
    List<String> arrayEventosString=new ArrayList<String>();
    List<Local> arrayLocales=new ArrayList<Local>();
    List<String> arrayLocalesString=new ArrayList<String>();
    Button botonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_evento_insertar);
        helper = new ControlBDCarolina(this);

        editIdLE=(EditText) findViewById(R.id.idLocalEvento);
        spE=(Spinner) findViewById(R.id.idEvento);
        spL=(Spinner) findViewById(R.id.idLocal);
        editCA=(EditText) findViewById(R.id.cantAutorizada);
        botonAgregar = (Button) findViewById(R.id.botonAgregarLocalEvento);

        //Llenado del spinner de eventos
        helper.open();
        arrayEventos=helper.consultarEventos();
        arrayEventosString=helper.consultarEventosString(arrayEventos);
        spE.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayEventosString));
        helper.close();

        //Llenado del spinner de locales
        helper.open();
        arrayLocales=helper.consultarLocales();
        arrayLocalesString=helper.consultarLocalesString(arrayLocales);
        spL.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayLocalesString));
        helper.close();

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idLocalE = editIdLE.getText().toString();
                String evento = spE.getSelectedItem().toString();
                String local = spL.getSelectedItem().toString();
                Integer cantAutorizada = entero(editCA.getText().toString());
                String insertarRegistros;

                if(idLocalE.isEmpty()||evento.equals("Seleccione un evento")|| local.equals("Seleccione un evento") ||cantAutorizada.toString().isEmpty()){
                    Toast.makeText(LocalEventoInsertarActivity.this, "Debe completar los campos para hacer el registro de local evento", Toast.LENGTH_SHORT).show();
                }else if(cantAutorizada<=0){
                    Toast.makeText(LocalEventoInsertarActivity.this, "La cantidad autorizada debe ser mayor a cero", Toast.LENGTH_SHORT).show();
                }else{
                    String idEventoSeleccionado=arrayEventos.get(spE.getSelectedItemPosition()-1).getIdEvento();
                    String idLocalSeleccionado=arrayLocales.get(spL.getSelectedItemPosition()-1).getIdLocal();

                    LocalEvento localEvento = new LocalEvento();
                    localEvento.setIdEvento(idLocalE);
                    localEvento.setIdEvento(idEventoSeleccionado);
                    localEvento.setIdLocal(idLocalSeleccionado);
                    localEvento.setCantAutorizada(cantAutorizada);

                    helper.open();
                    insertarRegistros = helper.insertarLocalEvento(localEvento);
                    helper.close();
                    Toast.makeText(LocalEventoInsertarActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                    editIdLE.setText("");
                    spE.setSelection(0);
                    spL.setSelection(0);
                    editCA.setText("");
                }
            }
        });

    }
    public Integer entero(String dato){
        Integer numero = numero= Integer.parseInt(dato);
        return numero;
    }
    public void limpiar(View v) {
        editIdLE.setText("");
        spE.setSelection(0);
        spL.setSelection(0);
        editCA.setText("");
    }*/
        super.onCreate(savedInstanceState);
    }
}