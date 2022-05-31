package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.periodoReserva;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.PeriodoReserva.PeriodoReservaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentPeriodoReservaBinding;


public class PeriodoReservaFragment extends Fragment {

    private FragmentPeriodoReservaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPeriodoReservaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar PeriodoReserva*/
        final Button buttonAgregarPeriodoReserva = binding.botonAgregarPeriodoReserva;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("051")) {
                buttonAgregarPeriodoReserva.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarPeriodoReserva.setVisibility(View.INVISIBLE);
            }
        }

        buttonAgregarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodoReservaFragment.this.getContext(), PeriodoReservaInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar PeriodoReserva*/

        final Button buttonConsultarPeriodoReserva = binding.botonConsultarPeriodoReserva;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("051")) {
                buttonConsultarPeriodoReserva.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarPeriodoReserva.setVisibility(View.INVISIBLE);
            }
        }

        buttonConsultarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (PeriodoReservaFragment.this.getContext(), PeriodoReservaConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar PeriodoReserva*/

        final Button buttonActualizarPeriodoReserva = binding.botonActualizarPeriodoReserva;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("051")) {
                buttonActualizarPeriodoReserva.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarPeriodoReserva.setVisibility(View.INVISIBLE);
            }
        }

        buttonActualizarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (PeriodoReservaFragment.this.getContext(), PeriodoReservaActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar PeriodoReserva*/

        final Button buttonEliminarPeriodoReserva = binding.botonEliminarPeriodoReserva;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("050")||ProcesarDatos.getAcceso().get(i).equals("052")||ProcesarDatos.getAcceso().get(i).equals("053")) {
                buttonEliminarPeriodoReserva.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarPeriodoReserva.setVisibility(View.INVISIBLE);
            }
        }

        buttonEliminarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodoReservaFragment.this.getContext(), PeriodoReservaEliminarActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}