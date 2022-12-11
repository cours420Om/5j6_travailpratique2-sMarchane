package com.example.tp2sifmarchane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ConnexionActivity extends AppCompatActivity {

    Button btn_deconnexion;
    Button btn_gestionProfil;
    Button btn_app;
    FirebaseAuth bdAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        bdAuth = FirebaseAuth.getInstance();
        btn_deconnexion = findViewById(R.id.btnDeconnexion);
        btn_gestionProfil = findViewById(R.id.btnGestionProfil);
        btn_app = findViewById(R.id.btnApplication);

        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdAuth.signOut();
                Intent intention = new Intent(ConnexionActivity.this, LoginActivity.class);
                startActivity(intention);
                finish();
            }
        });
        btn_gestionProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intention = new Intent(ConnexionActivity.this, GestionProfilActivity.class);
                startActivity(intention);
                finish();

            }
        });
        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intention = new Intent(ConnexionActivity.this, SoccerActivity.class);
                startActivity(intention);
                finish();
            }
        });
    }
}