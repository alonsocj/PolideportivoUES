package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.HorariosLocales;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HoariosLocalesActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocalesConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocalesEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocalesInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentHorariosLocalesBinding;


public class HorariosLocalesFragment extends Fragment {
    private FragmentHorariosLocalesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHorariosLocalesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button buttonAgregarDetallePR = binding.botonAgregarDetallePeriodosRF;
        buttonAgregarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosLocalesFragment.this.getContext(), HorariosLocalesInsertarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonConsultarDetallePR = binding.botonConsultarDetallePeriodosRF;
        buttonConsultarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosLocalesFragment.this.getContext(), HorariosLocalesConsultarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonActualizarDetallePR = binding.botonActualizarDetallePeriodosRF;
        buttonActualizarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosLocalesFragment.this.getContext(), HoariosLocalesActualizarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonEliminarDetallePR = binding.botonEliminarDetallePeriodosRF;
        buttonEliminarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosLocalesFragment.this.getContext(), HorariosLocalesEliminarActivity.class);
                startActivity(intent);
            }
        });

         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("121")) {
                buttonAgregarDetallePR.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarDetallePR.setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
           if (ProcesarDatos.getAcceso().get(i).equals("120") || ProcesarDatos.getAcceso().get(i).equals("122") || ProcesarDatos.getAcceso().get(i).equals("123")) {
                buttonEliminarDetallePR.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarDetallePR.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("121")) {
                buttonActualizarDetallePR.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarDetallePR.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("121")) {
                buttonConsultarDetallePR.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarDetallePR.setVisibility(View.INVISIBLE);
            }
        }


        return root;
    }
}