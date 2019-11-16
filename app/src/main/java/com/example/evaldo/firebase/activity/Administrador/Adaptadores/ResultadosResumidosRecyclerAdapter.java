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
import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;

import java.util.ArrayList;

public class ResultadosResumidosRecyclerAdapter extends RecyclerView.Adapter<ResultadosResumidosRecyclerAdapter.MeuViewHolderResultadosResumidos> {

    private ArrayList<PerguntasQuestionario> listRespResumidosAdapter;

    private Context context;

    private int posicao = 0;


    public ResultadosResumidosRecyclerAdapter(ArrayList<PerguntasQuestionario> listaRespostas, Context c){

        context = c;
        listRespResumidosAdapter = listaRespostas;
    }

    public ResultadosResumidosRecyclerAdapter(ArrayList<PerguntasQuestionario> listaRespostas){
        listRespResumidosAdapter = listaRespostas;
    }

    @NonNull
    @Override
    public MeuViewHolderResultadosResumidos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_resultados_resumidos, viewGroup, false);

        return new MeuViewHolderResultadosResumidos(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolderResultadosResumidos meuViewHolderResultadosResumidos, int position) {

        //System.out.println("public void onBindViewHolder(@NonNull MeuViewHolderResultadosDetalhados meuViewHolderResultadosDetalhados, int " + position +"+)");
        meuViewHolderResultadosResumidos.tvResultadosResumidosNomeQuestionario.setText("Administrador Resposável: " + listRespResumidosAdapter.get(position).getNomeFuncionario());
        meuViewHolderResultadosResumidos.tvResultadosResumidosAdministradorResponsavel.setText("Nome do Questionário: " + listRespResumidosAdapter.get(position).getNomeQuestionario());





    }

    @Override
    public int getItemCount() {
        //System.out.println("ResultadosResumidosRecyclerAdapter getItemCount() = " + listRespResumidosAdapter.size());
        return listRespResumidosAdapter.size();
    }



    public class MeuViewHolderResultadosResumidos extends RecyclerView.ViewHolder{

        public TextView tvResultadosResumidosNomeQuestionario;
        public TextView tvResultadosResumidosAdministradorResponsavel;
        public TextView tvResultadosResumidosKey;

        public MeuViewHolderResultadosResumidos(@NonNull View itemView) {
            super(itemView);

            tvResultadosResumidosNomeQuestionario = itemView.findViewById(R.id.tv_exibirResultadosResumidos_nomeQuestionario);
            tvResultadosResumidosAdministradorResponsavel = itemView.findViewById(R.id.tv_exibirResultadosResumidos_administradorResponsavel);

            if (posicao % 2 == 0){
                itemView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                posicao++;
            }else{
                itemView.setBackgroundColor(Color.parseColor("#FFFAFA"));
                posicao++;
            }





        }
    }


}
