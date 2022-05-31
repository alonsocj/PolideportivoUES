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
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentHorariosDisponiblesBinding;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentPersonaBinding;

public class HorariosDisponiblesFragment extends Fragment {
    private FragmentHorariosDisponiblesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHorariosDisponiblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Horario Disponible*/
        final Button buttonAgregarHorariosDisponibles = binding.botonAgregarHorarioDisponible;
        buttonAgregarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Horario Disponible*/

        final Button buttonConsultarHorariosDisponibles = binding.botonConsultarHorarioDisponible;
        buttonConsultarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Horario Disponible*/

        final Button buttonActualizarHorariosDisponibles = binding.botonActualizarHorarioDisponible;
        buttonActualizarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Horario Disponible*/

        final Button buttonEliminarHorariosDisponibles = binding.botonEliminarHorarioDisponible;
        buttonEliminarHorariosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HorariosDisponiblesFragment.this.getContext(), HorariosDisponiblesEliminarActivity.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("061")) {
                buttonAgregarHorariosDisponibles.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarHorariosDisponibles.setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("060") || ProcesarDatos.getAcceso().get(i).equals("062") || ProcesarDatos.getAcceso().get(i).equals("063")) {
                buttonEliminarHorariosDisponibles.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarHorariosDisponibles.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("061")) {
                buttonActualizarHorariosDisponibles.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarHorariosDisponibles.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("061")) {
                buttonConsultarHorariosDisponibles.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarHorariosDisponibles.setVisibility(View.INVISIBLE);
            }
        }


        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
