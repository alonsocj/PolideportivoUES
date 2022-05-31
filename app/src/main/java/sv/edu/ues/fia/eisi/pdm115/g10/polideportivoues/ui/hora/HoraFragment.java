package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.hora;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.HoraActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.HoraConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.HoraEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Hora.HoraInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentHoraBinding;


public class HoraFragment extends Fragment {

    private FragmentHoraBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHoraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Hora*/
        final Button buttonAgregarHora = binding.botonAgregarHora;
        buttonAgregarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoraFragment.this.getContext(), HoraInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Hora*/

        final Button buttonConsultarHora = binding.botonConsultarHora;
        buttonConsultarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HoraFragment.this.getContext(), HoraConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Hora*/

        final Button buttonActualizarHora = binding.botonModificarHora;
        buttonActualizarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HoraFragment.this.getContext(), HoraActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Hora*/

        final Button buttonEliminarHora = binding.botonEliminarHora;
        buttonEliminarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoraFragment.this.getContext(), HoraEliminarActivity.class);
                startActivity(intent);
            }
        });

         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("071")) {
                buttonAgregarHora.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarHora.setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("070") || ProcesarDatos.getAcceso().get(i).equals("072") || ProcesarDatos.getAcceso().get(i).equals("073")) {
                buttonEliminarHora.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarHora.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("071")) {
                buttonActualizarHora.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarHora.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("071")) {
                buttonConsultarHora.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarHora.setVisibility(View.INVISIBLE);
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