package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.webservices;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.R;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.InsertarEvento.InsertarEventoExternoActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentWebServicesBinding;

public class WebServicesFragment extends Fragment {

    private FragmentWebServicesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWebServicesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.buttonInsertEvent.setOnClickListener(this::lanzarActividad);

        return root;
    }

    public void lanzarActividad(View view){
        Intent i = null;
        switch (view.getId()){
            case R.id.button_insert_event:
                i = new Intent(WebServicesFragment.this.getContext(), InsertarEventoExternoActivity.class);
                break;
        }
        if(i!=null){
            startActivity(i);
        }
    }

}