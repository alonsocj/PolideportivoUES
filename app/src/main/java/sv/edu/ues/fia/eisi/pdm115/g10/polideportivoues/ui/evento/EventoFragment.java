package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.evento;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.EventoActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.EventoConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.EventoEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.EventoInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentEventoBinding;


public class EventoFragment extends Fragment {

    private FragmentEventoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEventoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        /*Funcionamiento de Botones*/

        /*AgregarEvento*/
        final Button buttonAgregarEvento = binding.botonAgregarEvento;
        buttonAgregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventoFragment.this.getContext(), EventoInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar evento*/
        final Button buttonConsultarEvento = binding.botonConsultarEvento;
        buttonConsultarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventoFragment.this.getContext(), EventoConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Actualizar evento*/
        final Button buttonActualizarEvento = binding.botonActualizarEvento;
        buttonActualizarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventoFragment.this.getContext(), EventoActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar evento*/
        final Button buttonEliminarEvento = binding.botonEliminarEvento;
        buttonEliminarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventoFragment.this.getContext(), EventoEliminarActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}