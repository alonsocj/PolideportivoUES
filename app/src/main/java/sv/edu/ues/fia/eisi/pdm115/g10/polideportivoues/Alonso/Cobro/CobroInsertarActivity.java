package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10Alonso;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class CobroInsertarActivity extends AppCompatActivity {

    ControlBDG10Alonso helper;
    //TextInputLayout inputIdCobro, inputIdPago, inputDuracion, inputPrecio;
    TextInputEditText editIdCobro, editCantPersonas, editDuracion, editPrecio;
    MaterialAutoCompleteTextView editTipoPago;
    LinearLayout linearLayout;
    Button btnInsertar,limpiar;
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
        linearLayout = findViewById(R.id.linearLayout);
        limpiar = findViewById(R.id.button_limpiar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
        float[] duracionf= new float[1];
        editDuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(1)
                        .setMinute(0)
                        .setTitleText(R.string.time_picker_title)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                        .build();
                timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String duracionM;
                        float duracion=0.0f;

                        if (timePicker.getHour() < 10) {
                            if (timePicker.getMinute() < 10) {
                                duracionM = "0" + timePicker.getHour() + ":0" + timePicker.getMinute();
                                duracion = timePicker.getHour() + (timePicker.getMinute() *  0.0167f);
                                editDuracion.setText(duracionM);
                            } else {
                                duracionM = "0" + timePicker.getHour() + ":" + timePicker.getMinute();
                                duracion = timePicker.getHour() + (timePicker.getMinute() *  0.0167f);
                                editDuracion.setText(duracionM);
                            }
                        } else {
                            if (timePicker.getMinute() < 10) {
                                duracionM = timePicker.getHour() + ":0" + timePicker.getMinute();
                                duracion = timePicker.getHour() + (timePicker.getMinute() *  0.0167f);
                                editDuracion.setText(duracionM);
                            } else {
                                duracionM = timePicker.getHour() + ":" + timePicker.getMinute();
                                duracion = timePicker.getHour() + (timePicker.getMinute() *  0.0167f);
                                editDuracion.setText(duracionM);
                            }
                        }
                        duracionf[0]=duracion;
                    }
                });
            }
        });
        // obtener datos de la lista de tipo pago
        db = openOrCreateDatabase("polideportivo.s3db", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM tipopago", null);
        arridTipoPago = new String[cursor.getCount()];
        arrtipoPago = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            arridTipoPago[i] = cursor.getString(0);
            arrtipoPago[i] = cursor.getString(1);
            cursor.moveToNext();
        }
        //adapter lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrtipoPago);
        editTipoPago.setAdapter(adapter);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    hideKeyboard(v);

                    String eventosRegistrados;
                    int idCobro = Integer.parseInt(editIdCobro.getText().toString());
                    String tipoPago = editTipoPago.getText().toString();
                    String idTipoPago = "";
                    int cantPersonas = Integer.parseInt(editCantPersonas.getText().toString());
                    String duracionM = editDuracion.getText().toString();
                    float precio = 0;

                    for (int i = 0; i < arrtipoPago.length; i++) {
                        if (tipoPago.equals(arrtipoPago[i])) {
                            idTipoPago = arridTipoPago[i];
                        }
                    }
                    Cobro cobro = new Cobro();
                    cobro.setIdCobro(idCobro);
                    cobro.setIdPago(idTipoPago);
                    cobro.setCantPersonas(cantPersonas);
                    cobro.setDuracion(duracionf[0]);
                    cobro.setDuracionTexto(duracionM);
                    cobro.setPrecio(precio);
                    helper.open();
                    eventosRegistrados = helper.insertar(cobro);
                    helper.close();
                    db = openOrCreateDatabase("polideportivo.s3db", MODE_PRIVATE, null);
                    Cursor cursor = db.rawQuery("SELECT (precio) FROM cobro WHERE idCobro="+idCobro, null);
                    cursor.moveToFirst();
                    editPrecio.setText(cursor.getString(0));
                    Toast.makeText(getApplicationContext(), eventosRegistrados, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al insertar, debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarTexto(v);
            }
        });
    }

    public void limpiarTexto(View v) {
        editIdCobro.setText("");
        editTipoPago.setText("");
        editCantPersonas.setText("");
        editDuracion.setText("");
        editPrecio.setText("");
    }
    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(CobroInsertarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}