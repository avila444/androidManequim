package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EstudoActivity extends AppCompatActivity {

    private Button bot_TempoEstudo;
    private EditText edit_TempoInformado;
    private EditText edit_discipçinaInformada;
    private TextView text_resultado;
    private TimePicker time_Picker;
    private Integer hora;
    private Integer minuto;
    private Integer tempoEstudo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudo);

        bot_TempoEstudo = findViewById(R.id.bot_TempoEstudo);
        time_Picker = findViewById(R.id.TimePicker);
        edit_TempoInformado = findViewById(R.id.editTempoInformado);
        edit_discipçinaInformada = findViewById(R.id.editdisciplinaInformada);
        text_resultado = findViewById(R.id.resultado);
        time_Picker.setIs24HourView(true);

        bot_TempoEstudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempo = edit_TempoInformado.getText().toString();
                String disciplina = edit_discipçinaInformada.getText().toString();

                if (tempo.isEmpty() || disciplina.isEmpty()) {
                    Toast.makeText(EstudoActivity.this, R.string.embranco, Toast.LENGTH_LONG).show();
                    return;
                }
                hora = time_Picker.getCurrentHour();
                minuto = time_Picker.getCurrentMinute();
                tempoEstudo = Integer.parseInt(tempo);
                text_resultado.setText("Agendamento: \n Disciplina: " + disciplina + "\n Tempo: " + tempo + "m" + "a partir das " + hora + ":" + minuto + "hs");

                AlertDialog.Builder dialog = new AlertDialog.Builder(EstudoActivity.this);
                dialog.setTitle(getString(R.string.popup1_1, disciplina));
                dialog.setMessage(getString(R.string.popup1_2, tempoEstudo, hora, minuto));
                dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imp.hideSoftInputFromWindow(edit_discipçinaInformada.getWindowToken(), 0);
                        imp.hideSoftInputFromWindow(edit_TempoInformado.getWindowToken(), 0);
                    }


                });
                dialog.create();
                dialog.show();
            }
        });
    }
}