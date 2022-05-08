package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.Cobro;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.Dia;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReserva;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.Hora;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDCarolina;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDG10William;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ControlBDGustavo;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponibles;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.Persona;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.Local;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacion;

public class ReservacionConsultarActivity extends AppCompatActivity {
    String horario,local;
    TextInputEditText editIdReservacion, etFechaR;
    ControlBDCarolina helper;
    ControlBDGustavo helper1;
    TextInputEditText spCobro,spPersona, spTReservacion,spEvento,spPeriodoR, spHLocal;

    List<Cobro> arrayCobros=new ArrayList<Cobro>();
    List<Persona> arrayPersonas=new ArrayList<Persona>();
    List<TipoReservacion> arrayTipoReservaciones=new ArrayList<TipoReservacion>();
    List<Evento> arrayEventos=new ArrayList<Evento>();
    List<HorariosDisponibles> arrayHorariosDisponibles=new ArrayList<HorariosDisponibles>();
    List<HorariosLocales> arrayHorariosLocales=new ArrayList<HorariosLocales>();
    List<PeriodoReserva> arrayPeriodoReserva=new ArrayList<PeriodoReserva>();
    List<Local> arraylocales=new ArrayList<Local>();

    List<Hora> arrayHoras=new ArrayList<Hora>();
    List<Dia> arrayDias=new ArrayList<Dia>();

    Button botonConsultar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_consultar);

        helper = new ControlBDCarolina(this);
        helper1 = new ControlBDGustavo(this);

        editIdReservacion=findViewById(R.id.idReservacion);
        spCobro=findViewById(R.id.idCobro);
        spPersona=findViewById(R.id.idPersona);
        spTReservacion=findViewById(R.id.editText_idTipoReservacion);
        spEvento=findViewById(R.id.idEvento);
        spPeriodoR=findViewById(R.id.idPeriodoReserva);
        spHLocal=findViewById(R.id.idHorarioLocal);
        etFechaR=findViewById(R.id.et_FechaR);
        botonConsultar = (Button) findViewById(R.id.botonConsultarReservacion);



        //Llenado del spinner de cobros
        helper.open();
        arrayCobros=helper.consultarCobros();
        helper.close();

        //Llenado del spinner de personas
        helper.open();
        arrayPersonas=helper.consultarPersonas();
        helper.close();

        //Llenado del spinner de tipo de reservacion
        helper.open();
        arrayTipoReservaciones=helper.consultarTipoReservacion();
        helper.close();

        //Llenado del spinner de eventos
        helper.open();
        arrayEventos=helper.consultarEventos();
        helper.close();

        //Llenado del spinner de periodo reservas
        helper.open();
        arrayPeriodoReserva=helper.consultarPeriodoReservacion();
        helper.close();

        //Llenado del spinner de horarios y locales
        helper.open();
        arrayHorariosLocales=helper.consultarHorariosLocales1();
        helper.close();

        //LLenado del array de horarios disponibles
        helper.open();
        arrayHorariosDisponibles=helper.consultarHorariosDisponibles();
        helper.close();

        //LLenado del array de horarios
        helper1.open();
        arrayHoras=helper1.consultarHoras();

        helper1.open();
        arrayDias=helper1.consultarDias();

        //Llenado del array de locales
        helper.open();
        arraylocales=helper.consultarLocales();
        helper.close();

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editIdReservacion.getText().toString().isEmpty()){
                    Toast.makeText(ReservacionConsultarActivity.this, "Debe completar los campos para consultar una reservación!", Toast.LENGTH_SHORT).show();
                }else {
                    helper.open();
                    Reservacion reservacion = helper.consultarReservacion(editIdReservacion.getText().toString());
                    helper.close();

                    /*Verificación que exista la reservación*/
                   if (reservacion == null) {
                        Toast.makeText(ReservacionConsultarActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                        etFechaR.setText("");
                        spCobro.setText("");
                        spPersona.setText("");
                        spTReservacion.setText("");
                        spEvento.setText("");
                        spPeriodoR.setText("");
                        spHLocal.setText("");
                   } else {
                       //Recuperamos los elementos

                       for (int i = 0; i < arrayCobros.size(); i++) {
                            Cobro cobro=arrayCobros.get(i);
                            if (cobro.getIdCobro()==reservacion.getIdCobro()) {
                                spCobro.setText(reservacion.getIdCobro().toString());
                            }
                        }
                       for(int i=0;i<arrayPersonas.size();i++){
                            Persona persona=arrayPersonas.get(i);
                            if (persona.getIdPersona().equals(reservacion.getIdPersona())) {
                                spPersona.setText(persona.getNombre()+" "+persona.getApellido());
                            }
                        }
                       for(int i=0;i<arrayTipoReservaciones.size();i++){
                            TipoReservacion tipoReservacion=arrayTipoReservaciones.get(i);
                            if(tipoReservacion.getIdTipoR().equals(reservacion.getIdTipoR())){
                                spTReservacion.setText(tipoReservacion.getNomTipoR());
                            }
                        }
                       for(int i=0;i<arrayEventos.size();i++){
                            Evento evento=arrayEventos.get(i);
                            if(evento.getIdEvento().equals(reservacion.getIdEvento())){
                                spEvento.setText(evento.getNomEvento());
                            }

                       }
                       for(int i=0;i<arrayPeriodoReserva.size();i++){
                            PeriodoReserva periodoReserva=arrayPeriodoReserva.get(i);
                            if (periodoReserva.getIdPeriodoReserva().equals(reservacion.getIdPeriodoReserva())){
                                spPeriodoR.setText(periodoReserva.getFechaInicio()+" - "+periodoReserva.getFechaFin());
                            }
                       }
                       for(int i=0;i<arrayHorariosLocales.size();i++){
                           HorariosLocales horarioLocales = arrayHorariosLocales.get(i);
                           if(horarioLocales.getIdHorario().equals(reservacion.getIdHorario()) && horarioLocales.getIdLocal().equals(reservacion.getIdLocal())){
                               for (int k=0;k<arrayHorariosDisponibles.size();k++){
                                   HorariosDisponibles horariosDisponibles=arrayHorariosDisponibles.get(k);
                                   if(horarioLocales.getIdHorario().equals(horariosDisponibles.getIdHorario())){
                                       for (int j = 0; j < arrayHoras.size(); j++) {
                                           Hora horasArray = arrayHoras.get(j);
                                           if (horasArray.getIdHora().equals(horariosDisponibles.getHora())) {
                                               horario = horariosDisponibles.getDia()+" "+ horasArray.getHoraInicio() + " - " + horasArray.getHoraFin();
                                           }
                                       }
                                   }
                               }
                               for (int l=0;l<arraylocales.size();l++){
                                   Local locales=arraylocales.get(l);
                                   if(locales.getIdLocal().equals(horarioLocales.getIdLocal())){
                                       local=locales.getNomLocal();
                                   }
                               }

                               spHLocal.setText(local+" "+horario);
                           }

                       }
                       etFechaR.setText(reservacion.getFechaRegistro());
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