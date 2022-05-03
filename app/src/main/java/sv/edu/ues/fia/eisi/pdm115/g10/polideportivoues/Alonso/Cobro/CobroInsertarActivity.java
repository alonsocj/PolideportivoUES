package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class CobroInsertarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    //TextInputLayout inputIdCobro, inputIdPago, inputDuracion, inputPrecio;
    TextInputEditText editIdCobro, editCantPersonas, editDuracion, editPrecio;
    MaterialAutoCompleteTextView editTipoPago;
    Button btnInsertar;
    SQLiteDatabase db;
    String[] arridTipoPago;
    String[] arrtipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro_insertar);
        helper = new ControlBDG10Alonso(this);
        editIdCobro = findViewById(R.id.editText_id_cobro);
        editCantPersonas = findViewById(R.id.editText_cantPersonas);
        editDuracion = findViewById(R.id.editText_duracion);
        editPrecio = findViewById(R.id.editText_precio);
        editTipoPago = findViewById(R.id.list_tipo_pago);
        btnInsertar = findViewById(R.id.button_guardar);

        // obtener datos de la lista de tipo pago
        db = openOrCreateDatabase("polideportivo.s3db",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("SELECT * FROM tipopago",null);
        arridTipoPago = new String[cursor.getCount()];
        arrtipoPago = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount(); i++){
            arridTipoPago[i] = cursor.getString(0);
            arrtipoPago[i] = cursor.getString(1);
            cursor.moveToNext();
        }
        //adapter lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrtipoPago);
        editTipoPago.setAdapter(adapter);

        btnInsertar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String eventosRegistrados;
                int idCobro = Integer.parseInt(editIdCobro.getText().toString());
                String tipoPago = editTipoPago.getText().toString();
                String idTipoPago = "";
                int cantPersonas = Integer.parseInt(editCantPersonas.getText().toString());
                float duracion = Float.parseFloat(editDuracion.getText().toString());
                float precio = Float.parseFloat(editPrecio.getText().toString());
                for(int i=0; i<arrtipoPago.length; i++){
                    if(tipoPago.equals(arrtipoPago[i])){
                        idTipoPago = arridTipoPago[i];
                    }
                }
                Cobro cobro = new Cobro();
                cobro.setIdCobro(idCobro);
                cobro.setIdPago(idTipoPago);
                cobro.setCantPersonas(cantPersonas);
                cobro.setDuracion(duracion);
                cobro.setPrecio(precio);
                helper.open();
                eventosRegistrados = helper.insertar(cobro);
                helper.close();
                Toast.makeText(getApplicationContext(), eventosRegistrados, Toast.LENGTH_SHORT).show();

            }
        });
    }
}