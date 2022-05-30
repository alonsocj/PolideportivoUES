package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.CobroInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LoginActivity extends AppCompatActivity implements Comunicacion {

    TextInputEditText txtUsuario, txtContrasena;
    TextInputLayout tilUsuario, tilContrasena;
    Button btnIngresar;
    LinearLayout linearLayout;
    TextView registrar;
    ProgressBar progressBar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = openOrCreateDatabase("polideportivo.s3db", MODE_PRIVATE, null);
        txtUsuario = findViewById(R.id.editText_user);
        txtContrasena = findViewById(R.id.editText_clave);
        btnIngresar = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        //registrar = findViewById(R.id.Registrar);
        tilUsuario = findViewById(R.id.input_user_layout);
        tilContrasena = findViewById(R.id.input_clave);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                Cursor cursor = db.rawQuery("SELECT * FROM usuario", null);
                cursor.moveToFirst();
                Cursor accesoUsuario = db.rawQuery("SELECT * FROM accesoUsuario", null);
                accesoUsuario.moveToFirst();
                new ProcesarDatos(LoginActivity.this).execute(txtUsuario.getText().toString(), txtContrasena.getText().toString(), 3000, cursor,accesoUsuario);
            }
        });
       /* registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void toggleProgressBar(boolean status) {
        if (status) {
            progressBar.setVisibility(View.VISIBLE);
            btnIngresar.setVisibility(View.GONE);
            tilUsuario.setEnabled(false);
            tilContrasena.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            btnIngresar.setVisibility(View.VISIBLE);
            tilUsuario.setEnabled(true);
            tilContrasena.setEnabled(true);
        }
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad) {
        Intent intent = new Intent(this, tipoActividad);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(CobroInsertarActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}