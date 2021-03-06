package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarReservacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.UnsupportedEncodingException;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.Reservacion;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

import android.os.StrictMode;
import android.annotation.SuppressLint;
@SuppressLint("NewApi")

public class InsertarReservacionExternoActivity extends AppCompatActivity{

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

    Button botonAgregar;
    DatePickerDialog.OnDateSetListener setListener;

    private final String urlHosting = "https://grupo10pdm2022.000webhostapp.com/ws_insert_reservacion.php";
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_reservacion_externo);

        //WEBSERVICES
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        helper = new ControlBDCarolina(this);

        editIdReservacion=(TextInputEditText) findViewById(R.id.idReservacion);
        spCobro=(MaterialAutoCompleteTextView) findViewById(R.id.idCobro);
        spPersona=(MaterialAutoCompleteTextView) findViewById(R.id.idPersona);
        spTReservacion=(MaterialAutoCompleteTextView) findViewById(R.id.editText_idTipoReservacion);
        spEvento=(MaterialAutoCompleteTextView) findViewById(R.id.idEvento);
        spPeriodoR=(MaterialAutoCompleteTextView) findViewById(R.id.idPeriodoReserva);
        spHLocal=(MaterialAutoCompleteTextView) findViewById(R.id.idHorarioLocal);
        etFechaR=findViewById(R.id.et_FechaR);

        botonAgregar = (Button) findViewById(R.id.botonAgregarReservacion);

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
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        etFechaR.setText(date);

        etFechaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(InsertarReservacionExternoActivity.this, new DatePickerDialog.OnDateSetListener() {
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


        botonAgregar.setOnClickListener(new View.OnClickListener() {
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
                String insertarRegistros;

                //WEBSERVICES
                String url="";
                String urlidreserva="";
                String urlidpersona="";
                String urlidtiporeserva="";
                String urlidevento="";
                String urlidperiodo="";
                String urlidhorario="";
                String urlidlocal="";
                String urlfecha="";

                if(idReservacion.isEmpty() || fechaRegistro.isEmpty() || spcobro.isEmpty() || sppersona.isEmpty() || sptipoReservacion.isEmpty() ||  spevento.isEmpty() ||  spperiodoReservacion.isEmpty() || sphorarioLocal.isEmpty()){
                    Toast.makeText(InsertarReservacionExternoActivity.this, "Debe completar los campos para realizar una reservaci??n!", Toast.LENGTH_SHORT).show();
                }else{
                    if(idReservacion.length()!=6){
                        Toast.makeText(InsertarReservacionExternoActivity.this, "El id de reservacion debe de ser de 6 car??cteres!", Toast.LENGTH_SHORT).show();
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
                        int prueba1=helper.listarReservacionEventos(reservacion);
                        int prueba2=helper.listarReservaPerHoraLoca(reservacion);
                        helper.close();

                        if (prueba2==1){
                            Toast.makeText(InsertarReservacionExternoActivity.this, "No se puede hacer mas de una reserva para el mismo periodo, horario y local", Toast.LENGTH_SHORT).show();
                        }else{
                            if (prueba1==5){
                                Toast.makeText(InsertarReservacionExternoActivity.this, "Hay 5 locales asociados al evento. No puede reservar m??s de 5 locales para un mismo evento", Toast.LENGTH_SHORT).show();
                            }else{
                                helper.open();
                                insertarRegistros = helper.insertarReservacion(reservacion);
                                helper.close();

                                Toast.makeText(InsertarReservacionExternoActivity.this, insertarRegistros, Toast.LENGTH_SHORT).show();

                                if(insertarRegistros.equals("Registro duplicado!")){
                                    //Limpiamos los campos
                                    editIdReservacion.setText("");
                                    etFechaR.setText("");
                                    spCobro.setText("");
                                    spPersona.setText("");
                                    spTReservacion.setText("");
                                    spEvento.setText("");
                                    spPeriodoR.setText("");
                                    spHLocal.setText("");
                                }else{
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

                        try {
                            urlidreserva= URLEncoder.encode(idReservacion,"UTF-8");
                            urlidpersona= URLEncoder.encode(idPersonaSeleccionada,"UTF-8");
                            urlidtiporeserva= URLEncoder.encode(idTipoReservacionSeleccionada,"UTF-8");
                            urlidevento= URLEncoder.encode(idEventoSeleccionada,"UTF-8");
                            urlidperiodo= URLEncoder.encode(idPeriodoReservaSeleccionada,"UTF-8");
                            urlidhorario= URLEncoder.encode(idHorariosSeleccionada,"UTF-8");
                            urlidlocal= URLEncoder.encode(idLocalesSeleccionada,"UTF-8");
                            urlfecha= URLEncoder.encode(fechaRegistro,"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //WEBSERVICE
                        url = urlHosting+ "?IDRESERVACION=" + urlidreserva + "&IDCOBRO=" + reservacion.getIdCobro() + "&IDPERSONA=" + urlidpersona + "&IDTIPOR=" + urlidtiporeserva+"&IDEVENTO="+urlidevento+"&IDPERIODORESERVA=" + urlidperiodo+ "&IDHORARIO="+urlidhorario+"&IDLOCAL="+urlidlocal+"&FECHAREGISTRO="+urlfecha;
                        ReservacionService.insertarReservacionExterno(url, InsertarReservacionExternoActivity.this);

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