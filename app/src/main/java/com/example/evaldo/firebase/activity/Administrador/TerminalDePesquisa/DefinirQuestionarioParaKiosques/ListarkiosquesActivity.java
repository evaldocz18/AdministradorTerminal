package com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaKiosques;

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
import com.example.evaldo.firebase.activity.Administrador.Classes.DispositivoKiosque;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.DispositivoKiosquesRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaKiosques.ListarQuestionarioResumidasKiosqueActivity.listDispositivoKiosqueArrayList;
import static com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaKiosques.ListarQuestionarioResumidasKiosqueActivity.perguntasQuestionario;

public class ListarkiosquesActivity extends AppCompatActivity {

    private DispositivoKiosque dispositivoKiosqueFinal;
    private DispositivoKiosquesRecyclerAdapter dispositivoKiosquesRecyclerAdapter;
    private ArrayList<DispositivoKiosque> dispositivoKiosquesArrayListFinal = listDispositivoKiosqueArrayList;
    private RecyclerView recyclerView;
    private Context context;
    private TextView tvNovoEAtual, tvIdDispositivo, tvNomeDispositivo, tvQuestionarioAtual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarkiosques);

        System.out.println("Tela = ListarkiosquesActivity");

        iniciarTextView();

        iniciarRecyclerView();

        iniciarFirebaseDispositivosKiosque();

        eventoClick();
    }

    private void iniciarTextView() {
        tvNovoEAtual = findViewById(R.id.tv_ListarKiosque_novoEAtual);
        tvNomeDispositivo = findViewById(R.id.tv_ListarKiosque_Nome);
    }

    private void iniciarRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recyclerView_Kiosques);

            listDispositivoKiosqueArrayList = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            DispositivoKiosquesRecyclerAdapter dispositivoKiosquesRecyclerAdapter = new DispositivoKiosquesRecyclerAdapter(listDispositivoKiosqueArrayList);
            //System.out.println(dispositivoKiosquesRecyclerAdapter.toString());
            recyclerView.setAdapter(dispositivoKiosquesRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }

    public void iniciarFirebaseDispositivosKiosque() {
        DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dispositivosKiosqueReferencia = databaseReferencia.child("Dispositivos Kiosque");


        DatabaseReference referenceDispositivosKiosque = FirebaseDatabase.getInstance().getReference().child("Dispositivos Kiosque").child("idDispositivo");
        //Query query = referenceDispositivosKiosque.orderByChild("idDispositivo").equalTo("c63fd0981626ca24");

        referenceDispositivosKiosque.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listDispositivoKiosqueArrayList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        DispositivoKiosque dispositivoKiosque = data.getValue(DispositivoKiosque.class);
                        dispositivoKiosque.setKey(data.getKey());
                        listDispositivoKiosqueArrayList.add(dispositivoKiosque);
                    } catch (Exception e) {
                        System.out.println("Catch =" + e);
                    }


                }
                System.out.println("Localizados = " + listDispositivoKiosqueArrayList.size() + " resultados");

                dispositivoKiosquesRecyclerAdapter = new DispositivoKiosquesRecyclerAdapter(listDispositivoKiosqueArrayList, ListarkiosquesActivity.this);
                recyclerView.setAdapter(dispositivoKiosquesRecyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(ResultadosQuestionariosActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void eventoClick() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView_Kiosques);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //System.out.println("Click curto");

                        dispositivoKiosqueFinal = listDispositivoKiosqueArrayList.get(position);


                        System.out.println("perguntasQuestionario.toString() = " + perguntasQuestionario.toString());

                        tvNomeDispositivo.setText("Nome do Dispositivo: " + dispositivoKiosqueFinal.getNomeDispositivo());
                        tvNovoEAtual.setText("ALTERAR O QUESTIONÁRIO ATUAL (" + dispositivoKiosqueFinal.getQuestionarioAtual() + ") para (" + perguntasQuestionario.getNomeQuestionario()+ ")" );


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("Click longo");

                    }
                })
        );
    }


    public void clikouConfirmarQuestionarioKiosque(View view) {
        System.out.println("tvNomeDispositivo.getText() = " + tvNomeDispositivo.getText());
        if (tvNomeDispositivo.getText().equals("Selecione um Dispositivo Kiosque da Lista")) {
            Toast.makeText(this, "Por favor escolha um dispositivo da lista",Toast.LENGTH_LONG).show();
        }else {
            DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
            DatabaseReference dispositivosKiosqueReferencia = databaseReferencia.child("Dispositivos Kiosque");

            dispositivosKiosqueReferencia.child("idDispositivo").child(dispositivoKiosqueFinal.getIdDispositivo()).child("questionarioAtual").setValue(perguntasQuestionario.getNomeQuestionario());

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


