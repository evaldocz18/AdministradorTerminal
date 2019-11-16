package com.example.evaldo.firebase.activity.Administrador.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosOuvidoria;

import java.util.ArrayList;

public class ResultadosDetalhadosOuvidoriaAdapter extends RecyclerView.Adapter<ResultadosDetalhadosOuvidoriaAdapter.MeuViewHolderResultadosDetalhadosOuvidoria> {

    private ArrayList<ResultadosOuvidoria> listOuvidAdapter;

    private Context context;
    private int posicao = 0;

    public ResultadosDetalhadosOuvidoriaAdapter(ArrayList<ResultadosOuvidoria> listaRespostasOuvidoria, Context c) {
        context = c;
        listOuvidAdapter = listaRespostasOuvidoria;
    }

    public ResultadosDetalhadosOuvidoriaAdapter(ArrayList<ResultadosOuvidoria> listaRespostasOuvidoria) {
        listOuvidAdapter = listaRespostasOuvidoria;
        System.out.println("Construtor ResultadosDetalhadosOuvidoriaAdapter" + listOuvidAdapter.toString());

    }

    @NonNull
    @Override
    public MeuViewHolderResultadosDetalhadosOuvidoria onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_resultados_detalhados_ouvidoria, viewGroup, false);

        return new MeuViewHolderResultadosDetalhadosOuvidoria(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolderResultadosDetalhadosOuvidoria meuViewHolderResultadosDetalhadosOuvidoria, int position) {

        meuViewHolderResultadosDetalhadosOuvidoria.tvAdministradorResponsavelOuvidoria.setText(listOuvidAdapter.get(position).getNomeFuncionario());
        meuViewHolderResultadosDetalhadosOuvidoria.tvHoraOuvidoria.setText(listOuvidAdapter.get(position).getHora());
        meuViewHolderResultadosDetalhadosOuvidoria.tvIdDispositivoOuvidoria.setText(listOuvidAdapter.get(position).getIdDispositivo());
        meuViewHolderResultadosDetalhadosOuvidoria.tvRespostaOuvidoria.setText(listOuvidAdapter.get(position).getResposta());

    }

    @Override
    public int getItemCount() {
        return listOuvidAdapter.size();
    }

    public class MeuViewHolderResultadosDetalhadosOuvidoria extends RecyclerView.ViewHolder {

        public TextView tvAdministradorResponsavelOuvidoria;
        public TextView tvHoraOuvidoria;
        public TextView tvIdDispositivoOuvidoria;
        public TextView tvRespostaOuvidoria;


        public MeuViewHolderResultadosDetalhadosOuvidoria(View itemView) {
            super(itemView);
            tvAdministradorResponsavelOuvidoria = itemView.findViewById(R.id.tv_resultados_detalhados_ouvidoria_administrador_responsavel);
            tvHoraOuvidoria = itemView.findViewById(R.id.tv_resultados_detalhados_ouvidoria_hora);
            tvIdDispositivoOuvidoria = itemView.findViewById(R.id.tv_resultados_detalhados_ouvidoria_id_dispositivo);
            tvRespostaOuvidoria = itemView.findViewById(R.id.tv_resultados_detalhados_ouvidoria_resposta);

            if (posicao % 2 == 0) {
                itemView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                posicao++;
            } else {
                itemView.setBackgroundColor(Color.parseColor("#FFFAFA"));
                posicao++;
            }
        }
    }


}
