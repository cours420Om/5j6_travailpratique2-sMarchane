package com.example.tp2sifmarchane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }
    public String getUtilisateurToken(){
        SharedPreferences prefs = getSharedPreferences("UtilisateurToken", MODE_PRIVATE);
        return "SuperTokenIdentificationUtilisateur";
    }


}