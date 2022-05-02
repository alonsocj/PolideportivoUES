package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class CobroInsertarActivity extends AppCompatActivity {

    ControlBDG10 helper;
    //TextInputLayout inputIdCobro, inputIdPago, inputDuracion, inputPrecio;
    TextInputEditText editIdCobro, editDuracion, editPrecio;
    MaterialAutoCompleteTextView editTipoPago;
    SQLiteDatabase db;
    String[] arridTipoPago;
    String[] arrtipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro_insertar);
        helper = new ControlBDG10(this);
        editIdCobro = findViewById(R.id.editText_id_cobro);
        editDuracion = findViewById(R.id.editText_duracion);
        editPrecio = findViewById(R.id.editText_precio);
        editTipoPago = findViewById(R.id.list_tipo_pago);

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
    }
}