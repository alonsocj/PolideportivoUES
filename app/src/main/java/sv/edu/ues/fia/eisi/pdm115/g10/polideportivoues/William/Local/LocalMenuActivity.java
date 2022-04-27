package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LocalMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String textos[]={"Agregar Local","Actualizar Local","Consultar Local","Eliminar Local"};
    String values[]={"LocalInsertarActivity","LocalActualizarActivity","LocalConsultarActivity","LocalEliminarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_menu);
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,textos);
        ListView listVi =(ListView)findViewById(R.id.listv);
        listVi.setAdapter(adaptador);
        listVi.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String nombreValue=values[position];
        try{ Class<?> clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace(); }
    }
}