package com.example.evaldo.firebase.activity.Administrador.Adaptadores;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.R;

import java.util.ArrayList;

public class PerguntasDetalhadasRecyclerAdapter extends RecyclerView.Adapter<PerguntasDetalhadasRecyclerAdapter.MeuViewHolder> {

    private ArrayList<PerguntasQuestionario> listPerguntasQuestAdapter;

    private Context context;
    private int posicao = 0;

    public PerguntasDetalhadasRecyclerAdapter(ArrayList<PerguntasQuestionario> listaPerguntas, Context c){
        context = c;
        listPerguntasQuestAdapter = listaPerguntas;
    }

    public PerguntasDetalhadasRecyclerAdapter(ArrayList<PerguntasQuestionario> listaPerguntas){
        listPerguntasQuestAdapter = listaPerguntas;
    }

    public PerguntasDetalhadasRecyclerAdapter.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_perguntas, viewGroup, false);

        return new PerguntasDetalhadasRecyclerAdapter.MeuViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull PerguntasDetalhadasRecyclerAdapter.MeuViewHolder meuViewHolder, int position) {
        meuViewHolder.tvKey.setText(listPerguntasQuestAdapter.get(position).getKey());
        meuViewHolder.tvHora.setText(listPerguntasQuestAdapter.get(position).getHora());
        meuViewHolder.tvPergunta1.setText(listPerguntasQuestAdapter.get(position).getPergunta1());
        meuViewHolder.tvResposta1.setText(listPerguntasQuestAdapter.get(position).getResposta1());
        meuViewHolder.tvPergunta2.setText(listPerguntasQuestAdapter.get(position).getPergunta2());
        meuViewHolder.tvResposta2.setText(listPerguntasQuestAdapter.get(position).getResposta2());
        meuViewHolder.tvPergunta3.setText(listPerguntasQuestAdapter.get(position).getPergunta3());
        meuViewHolder.tvResposta3.setText(listPerguntasQuestAdapter.get(position).getResposta3());
        meuViewHolder.tvPergunta4.setText(listPerguntasQuestAdapter.get(position).getPergunta4());
        meuViewHolder.tvResposta4.setText(listPerguntasQuestAdapter.get(position).getResposta4());
        meuViewHolder.tvPergunta5.setText(listPerguntasQuestAdapter.get(position).getPergunta5());
        meuViewHolder.tvResposta5.setText(listPerguntasQuestAdapter.get(position).getResposta5());
        meuViewHolder.tvPergunta6.setText(listPerguntasQuestAdapter.get(position).getPergunta6());
        meuViewHolder.tvResposta6.setText(listPerguntasQuestAdapter.get(position).getResposta6());
        meuViewHolder.tvPergunta7.setText(listPerguntasQuestAdapter.get(position).getPergunta7());
        meuViewHolder.tvResposta7.setText(listPerguntasQuestAdapter.get(position).getResposta7());
        meuViewHolder.tvPergunta8.setText(listPerguntasQuestAdapter.get(position).getPergunta8());
        meuViewHolder.tvResposta8.setText(listPerguntasQuestAdapter.get(position).getResposta8());
        meuViewHolder.tvPergunta9.setText(listPerguntasQuestAdapter.get(position).getPergunta9());
        meuViewHolder.tvResposta9.setText(listPerguntasQuestAdapter.get(position).getResposta9());
        meuViewHolder.tvPergunta10.setText(listPerguntasQuestAdapter.get(position).getPergunta10());
        meuViewHolder.tvResposta10.setText(listPerguntasQuestAdapter.get(position).getResposta10());


    }

    @Override
    public int getItemCount() {
        return listPerguntasQuestAdapter.size();
    }



    public class MeuViewHolder extends RecyclerView.ViewHolder{

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

        public MeuViewHolder(@NonNull View itemView) {
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
