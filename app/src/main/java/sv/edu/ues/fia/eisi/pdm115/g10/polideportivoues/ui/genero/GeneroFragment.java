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
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
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

        
        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("131")) {
                buttonAgregarGenero.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarGenero.setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("130") || ProcesarDatos.getAcceso().get(i).equals("132") || ProcesarDatos.getAcceso().get(i).equals("133")) {
                buttonEliminarGenero.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarGenero.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("131")) {
                buttonActualizarGenero.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarGenero.setVisibility(View.INVISIBLE);
            }
        }


         for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("131")) {
                buttonConsultarGenero.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarGenero.setVisibility(View.INVISIBLE);
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