package com.example.tp2sifmarchane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText tiet_courriel, tiet_mdp;
    Button btn_connexion, btn_creerCompte;
    FirebaseAuth bdAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bdAuth = FirebaseAuth.getInstance();
        tiet_courriel = findViewById(R.id.tiEditTextCourrielConnexion);
        tiet_mdp = findViewById(R.id.tiEditTextMdpConnexion);
        btn_connexion = findViewById(R.id.btnConnexion);
        btn_creerCompte = findViewById(R.id.btnCreerCompte);


        btn_creerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intention);
                finish();
            }
        });
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(courriel).matches() && mdp.length() >= 10){

                        bdAuth.signInWithEmailAndPassword(courriel, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Toast.makeText(LoginActivity.this, "Utilisateur créé", Toast.LENGTH_SHORT).show();
                                    FirebaseUser usager = bdAuth.getCurrentUser();


                                        Intent intention = new Intent(LoginActivity.this, ConnexionActivity.class);
                                        startActivity(intention);
                                        finish();


                                }else{
                                    Toast.makeText(LoginActivity.this, "Erreur d'authentification", Toast.LENGTH_SHORT).show();

                                }



                            }
                        });
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(courriel).matches()){
                        tiet_courriel.setError("Veuillez entrer un courriel valide");
                        tiet_courriel.requestFocus();
                    }else{
                        tiet_mdp.setError("Le mot de passe n'est pas valide");
                        tiet_mdp.requestFocus();
                    }
            }
        });

    }
}