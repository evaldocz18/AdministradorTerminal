package com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaTerminais;

import android.content.Context;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.TerminalPesquisa;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.TerminalPesquisaRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasTerminalActivity.listDispositivoTerminalArrayList;
import static com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasTerminalActivity.perguntasQuestionario;

public class ListarTerminaisActivity extends AppCompatActivity {

    private TerminalPesquisa terminalPesquisaFinal;
    private TerminalPesquisaRecyclerAdapter terminalPesquisaRecyclerAdapter;
    private ArrayList<TerminalPesquisa> TerminaisPesquisaArrayListFinal = listDispositivoTerminalArrayList;
    private RecyclerView recyclerView;
    private Context context;
    private TextView tvNovoEAtual, tvIdDispositivo, tvNomeDispositivo, tvQuestionarioAtual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_terminais);

        iniciarTextView();

        iniciarRecyclerView();

        iniciarFirebaseDispositivosTerminais();

        eventoClick();
    }

    private void iniciarTextView() {
        tvNovoEAtual = findViewById(R.id.tv_ListarTerminal_novoEAtual);
        tvNomeDispositivo = findViewById(R.id.tv_ListarTerminal_Nome);
    }

    private void iniciarRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recyclerView_terminais);

            listDispositivoTerminalArrayList = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            TerminalPesquisaRecyclerAdapter terminalPesquisaRecyclerAdapter = new TerminalPesquisaRecyclerAdapter(listDispositivoTerminalArrayList);
            //System.out.println(terminalPesquisaRecyclerAdapter.toString());
            recyclerView.setAdapter(terminalPesquisaRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }

    public void iniciarFirebaseDispositivosTerminais() {
       /* DatabaseReference dispositivosTerminalReferencia = databaseReferencia.child("Terminais de Pesquisa");
        Query query = referenceTerminaisPesquisa.orderByChild("idDispositivo").equalTo("c63fd0981626ca24");*/
        //DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();

        DatabaseReference referenceTerminal = FirebaseDatabase.getInstance().getReference().child("Terminais de Pesquisa").child("id");


        referenceTerminal.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listDispositivoTerminalArrayList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        TerminalPesquisa terminalPesquisa = data.getValue(TerminalPesquisa.class);
                        terminalPesquisa.setKey(data.getKey());
                        listDispositivoTerminalArrayList.add(terminalPesquisa);
                    } catch (Exception e) {
                        System.out.println("Catch =" + e);
                    }


                }
                System.out.println("Localizados = " + listDispositivoTerminalArrayList.size() + " resultados");

                terminalPesquisaRecyclerAdapter = new TerminalPesquisaRecyclerAdapter(listDispositivoTerminalArrayList, ListarTerminaisActivity.this);
                recyclerView.setAdapter(terminalPesquisaRecyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(ListarQuestionariosResultadosActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void eventoClick() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView_terminais);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //System.out.println("Click curto");

                        terminalPesquisaFinal = listDispositivoTerminalArrayList.get(position);


                        System.out.println("perguntasQuestionario.toString() = " + perguntasQuestionario.toString());

                        tvNomeDispositivo.setText("Nome do Dispositivo: " + terminalPesquisaFinal.getNomeDispositivo());
                        tvNovoEAtual.setText("ALTERAR O QUESTIONÁRIO ATUAL (" + terminalPesquisaFinal.getQuestionarioAtual() + ") para (" + perguntasQuestionario.getNomeQuestionario()+ ")" );


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("Click longo");

                    }
                })
        );
    }


    public void clikouConfirmarQuestionarioTerminal(View view) {
        System.out.println("tvNomeDispositivo.getText() = " + tvNomeDispositivo.getText());
        if (tvNomeDispositivo.getText().equals("Selecione um Terminal Lista")) {
            Toast.makeText(this, "Por favor escolha um dispositivo da lista",Toast.LENGTH_LONG).show();
        }else {
            DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
            DatabaseReference terminaisPesquisaReferencia = databaseReferencia.child("Terminais de Pesquisa").child("id");

            terminaisPesquisaReferencia.child(terminalPesquisaFinal.getIdDispositivo()).child("questionarioAtual").setValue(perguntasQuestionario.getNomeQuestionario());

            pegarIDDispositivo();

            Toast.makeText(this, "Questionário alterado com sucesso", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }
}


