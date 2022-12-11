package com.example.tp2sifmarchane;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    private ArrayList<utilisateur> listUtilisateur;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_joueur);

        lv_affichage = findViewById(R.id.lvJoueur);
        tv_res = findViewById(R.id.tvAffichage);



        listUtilisateur = new ArrayList<utilisateur>();
        initialisationListe();


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

        lv_affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedItem = String.valueOf(adapter.getItem(i));

                //tv_res.setText(selectedItem);
                reference.child(selectedItem).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        utilisateur additionalData = snapshot.getValue(utilisateur.class);
                        tv_res.setText(String.valueOf(additionalData));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                    }
                });

            }
        });

    }
}