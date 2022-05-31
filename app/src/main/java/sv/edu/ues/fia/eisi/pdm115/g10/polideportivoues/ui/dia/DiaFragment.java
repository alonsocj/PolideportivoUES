package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.dia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.DiaActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.DiaConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.DiaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Dia.DiaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentCobroBinding;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentDiaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaFragment extends Fragment {

    private FragmentDiaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDiaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnInsertar = binding.botonAgregarDia;
        final Button btnConsultar = binding.botonConsultarDia;
        final Button btnActualizar = binding.botonActualizarDia;
        final Button btnEliminar = binding.botonEliminarDia;

            for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("151")) {
                btnInsertar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnInsertar.setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("150") || ProcesarDatos.getAcceso().get(i).equals("152") || ProcesarDatos.getAcceso().get(i).equals("153")) {
                btnEliminar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnEliminar.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("151")) {
                btnActualizar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnActualizar.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("151")) {
                btnConsultar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnConsultar.setVisibility(View.INVISIBLE);
            }
        }

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaInsertarActivity.class);
                startActivity(intent);
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaConsultarActivity.class);
                startActivity(intent);
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaActualizarActivity.class);
                startActivity(intent);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaEliminarActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}