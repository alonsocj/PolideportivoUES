package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.nacionalidad;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.NacionalidadActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.NacionalidadConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.NacionalidadEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Nacionalidad.NacionalidadInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentNacionalidadBinding;

public class NacionalidadFragment extends Fragment {

    private FragmentNacionalidadBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentNacionalidadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Nacionalidad*/
        final Button buttonAgregarNacionalidad = binding.botonAgregarNacionalidad;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("151")) {
                buttonAgregarNacionalidad.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarNacionalidad.setVisibility(View.INVISIBLE);
            }
        }

        buttonAgregarNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NacionalidadFragment.this.getContext(), NacionalidadInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Nacionalidad*/

        final Button buttonConsultarNacionalidad = binding.botonConsultarNacionalidad;
        
        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("151")) {
                buttonConsultarNacionalidad.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarNacionalidad.setVisibility(View.INVISIBLE);
            }
        }
        
        buttonConsultarNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (NacionalidadFragment.this.getContext(), NacionalidadConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Nacionalidad*/

        final Button buttonActualizarNacionalidad = binding.botonActualizarNacionalidad;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("151")) {
                buttonActualizarNacionalidad.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarNacionalidad.setVisibility(View.INVISIBLE);
            }
        }

        buttonActualizarNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (NacionalidadFragment.this.getContext(), NacionalidadActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Nacionalidad*/

        final Button buttonEliminarNacionalidad = binding.botonEliminarNacionalidad;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("150")||ProcesarDatos.getAcceso().get(i).equals("152")||ProcesarDatos.getAcceso().get(i).equals("153")) {
                buttonEliminarNacionalidad.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarNacionalidad.setVisibility(View.INVISIBLE);
            }
        }

        buttonEliminarNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NacionalidadFragment.this.getContext(), NacionalidadEliminarActivity.class);
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