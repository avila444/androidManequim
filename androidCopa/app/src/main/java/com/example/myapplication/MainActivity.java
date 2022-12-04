package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View botaoEstudo; // o linear layout é uma view
    private View botaoRemedio;
    private View botaoCopa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEstudo = findViewById(R.id.botaoEstudo);
        botaoRemedio = findViewById(R.id.botaoRemedio);
        botaoCopa = findViewById(R.id.botaoCopa);


        //evento de click no linearlayout
        botaoEstudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EstudoActivity.class);
                startActivity(intent); //abre a nova tela
            }
        });

        //evento de click no linearlayout
        botaoRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IdosoActivity.class);
                startActivity(intent); //abre a nova tela
            }
        });

        //evento de click no linearlayout
        botaoCopa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CopaActivity.class);
                startActivity(intent); //abre a nova tela
            }
        });

    }
}