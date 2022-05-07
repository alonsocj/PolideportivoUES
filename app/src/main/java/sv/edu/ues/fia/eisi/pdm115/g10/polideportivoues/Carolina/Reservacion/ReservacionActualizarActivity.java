package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

public class ReservacionActualizarActivity extends AppCompatActivity {
    TextInputEditText editIdReservacion, etFechaR;
    ControlBDCarolina helper;
    MaterialAutoCompleteTextView spCobro,spPersona, spTReservacion,spEvento,spPeriodoR, spHLocal;
    List<Cobro> arrayCobros=new ArrayList<Cobro>();
    List<Persona> arrayPersonas=new ArrayList<Persona>();
    List<TipoReservacion> arrayTipoReservaciones=new ArrayList<TipoReservacion>();
    List<Evento> arrayEventos=new ArrayList<Evento>();
    List<HorariosLocales> arrayHorariosLocales=new ArrayList<HorariosLocales>();
    List<PeriodoReserva> arrayPeriodoReserva=new ArrayList<PeriodoReserva>();
    List<String> arrayCobrosString=new ArrayList<String>();
    List<String> arrayPersonasString=new ArrayList<String>();
    List<String> arrayTipoReservacionesString=new ArrayList<String>();
    List<String> arrayEventosString=new ArrayList<String>();
    List<String> arrayHorariosLocalesString=new ArrayList<String>();
    List<String> arrayPeriodoReservaString=new ArrayList<String>();

