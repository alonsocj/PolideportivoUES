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
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
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

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("011")) {
                buttonAgregarReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonAgregarReservacion.setVisibility(View.INVISIBLE);
            }
        }

        buttonAgregarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservacionFragment.this.getContext(), ReservacionInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Reservacion*/

        final Button buttonConsultarReservacion = binding.botonConsultarReservacion;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("011")) {
                buttonConsultarReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonConsultarReservacion.setVisibility(View.INVISIBLE);
            }
        }

        buttonConsultarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservacionFragment.this.getContext(), ReservacionConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Modificar Reservacion*/

        final Button buttonActualizarReservacion = binding.botonActualizarReservacion;
        
        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("011")) {
                buttonActualizarReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonActualizarReservacion.setVisibility(View.INVISIBLE);
            }
        }

        buttonActualizarReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservacionFragment.this.getContext(), ReservacionActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Reservacion*/

        final Button buttonEliminarReservacion = binding.botonEliminarReservacion;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("010") || ProcesarDatos.getAcceso().get(i).equals("012") || ProcesarDatos.getAcceso().get(i).equals("013")) {
                buttonEliminarReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                buttonEliminarReservacion.setVisibility(View.INVISIBLE);
            }
        }

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