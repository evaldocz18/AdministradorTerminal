package com.example.evaldo.firebase.activity.Administrador.Adaptadores;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosQuestionario;
import com.example.evaldo.firebase.R;

import java.util.ArrayList;

public class ResultadosDetalhadasRecyclerAdapter extends RecyclerView.Adapter<ResultadosDetalhadasRecyclerAdapter.MeuViewHolderResultadosDetalhados> {

    private ArrayList<ResultadosQuestionario> listQuestAdapter;

    private Context context;
    private int posicao = 0;

    public ResultadosDetalhadasRecyclerAdapter(ArrayList<ResultadosQuestionario> listaRespostas, Context c){
        context = c;
        listQuestAdapter = listaRespostas;
    }

    public ResultadosDetalhadasRecyclerAdapter(ArrayList<ResultadosQuestionario> listaRespostas){
        listQuestAdapter = listaRespostas;
    }

    @NonNull
    @Override
    public MeuViewHolderResultadosDetalhados onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_resultados_detalhados, viewGroup, false);

        return new MeuViewHolderResultadosDetalhados(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolderResultadosDetalhados meuViewHolderResultadosDetalhados, int position) {

        //System.out.println("public void onBindViewHolder(@NonNull MeuViewHolderResultadosDetalhados meuViewHolderResultadosDetalhados, int " + position +"+)");
//        meuViewHolderResultadosDetalhados.tvKey.setText(listQuestAdapter.get(position).getKey());
        meuViewHolderResultadosDetalhados.tvHora.setText(listQuestAdapter.get(position).getHora());
        meuViewHolderResultadosDetalhados.tvPergunta1.setText(listQuestAdapter.get(position).getPergunta1());
        meuViewHolderResultadosDetalhados.tvResposta1.setText(listQuestAdapter.get(position).getResposta1());
        meuViewHolderResultadosDetalhados.tvPergunta2.setText(listQuestAdapter.get(position).getPergunta2());
        meuViewHolderResultadosDetalhados.tvResposta2.setText(listQuestAdapter.get(position).getResposta2());
        meuViewHolderResultadosDetalhados.tvPergunta3.setText(listQuestAdapter.get(position).getPergunta3());
        meuViewHolderResultadosDetalhados.tvResposta3.setText(listQuestAdapter.get(position).getResposta3());
        meuViewHolderResultadosDetalhados.tvPergunta4.setText(listQuestAdapter.get(position).getPergunta4());
        meuViewHolderResultadosDetalhados.tvResposta4.setText(listQuestAdapter.get(position).getResposta4());
        meuViewHolderResultadosDetalhados.tvPergunta5.setText(listQuestAdapter.get(position).getPergunta5());
        meuViewHolderResultadosDetalhados.tvResposta5.setText(listQuestAdapter.get(position).getResposta5());
        meuViewHolderResultadosDetalhados.tvPergunta6.setText(listQuestAdapter.get(position).getPergunta6());
        meuViewHolderResultadosDetalhados.tvResposta6.setText(listQuestAdapter.get(position).getResposta6());
        meuViewHolderResultadosDetalhados.tvPergunta7.setText(listQuestAdapter.get(position).getPergunta7());
        meuViewHolderResultadosDetalhados.tvResposta7.setText(listQuestAdapter.get(position).getResposta7());
        meuViewHolderResultadosDetalhados.tvPergunta8.setText(listQuestAdapter.get(position).getPergunta8());
        meuViewHolderResultadosDetalhados.tvResposta8.setText(listQuestAdapter.get(position).getResposta8());
        meuViewHolderResultadosDetalhados.tvPergunta9.setText(listQuestAdapter.get(position).getPergunta9());
        meuViewHolderResultadosDetalhados.tvResposta9.setText(listQuestAdapter.get(position).getResposta9());
        meuViewHolderResultadosDetalhados.tvPergunta10.setText(listQuestAdapter.get(position).getPergunta10());
        meuViewHolderResultadosDetalhados.tvResposta10.setText(listQuestAdapter.get(position).getResposta10());


    }

    @Override
    public int getItemCount() {
        return listQuestAdapter.size();
    }



    public class MeuViewHolderResultadosDetalhados extends RecyclerView.ViewHolder{

        public TextView tvKey;
        public TextView tvHora;
        public TextView tvPergunta1;
        public TextView tvResposta1;
        public TextView tvPergunta2;
        public TextView tvResposta2;
        public TextView tvPergunta3;
        public TextView tvResposta3;
        public TextView tvPergunta4;
        public TextView tvResposta4;
        public TextView tvPergunta5;
        public TextView tvResposta5;
        public TextView tvPergunta6;
        public TextView tvResposta6;
        public TextView tvPergunta7;
        public TextView tvResposta7;
        public TextView tvPergunta8;
        public TextView tvResposta8;
        public TextView tvPergunta9;
        public TextView tvResposta9;
        public TextView tvPergunta10;
        public TextView tvResposta10;

        public MeuViewHolderResultadosDetalhados(@NonNull View itemView) {
            super(itemView);

            tvKey = itemView.findViewById(R.id.tv_key);
            tvHora = itemView.findViewById(R.id.tv_hora);
            tvPergunta1 = itemView.findViewById(R.id.tv_pergunta1);
            tvResposta1 = itemView.findViewById(R.id.tv_resposta1);
            tvPergunta2 = itemView.findViewById(R.id.tv_pergunta2);
            tvResposta2 = itemView.findViewById(R.id.tv_resposta2);
            tvPergunta3 = itemView.findViewById(R.id.tv_pergunta3);
            tvResposta3 = itemView.findViewById(R.id.tv_resposta3);
            tvPergunta4 = itemView.findViewById(R.id.tv_pergunta4);
            tvResposta4 = itemView.findViewById(R.id.tv_resposta4);
            tvPergunta5 = itemView.findViewById(R.id.tv_pergunta5);
            tvResposta5 = itemView.findViewById(R.id.tv_resposta5);
            tvPergunta6 = itemView.findViewById(R.id.tv_pergunta6);
            tvResposta6 = itemView.findViewById(R.id.tv_resposta6);
            tvPergunta7 = itemView.findViewById(R.id.tv_pergunta7);
            tvResposta7 = itemView.findViewById(R.id.tv_resposta7);
            tvPergunta8 = itemView.findViewById(R.id.tv_pergunta8);
            tvResposta8 = itemView.findViewById(R.id.tv_resposta8);
            tvPergunta9 = itemView.findViewById(R.id.tv_pergunta9);
            tvResposta9 = itemView.findViewById(R.id.tv_resposta9);
            tvPergunta10 = itemView.findViewById(R.id.tv_pergunta10);
            tvResposta10 = itemView.findViewById(R.id.tv_resposta10);

            if (posicao % 2 == 0){
                itemView.setBackgroundColor(Color.parseColor("#BEBEBE"));
                posicao++;
            }else{
                itemView.setBackgroundColor(Color.parseColor("#FFFAFA"));
                posicao++;
            }

        }
    }


}
