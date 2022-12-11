package com.example.tp2sifmarchane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoccerActivity extends AppCompatActivity {

    Button btn_Ajout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer);

        btn_Ajout = findViewById(R.id.btnAjouterJoueur);


        btn_Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(SoccerActivity.this, AjoutJoueurActivity.class);
                startActivity(intention);
                finish();

            }
        });
    }
}