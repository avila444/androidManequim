package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View botaoInicial; // o linear layout é uma view
    private View botaoIdoso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoInicial = findViewById(R.id.botaoinicial);
        botaoIdoso = findViewById(R.id.botaoidoso);


        //evento de click no linearlayout
        botaoInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EstudoActivity.class);
                startActivity(intent); //abre a nova tela
            }
        });

        //evento de click no linearlayout
        botaoIdoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IdosoActivity.class);
                startActivity(intent); //abre a nova tela
            }
        });

    }
}