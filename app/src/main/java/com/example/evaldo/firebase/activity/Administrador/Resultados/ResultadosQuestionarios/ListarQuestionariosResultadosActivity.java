package com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosQuestionarios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.ResultadosResumidosRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarQuestionariosResultadosActivity extends AppCompatActivity {

    private PerguntasQuestionario perguntasQuestionario;

    ResultadosResumidosRecyclerAdapter questAdapter;
    public ArrayList<PerguntasQuestionario> questListResumidos;
    public static ArrayList<PerguntasQuestionario> resultListResumidos = new ArrayList<>();
    RecyclerView recyclerView;

    private DatabaseReference referencePerguntas;
    private DatabaseReference referenceRespostas;
    private String selectedName;
    Context context = ListarQuestionariosResultadosActivity.this;

    //-------------------Variáveias para dados quantitativos---------------------------------------
    //private Context context;
    // private ArrayList<PerguntasQuestionario> pergList = new ArrayList<PerguntasQuestionario>();

    //private DatabaseReference referenceRespostas = FirebaseDatabase.getInstance().getReference();
    public static PerguntasQuestionario perguntasQuestionarioQuantitativo;
    //ResultadosQuantitativosRecyclerAdapter questAdapter;
    private Query query;
    private TextView tvNomeQuestionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_resultados_resumidos);


        tvNomeQuestionario = findViewById(R.id.tv_listarResultadosResumidos_nomeQuestionario);

        iniciarRecyclerView();

/////////Inicializando metodo para atualizar do iniciarFirebasePerguntas

        iniciarFirebasePerguntas();

        eventoClick();

    }

    private void iniciarRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recycler_view_mostrar_resultados_resumidos);

            questListResumidos = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            ResultadosResumidosRecyclerAdapter resultadosResumidosRecyclerAdapter = new ResultadosResumidosRecyclerAdapter(questListResumidos);

            // System.out.println("ListasRsultadosResumidos iniciarRecyclerView() questListResumidos.toString() = " + questListResumidos.toString());
            recyclerView.setAdapter(resultadosResumidosRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }


    public void clicouExibirResultadosQuantitativos(View view) {

        if (resultListResumidos.isEmpty()) {
            Toast.makeText(this, "Escolha uma opção na lista",Toast.LENGTH_LONG).show();
        }else {
            iniciarFirebaseResultados();
            finish();
            chamarClasseExibirResultadosQuantativos();
        }

    }

    public void clicouExibirResultadosDetalhados(View view) {
        if (resultListResumidos.isEmpty()) {
            Toast.makeText(this, "Escolha uma opção na lista",Toast.LENGTH_LONG).show();
        }else {
            iniciarFirebaseResultados();
            finish();
            chamarClasseExibirResultadosDetalhados();
        }
    }

    private void chamarClasseExibirResultadosQuantativos() {
        Intent intent1 = new Intent(context, ExibirResultadosQuantitativosActivity.class);
        intent1.putExtra("questionario", selectedName);
        startActivity(intent1);
    }

    private void chamarClasseExibirResultadosDetalhados() {

        Intent intent1 = new Intent(context, ExibirResultadosDetalhadosActivity.class);
        intent1.putExtra("questionario", selectedName);
        startActivity(intent1);
    }

    private void iniciarFirebasePerguntas() {
        referencePerguntas = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");
        referencePerguntas.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                questListResumidos.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        PerguntasQuestionario quest = data.getValue(PerguntasQuestionario.class);
                        quest.setKey(data.getKey());
                        questListResumidos.add(quest);
                        //System.out.println("data.toString() = " + data.toString());
                    } catch (Exception e) {
                        System.out.println("Catch");
                    }


                }

                questAdapter = new ResultadosResumidosRecyclerAdapter(questListResumidos, ListarQuestionariosResultadosActivity.this);
                recyclerView.setAdapter(questAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListarQuestionariosResultadosActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });
        System.out.println("Iniciou = iniciarFirebasePerguntas ");
    }

    public void iniciarFirebaseResultados() {

        DatabaseReference referenceRespostas = FirebaseDatabase.getInstance().getReference().child("Banco Respostas Questionário").child("id");

        Query query = referenceRespostas.orderByChild("nomeQuestionario").equalTo(perguntasQuestionarioQuantitativo.getNomeQuestionario());

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                resultListResumidos.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        PerguntasQuestionario quest = data.getValue(PerguntasQuestionario.class);
                        quest.setKey(data.getKey());
                        resultListResumidos.add(quest);
                        //System.out.println("data.toString() = " + data.toString());
                    } catch (Exception e) {
                        System.out.println("Catch");
                    }


                }
                System.out.println("Localizados = " + resultListResumidos.size() + " resultados");
                //System.out.println(" questListResumidos.toString() = " + questListResumidos.toString());
                //System.out.println(" questListResumidos.size() = " + questListResumidos.size());

                // questAdapter = new ResultadosResumidosRecyclerAdapter(resultListResumidos, ListarQuestionariosResultadosActivity.this);
                //recyclerView.setAdapter(questAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(ListarQuestionariosResultadosActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void eventoClick() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mostrar_resultados_resumidos);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        //System.out.println("Click curto");

                        perguntasQuestionario = questListResumidos.get(position);

                        perguntasQuestionarioQuantitativo = perguntasQuestionario;

                        tvNomeQuestionario.setText("QUESTIONÁRIO SELECIONADO = ( " + perguntasQuestionario.getNomeQuestionario() + " )");
                        selectedName = perguntasQuestionario.getNomeQuestionario();
                        iniciarFirebaseResultados();

                        //System.out.println("perguntasQuestionario do click = " + perguntasQuestionario);
                        //chamarClasseExibirResultadosQuantativos();


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("Click longo");

                    }
                })
        );
    }



}
