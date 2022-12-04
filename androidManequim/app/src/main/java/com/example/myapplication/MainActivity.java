package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText campoPeso;
    private EditText campoAltura;
    private Button botaoEnviar;
    private EditText editPeso;
    private EditText editAltura;

    private SharedPreferences preferencias;
    private String BmostraPeso;
    private String BmostraAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoPeso = findViewById(R.id.campoPeso);
        campoAltura = findViewById(R.id.campoAltura);
        botaoEnviar = findViewById(R.id.botaoEnviar);

        editPeso = findViewById(R.id.campoPeso);
        editAltura = findViewById(R.id.campoAltura);

        preferencias = getSharedPreferences("banco", Context.MODE_PRIVATE);

        BmostraPeso = preferencias.getString("Peso","");
        BmostraAltura = preferencias.getString("Altura","");

        editPeso.setText(BmostraPeso);
        editAltura.setText(BmostraAltura);
    }

    public void clickBotao(View view){
        String mostraPeso = editPeso.getText().toString();
        String mostraAltura = editAltura.getText().toString();

        Log.d("Teste de Manequim", "Peso" + mostraPeso + "Altura" + mostraAltura);

        if (mostraPeso.isEmpty() || mostraAltura.isEmpty()) {
            Toast.makeText(this, R.string.erroVazio, Toast.LENGTH_LONG).show();
            return;
        }
        int resp=confereTudo(mostraPeso, mostraAltura);
        Toast.makeText(this, resp, Toast.LENGTH_LONG).show();

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Peso", mostraPeso);
        editor.putString("Altura", mostraAltura);
        editor.apply();
    }

    public void notificaLimpar(View view){
        editPeso.setText("");
        editAltura.setText("");

        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove("Peso");
        editor.remove("Altura");
        editor.apply();

        if(BmostraPeso == null && BmostraAltura == null){
            botaoEnviar.setText("Ver seu tamanho");
            int bgcolor = ContextCompat.getColor(this, R.color.purple_200);
            int textcolor = ContextCompat.getColor(this, R.color.white);

            botaoEnviar.setBackgroundColor(bgcolor);
            botaoEnviar.setTextColor(textcolor);
        }
        else{
            botaoEnviar.setText("Manequim Salvo");
            int bgcolor = ContextCompat.getColor(this, R.color.black);
            int textcolor = ContextCompat.getColor(this, R.color.white);

            botaoEnviar.setBackgroundColor(bgcolor);
            botaoEnviar.setTextColor(textcolor);
        }

    }

    public int confereTudo(String peso, String altura){

        if(BmostraPeso == null && BmostraAltura == null){
            botaoEnviar.setText("Ver seu tamanho");
            int bgcolor = ContextCompat.getColor(this, R.color.purple_200);
            int textcolor = ContextCompat.getColor(this, R.color.white);

            botaoEnviar.setBackgroundColor(bgcolor);
            botaoEnviar.setTextColor(textcolor);
        }
        else{
            botaoEnviar.setText("Manequim Salvo");
            int bgcolor = ContextCompat.getColor(this, R.color.black);
            int textcolor = ContextCompat.getColor(this, R.color.white);

            botaoEnviar.setBackgroundColor(bgcolor);
            botaoEnviar.setTextColor(textcolor);
        }

        int intPeso = Integer.parseInt(peso);
        int intAltura = Integer.parseInt(altura) / 100;
        float imc = intPeso/(intAltura*intAltura);
        if(imc<=18){
            return R.string.p;
        }if(imc>18 && imc <=35){
            return R.string.m;
        }if(imc>25 && imc<=30 ){
            return R.string.g;
        }else{
            return R.string.gg;
        }
    }
}

