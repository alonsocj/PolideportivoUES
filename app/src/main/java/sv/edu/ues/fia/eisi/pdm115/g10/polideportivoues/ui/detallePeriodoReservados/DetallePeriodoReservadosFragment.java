package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.detallePeriodoReservados;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados.DetallePeriodosReservadosActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados.DetallePeriodosReservadosConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados.DetallePeriodosReservadosEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.DetallePeriodosReservados.DetallePeriodosReservadosInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentDetallePeriodoReservadosBinding;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.local.LocalFragment;


public class DetallePeriodoReservadosFragment extends Fragment {
    private FragmentDetallePeriodoReservadosBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetallePeriodoReservadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button buttonAgregarDetallePR = binding.botonAgregarDetallePeriodosRF;
        buttonAgregarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePeriodoReservadosFragment.this.getContext(), DetallePeriodosReservadosInsertarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonConsultarDetallePR = binding.botonConsultarDetallePeriodosRF;
        buttonConsultarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePeriodoReservadosFragment.this.getContext(), DetallePeriodosReservadosConsultarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonActualizarDetallePR = binding.botonActualizarDetallePeriodosRF;
        buttonActualizarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePeriodoReservadosFragment.this.getContext(), DetallePeriodosReservadosActualizarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonEliminarDetallePR = binding.botonEliminarDetallePeriodosRF;
        buttonEliminarDetallePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePeriodoReservadosFragment.this.getContext(), DetallePeriodosReservadosEliminarActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}