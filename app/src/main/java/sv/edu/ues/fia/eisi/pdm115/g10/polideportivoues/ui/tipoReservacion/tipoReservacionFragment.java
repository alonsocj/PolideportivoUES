package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.tipoReservacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Login.ProcesarDatos;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.Local.LocalInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacionActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacionConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacionEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.TipoReservacion.TipoReservacionInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentLocalBinding;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentTipoReservacionBinding;


public class tipoReservacionFragment extends Fragment {

    private FragmentTipoReservacionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTipoReservacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnAgregartipoReservacion = binding.botonAgregarTipoReservacionF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("111")) {
                btnAgregartipoReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                btnAgregartipoReservacion.setVisibility(View.INVISIBLE);
            }
        }

        btnAgregartipoReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoReservacionFragment.this.getContext(), TipoReservacionInsertarActivity.class);
                startActivity(intent);
            }
        });
        final Button btnConsultartipoReservacion = binding.botonConsultarTipoReservacionF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("111")) {
                btnConsultartipoReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                btnConsultartipoReservacion.setVisibility(View.INVISIBLE);
            }
        }

        btnConsultartipoReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoReservacionFragment.this.getContext(), TipoReservacionConsultarActivity.class);
                startActivity(intent);
            }
        });
        final Button btnActualizartipoReservacion = binding.botonActualizarTipoReservacionF;

        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("111")) {
                btnActualizartipoReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                btnActualizartipoReservacion.setVisibility(View.INVISIBLE);
            }
        }


        btnActualizartipoReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoReservacionFragment.this.getContext(), TipoReservacionActualizarActivity.class);
                startActivity(intent);
            }
        });
        final Button btnEliminartipoReservacion = binding.botonEliminarTipoReservacionF;


        for (int i = 0; i < ProcesarDatos.getAcceso().size(); i++) {
            if (ProcesarDatos.getAcceso().get(i).equals("110")||ProcesarDatos.getAcceso().get(i).equals("112")||ProcesarDatos.getAcceso().get(i).equals("113")) {
                btnEliminartipoReservacion.setVisibility(View.VISIBLE);
                break;
            } else {
                btnEliminartipoReservacion.setVisibility(View.INVISIBLE);
            }
        }

        btnEliminartipoReservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoReservacionFragment.this.getContext(), TipoReservacionEliminarActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}