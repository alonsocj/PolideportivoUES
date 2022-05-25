package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarCobro;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConsultarCobroExternoActivity extends AppCompatActivity {
    TextInputEditText idCobro;
    static List<Cobro> listaCobros;
    static List<String> nombreCobros;
    ListView listViewCobros;

    private final String urlService = "http://192.168.0.21/WSPolideportivoUES/ws_cobro_query.php";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cobro_externo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listaCobros = new ArrayList<Cobro>();
        nombreCobros = new ArrayList<String>();
        idCobro = findViewById(R.id.editText_id_cobro);
        listViewCobros = findViewById(R.id.listViewCobros);
    }

    public void servicioPHP(View v) {
        String id = idCobro.getText().toString();
        String url = "";
        url = urlService + "?idcobro=" + id;
        String cobrosExternos = CobroService.obtenerRespuestaPeticion(url, this);
        try {
            listaCobros.addAll(CobroService.obtenerCobrosExterno(cobrosExternos, this));
            actualizarListView();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void limpiar(){
        idCobro.setText("");
        //listaCobros.removeAll(listaCobros);
        //actualizarListView();
    }
    private void actualizarListView() {
        String dato = "";
        nombreCobros.clear();
        for (int i = 0; i < listaCobros.size(); i++) {
            dato = listaCobros.get(i).getIdCobro() + "    " + listaCobros.get(i).getIdPago();
            nombreCobros.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCobros);
        listViewCobros.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Cobro> conjuntoCobro = new HashSet<Cobro>();
        conjuntoCobro.addAll(listaCobros);
        listaCobros.clear();
        listaCobros.addAll(conjuntoCobro);
        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreCobros);
        nombreCobros.clear();
        nombreCobros.addAll(conjuntoNombre);
    }
}