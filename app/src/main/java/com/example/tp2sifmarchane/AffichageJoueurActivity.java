package com.example.tp2sifmarchane;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp2sifmarchane.databinding.ActivityAffichageJoueurBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AffichageJoueurActivity extends AppCompatActivity {

    private TextView tv_res;
    private ListView lv_affichage;
    Button btn_miseAJour, btn_supprimer;
    private ArrayList<utilisateur> listUtilisateur;
    DatabaseReference reference;

    ActivityAffichageJoueurBinding binding;
    utilisateur getListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAffichageJoueurBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lv_affichage = findViewById(R.id.lvJoueur);
        tv_res = findViewById(R.id.tvAffichage);
        btn_miseAJour = findViewById(R.id.btnMiseAJour);
        btn_supprimer = findViewById(R.id.btnSupprimer);


        listUtilisateur = new ArrayList<utilisateur>();
        initialisationListe();
        lv_affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getListe = listUtilisateur.get(i);
                lectureDonnees(getListe.getNom());
                //String selectedItem = String.valueOf(adapter.getItem(i));


            }
        });




        btn_miseAJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(AffichageJoueurActivity.this, MiseAJourActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("liste", getListe);
                intention.putExtras(b);
                startActivity(intention);
                finish();



            }
        });
        btn_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                effacerDonnees(getListe.toString());


            }
        });

    }
    private void initialisationListe(){
        final ArrayAdapter<utilisateur> adapter = new ArrayAdapter<utilisateur>(this, android.R.layout.simple_list_item_1, listUtilisateur);
        reference = FirebaseDatabase.getInstance().getReference("Utilisateurs");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listUtilisateur.add(snapshot.getValue(utilisateur.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                listUtilisateur.remove(snapshot.getValue(utilisateur.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lv_affichage.setAdapter(adapter);


    }
    private void lectureDonnees(String usager){
        reference = FirebaseDatabase.getInstance().getReference("Utilisateurs");
        reference.child(usager).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot data = task.getResult();
                        String age = String.valueOf(data.child("age").getValue());
                        String position = String.valueOf(data.child("position").getValue());
                        String nationalite = String.valueOf(data.child("nationalite").getValue());
                        binding.tvAffichage.setText(age + " " + position + " " + nationalite);
                    }
                }

            }
        });

    }

    private void effacerDonnees(String usager){
        reference = FirebaseDatabase.getInstance().getReference("Utilisateurs");
        reference.child(usager).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AffichageJoueurActivity.this, "Supprimé",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}