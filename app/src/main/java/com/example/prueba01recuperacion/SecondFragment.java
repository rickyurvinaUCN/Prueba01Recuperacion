package com.example.prueba01recuperacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private EditText txt_email, txt_name, txt_search;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        txt_email = (EditText) binding.txtEmail;
        txt_name = (EditText) binding.txtName;
        txt_search = (EditText) binding.txtSearch;
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        binding.btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate();
            }
        });

        binding.btnToThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navigate() {
        String url = txt_search.getText().toString();
        Intent next = new Intent(this.getActivity(), ActivityWeb.class);
        next.putExtra("url", url);
        startActivity(next);

    }

    public void save() {
        String name = txt_name.getText().toString();
        String email = txt_email.getText().toString();
        SharedPreferences preferences = getActivity().getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString(name, email);
        obj_editor.commit();
        txt_email.setText("");
        txt_name.setText("");
        Toast.makeText(this.getActivity(), "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();
    }

    public void search() {
        String name = txt_name.getText().toString();
        SharedPreferences preferences = getActivity().getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String info = preferences.getString(name, "");
        if (info.length() == 0) {
            Toast.makeText(getActivity(), "No se encontró ningun registro", Toast.LENGTH_LONG).show();
        } else {
            txt_email.setText(info);
        }
    }
}