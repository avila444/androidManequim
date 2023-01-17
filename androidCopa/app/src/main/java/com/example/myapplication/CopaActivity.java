package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CopaActivity extends AppCompatActivity {

    private View botaoShowdb;
    private Button bt_enviar;
    private EditText edit_campeao;
    private EditText edit_segundo;
    private EditText edit_terceiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);

        botaoShowdb = findViewById(R.id.botaoShowdb);
        bt_enviar = findViewById(R.id.bt_enviar);

        //Evento de Click no botão Enviar
        bt_enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                edit_campeao = findViewById(R.id.edit_campeao);
                edit_segundo = findViewById(R.id.edit_segundo);
                edit_terceiro = findViewById(R.id.edit_terceiro);

                String campeao = edit_campeao.getText().toString();
                String segundo = edit_segundo.getText().toString();
                String terceiro = edit_terceiro.getText().toString();

                if (campeao.isEmpty() || segundo.isEmpty() || terceiro.isEmpty()){
                    Toast.makeText(CopaActivity.this, R.string.embranco, Toast.LENGTH_LONG).show();
                    return;
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(CopaActivity.this);
                dialog.setTitle("Seu Palpite: ");
                dialog.setMessage("O seu palpite foi:\nCampeão:  " + campeao + "\nSegundo Colocado:" + segundo + "\nTerceiro Colocado: " + terceiro);

                //Alerta Salvar
                dialog.setPositiveButton(R.string.salvar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        long id_table = sqlHelper.getInstance(CopaActivity.this).addAgendamento(campeao, segundo, terceiro);
                        if (id_table>0)
                            Toast.makeText(CopaActivity.this,R.string.salvo,Toast.LENGTH_LONG).show();

                    }
                });

                //Alerta Cancelar
                dialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imp.hideSoftInputFromWindow(edit_campeao.getWindowToken(), 0);
                        imp.hideSoftInputFromWindow(edit_segundo.getWindowToken(), 0);
                        imp.hideSoftInputFromWindow(edit_terceiro.getWindowToken(), 0);
                    }
                });

                dialog.create();
                dialog.show();

            }
        });

        //Click no botao mostrar banco
        botaoShowdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CopaActivity.this, ListaActivity.class);
                intent.putExtra("valor", "Português");
                startActivity(intent);
            }
        });

    }
}