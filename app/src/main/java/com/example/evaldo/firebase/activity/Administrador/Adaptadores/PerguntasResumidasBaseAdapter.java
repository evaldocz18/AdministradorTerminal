package com.example.evaldo.firebase.activity.Administrador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.R;

import java.util.List;

public class PerguntasResumidasBaseAdapter extends BaseAdapter {

    private Context context;
    private List<PerguntasQuestionario> listPerguntasQuestAdapter;

    public PerguntasResumidasBaseAdapter(Context context, List<PerguntasQuestionario> listPergQuestionarios) {
        this.context = context;
        this.listPerguntasQuestAdapter = listPergQuestionarios;
    }

    @Override
    public int getCount() {
        return listPerguntasQuestAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return listPerguntasQuestAdapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerguntasQuestionario perguntasQuestionario = listPerguntasQuestAdapter.get(position);

        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.base_adapter_listar_perguntas_resumidas, null);

        TextView tvNomeQuestionario = view.findViewById(R.id.tv_nomeQuestionario);
        tvNomeQuestionario.setText("Nome do Questionário: "+"\n" + "(" + perguntasQuestionario.getNomeQuestionario()+ ")");

        TextView tvAdministradorResponsavel = view.findViewById(R.id.tv_administradorResponsavel);
        tvAdministradorResponsavel.setText("Responsável: " + perguntasQuestionario.getNomeFuncionario());

        TextView tvQtdPerguntas = view.findViewById(R.id.tv_qtdPerguntas);
        tvQtdPerguntas.setText("Perguntas Cadastradas = " +perguntasQuestionario.getContPerguntas());





        if(position % 2 == 0){

            view.findViewById(R.id.layoutInterno).
                    setBackgroundColor(view.getResources().getColor(R.color.branco_claro));
        }else{
            view.findViewById(R.id.layoutExterno).
                    setBackgroundColor(view.getResources().getColor(R.color.branco_cinza));
        }


        return view;
    }
}


