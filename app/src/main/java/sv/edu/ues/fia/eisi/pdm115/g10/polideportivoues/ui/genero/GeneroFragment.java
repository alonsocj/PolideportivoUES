package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.genero;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.GeneroActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.GeneroConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.GeneroEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Genero.GeneroInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentGeneroBinding;

public class GeneroFragment extends Fragment {

    private FragmentGeneroBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGeneroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Genero*/
        final Button buttonAgregarGenero= binding.botonAgregarGenero;
        buttonAgregarGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneroFragment.this.getContext(), GeneroInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Genero*/

        final Button buttonConsultarGenero = binding.botonConsultarGenero;
        buttonConsultarGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GeneroFragment.this.getContext(), GeneroConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Genero*/

        final Button buttonActualizarGenero = binding.botonActualizarGenero;
        buttonActualizarGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GeneroFragment.this.getContext(), GeneroActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Genero*/

        final Button buttonEliminarGenero = binding.botonEliminarGenero;
        buttonEliminarGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneroFragment.this.getContext(), GeneroEliminarActivity.class);
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