package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.localEvento;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.LocalEvento.LocalEventoInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentLocalEventoBinding;


public class LocalEventoFragment extends Fragment {
    private FragmentLocalEventoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLocalEventoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar LocalEvento*/
        final Button buttonAgregarLocalEvento = binding.botonAgregarLocalEvento;
        buttonAgregarLocalEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalEventoFragment.this.getContext(), LocalEventoInsertarActivity.class);
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