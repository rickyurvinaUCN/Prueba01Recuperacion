package com.example.prueba01recuperacion;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    private EditText txt_code, txt_product;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        txt_code = (EditText) binding.txtCode;
        txt_product = (EditText) binding.txtProduct;
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_SecondFragment);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void save() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(), "administration", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String code = txt_code.getText().toString();
        String name = txt_product.getText().toString();

        if (!code.isEmpty() && !name.isEmpty()) {
            ContentValues register = new ContentValues();
            register.put("code", code);
            register.put("name", name);
            database.insert("products", null, register);
            database.close();
            cleanForm();
            Toast.makeText(this.getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getActivity(), "Completar todos los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(), "administration", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        admin.getWritableDatabase();
        String code = txt_code.getText().toString();
        if (!code.isEmpty()) {
            int count = database.delete("products", "code=" + code, null);
            if (count == 1) {
                database.close();
                cleanForm();
                Toast.makeText(this.getActivity(), "Eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getActivity(), "El producto no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this.getActivity(), "Ingresar código", Toast.LENGTH_SHORT).show();
        }
    }

    public void cleanForm() {
        txt_code.setText("");
        txt_product.setText("");
    }
}