package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.horariosdisponibles;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.HorariosDisponibles.HorariosDisponiblesInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentHorariosDisponiblesBinding;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentPersonaBinding;

public class HorariosDisponiblesFragment extends Fragment {
    private FragmentHorariosDisponiblesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHorariosDisponiblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Persona*/
        final Button buttonAgregarHorariosDisponibles = binding.botonAgregarHorarioDisponible;
        buttonAgregarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Persona*/

        final Button buttonConsultarHorariosDisponibles = binding.botonConsultarHorarioDisponible;
        buttonConsultarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Persona*/

        final Button buttonActualizarHorariosDisponibles = binding.botonActualizarHorararioDisponible;
        buttonActualizarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Persona*/

        final Button buttonEliminarHorariosDisponibles = binding.botonEliminarHorararioDisponible;
        buttonEliminarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesEliminarActivity.class);
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
