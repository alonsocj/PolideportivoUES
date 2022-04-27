package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.local;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.MainActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalMenuActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentHoraBinding;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentLocalBinding;

public class LocalFragment extends Fragment {

    private FragmentLocalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLocalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con Boton Gestionar*/
        final Button buttonGestionarLocal = binding.botonGestionarLocal;
        buttonGestionarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalFragment.this.getContext(), LocalMenuActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}