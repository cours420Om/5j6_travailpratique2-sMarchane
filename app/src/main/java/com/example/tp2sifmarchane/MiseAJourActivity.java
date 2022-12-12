package com.example.tp2sifmarchane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2sifmarchane.databinding.ActivityAjoutJoueurBinding;
import com.example.tp2sifmarchane.databinding.ActivityMiseAjourBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MiseAJourActivity extends AppCompatActivity {

    EditText et_nom, et_age, et_position, et_nationalite;
    Button mise_a_jour;
    //ArrayList<utilisateur> getUtilisateur = new ArrayList<utilisateur>();
    FirebaseDatabase bd;
    utilisateur getUtilisateur;
    DatabaseReference ref;
    ActivityMiseAjourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMiseAjourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        et_nom = findViewById(R.id.etNomMiseAJour);
        et_age = findViewById(R.id.etAgeMiseAJour);
        et_position = findViewById(R.id.etPositionMiseAJour);
        et_nationalite = findViewById(R.id.etNationaliteMiseAJour);


        mise_a_jour = findViewById(R.id.btnMiseAJour);

        Intent intention = getIntent();
        Bundle b_rep = intention.getExtras();
        getUtilisateur = b_rep.getParcelable("liste");
        Log.i("Test", String.valueOf(getUtilisateur));

        et_nom.setText(String.valueOf(getUtilisateur.getNom()));
        et_age.setText(String.valueOf(getUtilisateur.getAge()));
        et_position.setText(String.valueOf(getUtilisateur.getPosition()));
        et_nationalite.setText(String.valueOf(getUtilisateur.getNationalite()));

        binding.btnSaveMiseAJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_nom.getText().toString().isEmpty() && !et_age.getText().toString().isEmpty() && !et_position.getText().toString().isEmpty()
                    && !et_nationalite.getText().toString().isEmpty()){

                    majDonnees(et_nom.getText().toString(), et_age.getText().toString(), et_position.getText().toString(), et_nationalite.getText().toString());

                }



            }
        });


    }
    private void majDonnees(String nom, String age, String position, String nationalite){
        HashMap usager = new HashMap();
        //binding = MiseAJourActivity.inflate(getLayoutInflater());
        usager.put("nom", nom);
        usager.put("age", age);
        usager.put("position", position);
        usager.put("nationalite", nationalite);
        ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");

        ref.child(nom).updateChildren(usager).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    binding.etNomMiseAJour.setText("");
                    binding.etAgeMiseAJour.setText("");
                    binding.etPositionMiseAJour.setText("");
                    binding.etNationaliteMiseAJour.setText("");
                    Toast.makeText(MiseAJourActivity.this, "Mis à jour", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MiseAJourActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}