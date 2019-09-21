package com.example.evaldo.firebase.activity.Administrador.Adaptadores;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaldo.firebase.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ResultadosQuantitativosAdapter extends RecyclerView.Adapter<ResultadosQuantitativosAdapter.ViewHolder> {

    private HashMap<String, HashMap<String, Integer>> perguntas;
    private List<String> perguntaText;


    public ResultadosQuantitativosAdapter() {
        perguntaText = new ArrayList<>();
    }

    public ResultadosQuantitativosAdapter(HashMap<String, HashMap<String, Integer>> perguntas) {

        update(perguntas);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView perguntaView;
        private TextView respostasView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            perguntaView = itemView.findViewById(R.id.perguntaView);
            respostasView = itemView.findViewById(R.id.respostaView);
        }
    }

    public void update(HashMap<String, HashMap<String, Integer>> perguntas){
        this.perguntas = perguntas;
        perguntaText = new ArrayList<>(perguntas.keySet());

        Collections.sort(perguntaText, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i1 = Integer.parseInt(o1.split("\\.")[0]);
                int i2 = Integer.parseInt(o2.split("\\.")[0]);

                if(i1 < i2) return -1;
                else if (i1 == i2) return 0;
                else return 1;
            }
        });

        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_resultados_quantitativos, viewGroup, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.perguntaView.setText(perguntaText.get(i));

        StringBuilder respostas = new StringBuilder();

        HashMap<String, Integer> resposta = perguntas.get(perguntaText.get(i));

        for (String key : resposta.keySet()) {
            respostas.append(key).append(" ").append(resposta.get(key)).append("\n");
        }

        viewHolder.respostasView.setText(respostas.toString());

    }

    @Override
    public int getItemCount() {
        return perguntaText.size();
    }
}
