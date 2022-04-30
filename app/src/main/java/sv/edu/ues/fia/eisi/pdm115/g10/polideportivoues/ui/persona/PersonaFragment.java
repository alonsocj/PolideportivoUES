package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.persona;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Gustavo.Persona.PersonaInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentPersonaBinding;

public class PersonaFragment extends Fragment {
    private FragmentPersonaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPersonaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Persona*/
        final Button buttonAgregarPersona = binding.botonAgregarPersona;
        buttonAgregarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonaFragment.this.getContext(), PersonaInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Persona*/

        final Button buttonConsultarPersona = binding.botonConsultarPersona;
        buttonConsultarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (PersonaFragment.this.getContext(), PersonaConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Persona*/

        final Button buttonActualizarPersona = binding.botonActualizarPersona;
        buttonActualizarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (PersonaFragment.this.getContext(), PersonaActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Persona*/

        final Button buttonEliminarPersona = binding.botonEliminarPersona;
        buttonEliminarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonaFragment.this.getContext(), PersonaEliminarActivity.class);
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
