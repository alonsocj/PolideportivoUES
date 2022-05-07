package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class LoginActivity extends AppCompatActivity implements Comunicacion{

    TextInputEditText txtUsuario, txtContrasena;
    Button btnIngresar;
    ProgressBar progressBar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = findViewById(R.id.editText_user);
        txtContrasena = findViewById(R.id.editText_clave);
        btnIngresar = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProcesarDatos(LoginActivity.this).execute(txtUsuario.getText().toString(), txtContrasena.getText().toString(), 3000);
            }
        });
    }

    @Override
    public void toggleProgressBar(boolean status) {
        if (status){
            progressBar.setVisibility(View.VISIBLE);
            btnIngresar.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            btnIngresar.setVisibility(View.VISIBLE);
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
}