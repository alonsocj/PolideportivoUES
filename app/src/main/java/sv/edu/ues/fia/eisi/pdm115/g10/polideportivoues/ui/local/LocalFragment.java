package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.local;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentLocalBinding;

public class LocalFragment extends Fragment {

    private FragmentLocalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLocalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button buttonAgregarlocal = binding.botonAgregarLocalF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("041")) {
                buttonAgregarlocal.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarlocal.setVisibility(View.INVISIBLE);
            }
        }

        buttonAgregarlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalFragment.this.getContext(), LocalInsertarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonConsultarlocal = binding.botonConsultarLocalF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("041")) {
                buttonConsultarlocal.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarlocal.setVisibility(View.INVISIBLE);
            }
        }

        buttonConsultarlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalFragment.this.getContext(), LocalConsultarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonActualizarlocal = binding.botonActualizarLocalF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("041")) {
                buttonActualizarlocal.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarlocal.setVisibility(View.INVISIBLE);
            }
        }
        buttonActualizarlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalFragment.this.getContext(), LocalActualizarActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonEliminarlocal = binding.botonEliminarLocalF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("040") || ProcesarDatos.getAcceso().get(i).equals("042") || ProcesarDatos.getAcceso().get(i).equals("043")) {
                buttonEliminarlocal.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarlocal.setVisibility(View.INVISIBLE);
            }
        }

        buttonEliminarlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalFragment.this.getContext(), LocalEliminarActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}