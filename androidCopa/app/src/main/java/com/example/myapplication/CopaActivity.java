package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CopaActivity extends AppCompatActivity {

    private View botaoShowdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);

        botaoShowdb = findViewById(R.id.botaoShowdb);

        /* Onclick para abrir a tela com os dados salvos no banco de dados

        botaoShowdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CopaActivity.this, EstudoActivity.class);
                startActivity(intent); //Abre a Estudo Activity
            }
        });
         */
    }
}