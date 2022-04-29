package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.tipoevento;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEventoActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEventoConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEventoEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEventoInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentTipoEventoBinding;


public class TipoEventoFragment extends Fragment {

    private FragmentTipoEventoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTipoEventoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnAgregarTipoE = binding.botonAgregarTipoEvento;
        final Button btnConsultarTipoE = binding.botonConsultarTipoEvento;
        final Button btnActualizarTipoE = binding.botonActualizarTipoEvento;
        final Button btnEliminarTipoE = binding.botonEliminarTipoEvento;
        // Acciones con los botones

        // agregar tipo evento

        btnAgregarTipoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipoEventoFragment.this.getContext(), TipoEventoInsertarActivity.class);
                startActivity(intent);
            }
        });

        // consultar tipo evento

        btnConsultarTipoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipoEventoFragment.this.getContext(), TipoEventoConsultarActivity.class);
                startActivity(intent);
            }
        });

        // actualizar tipo evento

        btnActualizarTipoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipoEventoFragment.this.getContext(), TipoEventoActualizarActivity.class);
                startActivity(intent);
            }
        });

        // eliminar tipo evento

        btnEliminarTipoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipoEventoFragment.this.getContext(), TipoEventoEliminarActivity.class);
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