    Button botonActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_actualizar);
        helper = new ControlBDCarolina(this);

        editIdReservacion=(TextInputEditText) findViewById(R.id.idReservacion);
        spCobro=(MaterialAutoCompleteTextView) findViewById(R.id.idCobro);
        spPersona=(MaterialAutoCompleteTextView) findViewById(R.id.idPersona);
        spTReservacion=(MaterialAutoCompleteTextView) findViewById(R.id.editText_idTipoReservacion);
        spEvento=(MaterialAutoCompleteTextView) findViewById(R.id.idEvento);
        spPeriodoR=(MaterialAutoCompleteTextView) findViewById(R.id.idPeriodoReserva);
        spHLocal=(MaterialAutoCompleteTextView) findViewById(R.id.idHorarioLocal);
        etFechaR=findViewById(R.id.et_FechaR);

        botonActualizar = (Button) findViewById(R.id.botonActualizarReservacion);

        //Llenado del spinner de cobros
        helper.open();
        arrayCobros=helper.consultarCobros();
        arrayCobrosString=helper.consultarCobrosString(arrayCobros);
        spCobro.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayCobrosString));
        helper.close();

        //Llenado del spinner de personas
        helper.open();
        arrayPersonas=helper.consultarPersonas();
        arrayPersonasString=helper.consultarPersonasString(arrayPersonas);
        spPersona.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayPersonasString));
        helper.close();

        //Llenado del spinner de tipo de reservacion
        helper.open();
        arrayTipoReservaciones=helper.consultarTipoReservacion();
        arrayTipoReservacionesString=helper.consultarTipoReservacionString(arrayTipoReservaciones);
        spTReservacion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayTipoReservacionesString));
        helper.close();

        //Llenado del spinner de eventos
        helper.open();
        arrayEventos=helper.consultarEventos();
        arrayEventosString=helper.consultarEventosString(arrayEventos);
        spEvento.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayEventosString));
        helper.close();

        //Llenado del spinner de periodo reservas
        helper.open();
        arrayPeriodoReserva=helper.consultarPeriodoReservacion();
        arrayPeriodoReservaString=helper.consultarPeriodoReservacionString(arrayPeriodoReserva);
        spPeriodoR.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayPeriodoReservaString));
        helper.close();

        //Llenado del spinner de horarios y locales
        helper.open();
        arrayHorariosLocales=helper.consultarHorariosLocales();
        arrayHorariosLocalesString=helper.consultarHorariosLocalesString(arrayHorariosLocales);
        spHLocal.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayHorariosLocalesString));
        helper.close();

        //Mostrar Calendario
        etFechaR=findViewById(R.id.et_FechaR);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        etFechaR.setText(date);

        etFechaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ReservacionActualizarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        etFechaR.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        }); //Fin calendario
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idReservacion=editIdReservacion.getText().toString();
                String fechaRegistro=etFechaR.getText().toString();
                String spcobro=spCobro.getText().toString();
                String sppersona=spPersona.getText().toString();
                String sptipoReservacion=spTReservacion.getText().toString();
                String spevento=spEvento.getText().toString();
                String spperiodoReservacion=spPeriodoR.getText().toString();
                String sphorarioLocal=spHLocal.getText().toString();
                String actualizarRegistros;


                if(idReservacion.isEmpty() || fechaRegistro.isEmpty() || spcobro.isEmpty() || sppersona.isEmpty() || sptipoReservacion.isEmpty() ||  spevento.isEmpty() ||  spperiodoReservacion.isEmpty() || sphorarioLocal.isEmpty()){
                    Toast.makeText(ReservacionActualizarActivity.this, "Debe completar los campos para actualizar una reservación!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idReservacion.length()!=6){
                        Toast.makeText(ReservacionActualizarActivity.this, "El id de reservacion debe de ser de 6 carácteres!", Toast.LENGTH_SHORT).show();
                    }else{

                        Integer idCobroSeleccionada=arrayCobros.get(arrayCobrosString.indexOf(spcobro)).getIdCobro();
                        String idPersonaSeleccionada=arrayPersonas.get(arrayPersonasString.indexOf(sppersona)).getIdPersona();
                        String idTipoReservacionSeleccionada=arrayTipoReservaciones.get(arrayTipoReservacionesString.indexOf(sptipoReservacion)).getIdTipoR();
                        String idEventoSeleccionada=arrayEventos.get(arrayEventosString.indexOf(spevento)).getIdEvento();
                        String idPeriodoReservaSeleccionada=arrayPeriodoReserva.get(arrayPeriodoReservaString.indexOf(spperiodoReservacion)).getIdPeriodoReserva();
                        String idHorariosSeleccionada=arrayHorariosLocales.get(arrayHorariosLocalesString.indexOf(sphorarioLocal)).getIdHorario();
                        String idLocalesSeleccionada=arrayHorariosLocales.get(arrayHorariosLocalesString.indexOf(sphorarioLocal)).getIdLocal();


                        Reservacion reservacion = new Reservacion();
                        reservacion.setIdReservacion(idReservacion);
                        reservacion.setIdCobro(idCobroSeleccionada);
                        reservacion.setIdPersona(idPersonaSeleccionada);
                        reservacion.setIdTipoR(idTipoReservacionSeleccionada);
                        reservacion.setIdEvento(idEventoSeleccionada);
                        reservacion.setIdPeriodoReserva(idPeriodoReservaSeleccionada);
                        reservacion.setIdHorario(idHorariosSeleccionada);
                        reservacion.setIdLocal(idLocalesSeleccionada);
                        reservacion.setFechaRegistro(fechaRegistro);

                        helper.open();
                        actualizarRegistros = helper.actualizarReservacion(reservacion);
                        helper.close();
                        Toast.makeText(ReservacionActualizarActivity.this, actualizarRegistros, Toast.LENGTH_SHORT).show();

                        //Limpiamos los campos
                        editIdReservacion.setText("");
                        etFechaR.setText("");
                        spCobro.setText("");
                        spPersona.setText("");
                        spTReservacion.setText("");
                        spEvento.setText("");
                        spPeriodoR.setText("");
                        spHLocal.setText("");

                    }
                }
            }
        });
    }
    public void limpiar(View v) {

        editIdReservacion.setText("");
        etFechaR.setText("");
        spCobro.setText("");
        spPersona.setText("");
        spTReservacion.setText("");
        spEvento.setText("");
        spPeriodoR.setText("");
        spHLocal.setText("");
    }
}