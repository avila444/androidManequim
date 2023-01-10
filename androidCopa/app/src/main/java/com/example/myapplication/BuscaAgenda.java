package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class BuscaAgenda extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);

        RecyclerView recyclerView = findViewById(R.id.lista);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String valor = extras.getString("valor");

            List<Registro> registros = sqlHelper.getInstance(this).getRegistro(valor);
            //-----
            ListaValores adapter = new ListaValores(registros);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    private class ListaValores extends RecyclerView.Adapter<ListaValores.ListaValoresViewHolder> {

        private List<Registro> dados;
        //----

        public ListaValores(List<Registro> dados) {
            this.dados = dados;
        }
        @NonNull
        @Override
        public ListaValoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){
            return new ListaValoresViewHolder(getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListaValoresViewHolder holder, int position) {
            Registro dado = dados.get(position);
            holder.bind(dado);
        }
        @Override
        public int getItemCount() {return dados.size();}
        private class ListaValoresViewHolder extends RecyclerView.ViewHolder{
            public ListaValoresViewHolder(@NonNull View itemView) {super(itemView);}
            public void bind(Registro dado) {
                ((TextView) itemView).setText("Campeao: " + dado.campeao);
                ((TextView) itemView).setText("Campeao: " + dado.segundo);
                ((TextView) itemView).setText("Campeao: " + dado.terceiro);

            }
        }

    }

}