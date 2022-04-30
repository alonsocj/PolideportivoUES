package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.ui.tipoPago;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPagoActualizarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPagoConsultarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPagoEliminarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.TipoPago.TipoPagoInsertarActivity;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.databinding.FragmentTipoPagoBinding;

public class tipoPagoFragment extends Fragment {

    private FragmentTipoPagoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTipoPagoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        /*Funcionamiento de Botones*/

        /*Agregar Tipo de Pago*/

        final Button buttonAgregarTipoPago = binding.botonAgregarTipoPago;
        buttonAgregarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoPagoFragment.this.getContext(), TipoPagoInsertarActivity.class);
                startActivity(intent);
            }
        });

        /*Consultar Tipo de Pago*/
        final Button buttonConsultarTipoPago = binding.botonConsultarTipoPago;
        buttonConsultarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoPagoFragment.this.getContext(), TipoPagoConsultarActivity.class);
                startActivity(intent);
            }
        });

        /*Actualizar Tipo de Pago */
        final Button buttonActualizarTipoPago = binding.botonModificarTipoPago;
        buttonActualizarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoPagoFragment.this.getContext(), TipoPagoActualizarActivity.class);
                startActivity(intent);
            }
        });

        /*Eliminar Tipo de Pago*/
        final Button buttonEliminarTipoPago = binding.botonEliminarTipoPago;
        buttonEliminarTipoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tipoPagoFragment.this.getContext(), TipoPagoEliminarActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}