package com.example.mesajgonderme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class grupolustur extends Fragment {

    EditText grupadi, aciklama;
    ImageView grupresim;
    Button grupolustur;
    RecyclerView gruplar;

    Uri grupresmi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grupolustur, container, false);

        grupadi = view.findViewById(R.id.editTextTextPersonName);
        aciklama = view.findViewById(R.id.editTextTextPersonName2);
        grupresim = view.findViewById(R.id.imageView3);
        grupolustur = view.findViewById(R.id.button);
        gruplar = view.findViewById(R.id.RecyclerView);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if(result.getResultCode() == getActivity().RESULT_OK){
                        Intent data = result.getData();
                        grupresmi = data.getData();
                        grupresim.setImageURI(grupresmi);
                    }
                }
        );

        grupresim.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);
        });

        return view;
    }
}