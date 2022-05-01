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
        buttonAgregarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodoReservaFragment.this.getContext(), PeriodoReservaInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar PeriodoReserva*/

        final Button buttonConsultarPeriodoReserva = binding.botonConsultarPeriodoReserva;
        buttonConsultarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (PeriodoReservaFragment.this.getContext(), PeriodoReservaConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar PeriodoReserva*/

        final Button buttonActualizarPeriodoReserva = binding.botonActualizarPeriodoReserva;
        buttonActualizarPeriodoReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (PeriodoReservaFragment.this.getContext(), PeriodoReservaActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar PeriodoReserva*/

        final Button buttonEliminarPeriodoReserva = binding.botonEliminarPeriodoReserva;
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