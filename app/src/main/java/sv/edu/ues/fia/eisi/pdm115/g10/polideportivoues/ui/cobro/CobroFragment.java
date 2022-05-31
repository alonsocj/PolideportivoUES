package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.cobro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.CobroActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.CobroConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.CobroEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.Cobro.CobroInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentCobroBinding;

public class CobroFragment extends Fragment {

    private FragmentCobroBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCobroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnInsertar = binding.botonAgregarCobro;
        final Button btnConsultar = binding.botonConsultarCobro;
        final Button btnActualizar = binding.botonActualizarCobro;
        final Button btnEliminar = binding.botonEliminarCobro;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("091")) {
                btnInsertar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnInsertar.setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("090") || ProcesarDatos.getAcceso().get(i).equals("092") || ProcesarDatos.getAcceso().get(i).equals("093")) {
                btnEliminar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnEliminar.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("091")) {
                btnActualizar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnActualizar.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("091")) {
                btnConsultar.setVisibility(View.VISIBLE);
                break;
            } else {
                btnConsultar.setVisibility(View.INVISIBLE);
            }
        }
        
        
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CobroInsertarActivity.class);
                startActivity(intent);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CobroConsultarActivity.class);
                startActivity(intent);
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CobroActualizarActivity.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CobroEliminarActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}