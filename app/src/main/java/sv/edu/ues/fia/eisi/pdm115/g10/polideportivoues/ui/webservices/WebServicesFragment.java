package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.webservices;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ActualizarEvento.ActualizarEventoExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarCobro.ConsultarCobroExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarEvento.ConsultarEventoExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarReservacion.ConsultarReservacionExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.HorariosLocalesUpdate.UpdateHorarioLocalActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarEvento.InsertarEventoExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarPersona.InsertarPersonaExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarReservacion.InsertarReservacionExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentWebServicesBinding;

public class WebServicesFragment extends Fragment {

    private FragmentWebServicesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWebServicesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.buttonInsertEvent.setOnClickListener(this::lanzarActividad);
        binding.buttonQueryCobro.setOnClickListener(this::lanzarActividad);
        binding.buttonQueryReservacion.setOnClickListener(this::lanzarActividad);
        binding.buttonQueryEvento.setOnClickListener(this::lanzarActividad);
        binding.buttonupdateevento.setOnClickListener(this::lanzarActividad);
        binding.btnUpdateLocalReservacion.setOnClickListener(this::lanzarActividad);
        binding.btnInsertPersona.setOnClickListener(this::lanzarActividad);
        binding.btnInsertReservacion.setOnClickListener(this::lanzarActividad);


        return root;
    }

    public void lanzarActividad(View view){
        Intent i = null;
        switch (view.getId()){
            case R.id.button_insert_event:
                i = new Intent(WebServicesFragment.this.getContext(), InsertarEventoExternoActivity.class);
                break;
            case R.id.button_query_cobro:
                i = new Intent(WebServicesFragment.this.getContext(), ConsultarCobroExternoActivity.class);
                break;
            case R.id.button_query_reservacion:
                i = new Intent(WebServicesFragment.this.getContext(), ConsultarReservacionExternoActivity.class);
                break;
            case R.id.button_query_evento:
                i = new Intent(WebServicesFragment.this.getContext(), ConsultarEventoExternoActivity.class);
                break;
            case R.id.buttonupdateevento:
                i = new Intent(WebServicesFragment.this.getContext(), ActualizarEventoExternoActivity.class);
                break;
            case R.id.btn_update_local_reservacion:
                i = new Intent(WebServicesFragment.this.getContext(), UpdateHorarioLocalActivity.class);
                break;
            case R.id.btn_insert_persona:
                i = new Intent(WebServicesFragment.this.getContext(), InsertarPersonaExternoActivity.class);
                break;
            case R.id.btn_insert_reservacion:
                i = new Intent(WebServicesFragment.this.getContext(), InsertarReservacionExternoActivity.class);
                break;
        }
        if(i!=null){
            startActivity(i);
        }
    }

}