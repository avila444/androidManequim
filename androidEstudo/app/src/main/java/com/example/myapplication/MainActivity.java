package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View botao_inicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao_inicial = findViewById(R.id.botaoinicial);

        //Evento de click no linear layout
        botao_inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cria a intenção de abrir a nova tela
                Intent intent = new Intent(MainActivity.this, EstudoActivity.class);
                startActivity(intent);
            }
        });
    }
}