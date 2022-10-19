package com.example.prueba01recuperacion;

import android.media.MediaPlayer;
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

import java.lang.reflect.Array;

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
        scr_1 = (ScrollView) binding.scr1;
        sp_1 = (Spinner) binding.sp1;

        String[] names =
                {
                        "Ricardo",
                        "Ingrid",
                        "Menthor"
                };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
        sp_1.setAdapter(adapter);
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
                Toast.makeText(getContext(), "Cerezas seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnMora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Moras seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnFrutilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Frutillas seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnBananas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Bananas seleccionadas", Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.sound_long);
                mp.start();
            }
        });

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = sp_1.getSelectedItem().toString();
                switch (selected) {
                    case "Ricardo":
                        Toast.makeText(getContext(), selected + ": Edad 26", Toast.LENGTH_SHORT).show();
                        break;
                    case "Ingrid":
                        Toast.makeText(getContext(), selected + ": Edad 27", Toast.LENGTH_SHORT).show();
                        break;
                    case "Menthor":
                        Toast.makeText(getContext(), selected + ": Edad 60", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}