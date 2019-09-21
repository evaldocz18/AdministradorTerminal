package com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaKiosques;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.DispositivoKiosque;
import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.ResultadosResumidosRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarQuestionarioResumidasKiosqueActivity extends AppCompatActivity {


    ResultadosResumidosRecyclerAdapter questAdapter;

    public static PerguntasQuestionario perguntasQuestionario;

    public ArrayList<PerguntasQuestionario> listquestListQuestionariosResumidosArrayList ;
    public static ArrayList<DispositivoKiosque> listDispositivoKiosqueArrayList = new ArrayList<>();
    RecyclerView recyclerView;

    private DatabaseReference referencePerguntas;
    private DatabaseReference referenceDispositivosKiosque;

    Context context = ListarQuestionarioResumidasKiosqueActivity.this;

    private TextView tvNomeQuestionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_resultados_resumidas_kiosque);

        System.out.println("Tela = ListarQuestionarioResumidasKiosqueActivity");

        tvNomeQuestionario = findViewById(R.id.tv_listarResultadosResumidosKiosque_nomeQuestionario);

        iniciarRecyclerView();

/////////Inicializando metodo para atualizar do iniciarFirebasePerguntas

        iniciarFirebasePerguntas();

        eventoClick();

    }

    private void iniciarRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recyclerView_mostrarResultadosResumidosKiosque);

            listquestListQuestionariosResumidosArrayList = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            ResultadosResumidosRecyclerAdapter resultadosResumidosRecyclerAdapter = new ResultadosResumidosRecyclerAdapter(listquestListQuestionariosResumidosArrayList);

            // System.out.println("ListasRsultadosResumidos iniciarRecyclerView() questListResumidos.toString() = " + questListResumidos.toString());
            recyclerView.setAdapter(resultadosResumidosRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }


    private void  chamarlistarkiosques() {
        Intent intent1 = new Intent(context, ListarkiosquesActivity.class);
        startActivity(intent1);
    }


    private void iniciarFirebasePerguntas() {
        referencePerguntas = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");
        referencePerguntas.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listquestListQuestionariosResumidosArrayList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        PerguntasQuestionario quest = data.getValue(PerguntasQuestionario.class);
                        quest.setKey(data.getKey());
                        listquestListQuestionariosResumidosArrayList.add(quest);
                        //System.out.println("data.toString() = " + data.toString());
                    } catch (Exception e) {
                        System.out.println("Catch");
                    }


                }

                questAdapter = new ResultadosResumidosRecyclerAdapter(listquestListQuestionariosResumidosArrayList, ListarQuestionarioResumidasKiosqueActivity.this);
                recyclerView.setAdapter(questAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListarQuestionarioResumidasKiosqueActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void eventoClick() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView_mostrarResultadosResumidosKiosque);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //System.out.println("Click curto");

                        perguntasQuestionario = listquestListQuestionariosResumidosArrayList.get(position);


                        tvNomeQuestionario.setText("Questionário selecionado (" + perguntasQuestionario.getNomeQuestionario() + ")");

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("Click longo");

                    }
                })
        );
    }


    public void clicouConfirmarQuestionarioKiosque(View view) {
       // System.out.println("perguntasQuestionario.getNomeQuestionario()" +perguntasQuestionario.getNomeQuestionario().toString());
        if (tvNomeQuestionario.getText().equals("Selecione um Questionário da Lista")) {
            Toast.makeText(this, "Por favor escolha uma questionário da lista",Toast.LENGTH_LONG).show();
        }else {

            finish();
            chamarlistarkiosques();
        }
    }
}
