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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtLogin;
    private TextView txtSenha;
    private Button btSalvar;
    private Button btSair;
    private EditText editLogin;
    private EditText editSenha;
    private String mostraLogin;
    private String mostraSenha;

    private String BmostraLogin;
    private String BmostraSenha;
    private Boolean BbtSalvar;

    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = findViewById(R.id.txt1);
        txtSenha= findViewById(R.id.txt2);
        btSalvar = findViewById(R.id.bt1);
        btSair = findViewById(R.id.bt2);
        btSalvar.setText(R.string.txtsalvar);
        btSair.setText(R.string.txtsair);
        txtLogin.setText(R.string.txtlogin);
        txtSenha.setText(R.string.txtsenha);

        editLogin = findViewById(R.id.campologin);
        editSenha = findViewById(R.id.camposenha);

        preferencias = getSharedPreferences("banco", Context.MODE_PRIVATE);

        BmostraLogin = preferencias.getString("Login","");
        BmostraSenha = preferencias.getString("Senha","");
        BbtSalvar = preferencias.getBoolean("bt",false);

        editLogin.setText(BmostraLogin);
        editSenha.setText(BmostraSenha);
        btSalvar.setText(R.string.txtsalvar);
        int cord = ContextCompat.getColor(this, R.color.btdfb);
        btSalvar.setBackgroundColor(cord);
        int cortextod = ContextCompat.getColor(this, R.color.btdtt);
        btSalvar.setTextColor(cortextod);

        if(BbtSalvar){
            btSalvar.setText("Logado");
            int cor = ContextCompat.getColor(this, R.color.amaruuuun);
            btSalvar.setBackgroundColor(cor);
            int cortexto = ContextCompat.getColor(this, R.color.white);
            btSalvar.setTextColor(cortexto);
            return;
        }
    }

    public void notificaSair(View view){
        editLogin.setText("");
        editSenha.setText("");

        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove("login");
        editor.remove("senha");
        editor.putBoolean("bt", false);
        editor.apply();
    }

    public void notificaClick(View view){
        mostraLogin = editLogin.getText().toString();
        mostraSenha = editSenha.getText().toString();

        Log.d("Teste", "Login: " + mostraLogin + "Senha: " + mostraSenha);

        if(mostraLogin.isEmpty() || mostraSenha.isEmpty()){
            Toast.makeText(this, R.string.erro, Toast.LENGTH_LONG).show();

            return;
        }
        int resp=confereSenha(mostraSenha);
        Toast.makeText(this, resp, Toast.LENGTH_LONG).show();
    }
    public int confereSenha(String senha){
        if (senha.length() < 4){
            return R.string.senhaCurta;
        }
        else if (senha.length() > 4){
            return R.string.senhaLonga;
        }
        else{

            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("Login", mostraLogin);
            editor.putString("Senha", mostraSenha);
            editor.putBoolean("bt", true);
            editor.apply();

            return R.string.senhaOK;
        }
    }

}
