package com.example.tp2sifmarchane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

public class MainActivity extends AppCompatActivity {

    TextInputEditText ti_courriel, ti_mdp, ti_confirmation;
    Button btn_inscription, btn_connexion;
    Dialog bteDialog;

    FirebaseAuth bdAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ti_courriel = findViewById(R.id.tiEditTextCourriel);
        ti_mdp = findViewById(R.id.tiEditTextMdp);
        ti_confirmation = findViewById(R.id.tiEditTextMdpConfirmation);
        btn_inscription = findViewById(R.id.btnInscription);
        btn_connexion = findViewById(R.id.btnConnexionAVotreCompte);
        bdAuth = FirebaseAuth.getInstance();


        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intention = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intention);
                finish();
            }
        });


        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courriel = ti_courriel.getText().toString();
                String mdp = ti_mdp.getText().toString();
                String confirmation = ti_confirmation.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(courriel).matches()){
                    if (mdp.matches(confirmation) && mdp.length() >= 10){
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                        bdAuth.createUserWithEmailAndPassword(courriel, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(MainActivity.this, "Utilisateur créé", Toast.LENGTH_SHORT).show();
                                            FirebaseUser usager = bdAuth.getCurrentUser();

                                            if(usager != null){
                                                Intent intention = new Intent(MainActivity.this, LoginActivity.class);
                                                startActivity(intention);
                                                finish();
                                            }

                                        }else{
                                            Toast.makeText(MainActivity.this, "Erreur d'authentification", Toast.LENGTH_SHORT).show();

                                        }



                                    }
                                });
                    }else if(mdp.length() < 10){
                        ti_mdp.setError("Le mot de passe est trop court");
                        ti_mdp.requestFocus();
                    }else{
                        ti_confirmation.setError("Les mots de passe sont différent");
                        ti_confirmation.requestFocus();
                    }
                }else{
                    ti_courriel.setError("Entrer un courriel valide");
                    ti_courriel.requestFocus();
                }
            }
        });
    }

}