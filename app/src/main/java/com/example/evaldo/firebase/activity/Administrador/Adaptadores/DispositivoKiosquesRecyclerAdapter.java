package com.example.evaldo.firebase.activity.Administrador.Adaptadores;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.DispositivoKiosque;

import java.util.ArrayList;

public class DispositivoKiosquesRecyclerAdapter extends RecyclerView.Adapter<DispositivoKiosquesRecyclerAdapter.MeuViewHolderListarKiosques> {

    public ArrayList<DispositivoKiosque> listaKiosque;

    private Context context;
    private int posicao = 0;

    public DispositivoKiosquesRecyclerAdapter(ArrayList<DispositivoKiosque> listKiosque, Context c){
        context = c;
        listaKiosque = listKiosque;
    }

    public DispositivoKiosquesRecyclerAdapter(ArrayList<DispositivoKiosque> listKiosque){
        listaKiosque = listKiosque;
    }

    @NonNull
    @Override
    public MeuViewHolderListarKiosques onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_kiosque, viewGroup, false);

        return new MeuViewHolderListarKiosques(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolderListarKiosques meuViewHolderListarKiosques, int position) {
        meuViewHolderListarKiosques.tvNomeKiosque.setText("Nome do Dispositivo = " + listaKiosque.get(position).getNomeDispositivo());
        meuViewHolderListarKiosques.tvID.setText("ID = " + listaKiosque.get(position).getIdDispositivo());
        meuViewHolderListarKiosques.tvQuestionarioAtual.setText("Questionário Atual = " + listaKiosque.get(position).getQuestionarioAtual());
        meuViewHolderListarKiosques.dataAtivacao.setText("Data da Ativação = " + listaKiosque.get(position).getDataAtivacao());
        meuViewHolderListarKiosques.tvStatus.setText("Status do Dispositivo = " + listaKiosque.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return listaKiosque.size();
    }

    public class MeuViewHolderListarKiosques extends RecyclerView.ViewHolder{
        public TextView tvID;
        public TextView tvNomeKiosque;
        public TextView tvStatus;
        public TextView tvQuestionarioAtual;
        public TextView dataAtivacao;

        public MeuViewHolderListarKiosques(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_kiosque_id);
            tvNomeKiosque = itemView.findViewById(R.id.tv_kiosque_nomekiosque);
            tvStatus = itemView.findViewById(R.id.tv_kiosque_status);
            tvQuestionarioAtual = itemView.findViewById(R.id.tv_kiosque_QuestionarioAtual);
            dataAtivacao = itemView.findViewById(R.id.tv_kiosque_dataAtivacao);

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
