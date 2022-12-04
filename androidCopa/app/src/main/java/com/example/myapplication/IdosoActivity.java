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

public class IdosoActivity extends AppCompatActivity {

    private TimePicker time_picker;
    private Button tempo_remedio;
    private Integer hora;
    private Integer minuto;
    private EditText remedioInformado;
    private TextView resultado1;
    private TextView resultado2;
    private TextView resultado3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idoso);

        time_picker = findViewById(R.id.TimePicker);
        tempo_remedio = findViewById(R.id.tempoRemedio);
        time_picker.setIs24HourView(true);
        remedioInformado = findViewById(R.id.remedioInformado);
        resultado1 = findViewById(R.id.resultado1);
        resultado2 = findViewById(R.id.resultado2);
        resultado3 = findViewById(R.id.resultado3);


        tempo_remedio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                hora = time_picker.getCurrentHour();
                minuto = time_picker.getCurrentMinute();
                String medicamento = remedioInformado.getText().toString();
                String horario = String.valueOf(hora) + ":" + String.valueOf(minuto);

                if (medicamento.isEmpty()) {
                    Toast.makeText(IdosoActivity.this, R.string.embranco, Toast.LENGTH_LONG).show();
                    return;
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(IdosoActivity.this);
                dialog.setTitle("Remédio: " + medicamento);
                dialog.setMessage("Horário: " + hora + ":" + minuto);
                dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imp.hideSoftInputFromWindow(remedioInformado.getWindowToken(), 0);
                        imp.hideSoftInputFromWindow(tempo_remedio.getWindowToken(), 0);
                    }
                });
                dialog.create();
                dialog.show();

                if (resultado1.getText().toString().isEmpty()){
                    resultado1.setText("Agendamento \n Remédio: " + medicamento + "\n Horário: " + horario);
                }else if (resultado2.getText().toString().isEmpty()){
                    resultado2.setText("Agendamento \n Remédio: " + medicamento + "\n Horário: " + horario);
                }else if (resultado3.getText().toString().isEmpty()){
                    resultado3.setText("Agendamento \n Remédio: " + medicamento + "\n Horário: " + horario);
                }


            }
        });

    }
}