package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;

public class PeriodoReservaConsultarActivity extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener setListener;
    ControlBDCarolina helper;
    EditText editPR, editFI, editFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo_reserva_consultar);

        helper = new ControlBDCarolina(this);
        editPR = (EditText) findViewById(R.id.idPeriodoReserva);
        editFI = (EditText) findViewById(R.id.et_FechaI);
        editFF = (EditText) findViewById(R.id.et_FechaF);
    }
    public void consultarPeriodoReserva(View v) {
        helper.open();
        PeriodoReserva periodoReserva =helper.consultarPeriodoReserva(editPR.getText().toString());
        helper.close();
        if (editPR.getText().toString().isEmpty()) {
            Toast.makeText(PeriodoReservaConsultarActivity.this, "Ingrese un id del periodo de reserva para hacer la consulta ", Toast.LENGTH_LONG).show();
        }else{
            if(periodoReserva == null)
                Toast.makeText(PeriodoReservaConsultarActivity.this, "Periodo de reserva con id " + editPR.getText().toString() +" no encontrado", Toast.LENGTH_LONG).show();
            else{
                editFI.setText(periodoReserva.getFechaInicio());
                editFF.setText(periodoReserva.getFechaFin());
            }
        }


    }
    public void limpiar(View v){
        editPR.setText("");
        editFI.setText("");
        editFF.setText("");
    }
}
