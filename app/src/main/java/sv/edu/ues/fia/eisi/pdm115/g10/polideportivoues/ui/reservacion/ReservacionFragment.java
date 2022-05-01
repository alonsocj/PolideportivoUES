package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.reservacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Carolina.Reservacion.ReservacionInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentReservacionBinding;

public class ReservacionFragment extends Fragment {

    private FragmentReservacionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReservacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Funcionamiento con botones*/

        /*Agregar Reservacion*/
        final Button buttonAgregarReservacion = binding.botonAgregarReservacion;
        buttonAgregarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservacionFragment.this.getContext(), ReservacionInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Reservacion*/

        final Button buttonConsultarReservacion = binding.botonConsultarReservacion;
        buttonConsultarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ReservacionFragment.this.getContext(), ReservacionConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Reservacion*/

        final Button buttonActualizarReservacion = binding.botonActualizarReservacion;
        buttonActualizarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ReservacionFragment.this.getContext(), ReservacionActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Reservacion*/

        final Button buttonEliminarReservacion = binding.botonEliminarReservacion;
        buttonEliminarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservacionFragment.this.getContext(), ReservacionEliminarActivity.class);
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