package com.example.tp2sifmarchane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tp2sifmarchane.databinding.ActivityAjoutJoueurBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AjoutJoueurActivity extends AppCompatActivity {

    ActivityAjoutJoueurBinding binding;
    String nom, age, position, nationalite;
    FirebaseDatabase bd;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_joueur);


        binding = ActivityAjoutJoueurBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSauvegarderJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nom = binding.etNomJoueur.getText().toString();
                age = binding.etAge.getText().toString();
                position = binding.etPosition.getText().toString();
                nationalite = binding.etNationalite.getText().toString();

                if(!nom.isEmpty() && !age.isEmpty() && !position.isEmpty() && !nationalite.isEmpty()){

                    utilisateur usager = new utilisateur(nom, age, position, nationalite);
                    bd = FirebaseDatabase.getInstance();
                    ref = bd.getReference("Utilisateurs");
                    ref.child(nom).setValue(usager).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.etNomJoueur.setText("");
                            binding.etAge.setText("");
                            binding.etPosition.setText("");
                            binding.etNationalite.setText("");

                            Toast.makeText(AjoutJoueurActivity.this, "Joueur ajouté", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });








    }
}