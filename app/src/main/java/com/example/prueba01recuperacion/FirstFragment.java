package com.example.prueba01recuperacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ScrollView scr_1;
    private Spinner sp_1;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        scr_1=(ScrollView) binding.scr1;

//        String [] names={};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>();
//        name_spinner.setAdapter(adapter);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnCerezas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Completar lógica de ScrollView, ojo no necesariamente se aplica toda la lógica en este boton.
            }
        });

        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Completar el codigo necesario para reproducir el sonido "sound_long.mp3"
//                mp.start();
            }
        });

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Código para dar funcionamiento al spinner.
                //Puede que el onClick no sea el mejor método para resolver.
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Función para capturar la imagen seleccionada
     * @param view
     */
    public void selected(View view) {
//        Se recomendia generar una lógica de prgoramación para validar que fruta se selecciono y mostrar en un Toast el Nombre de la fruta seleccionada.
    }

}