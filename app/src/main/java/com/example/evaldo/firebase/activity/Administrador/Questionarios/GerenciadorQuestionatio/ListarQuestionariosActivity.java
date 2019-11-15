package com.example.evaldo.firebase.activity.Administrador.Questionarios.GerenciadorQuestionatio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.evaldo.firebase.activity.Administrador.Questionarios.NovoQuestionarioActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.PerguntasResumidasBaseAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarQuestionariosActivity extends AppCompatActivity {

    private TextView tvKey, tvIdDispositivo, tvNomeQuestionario, tvAdministradorResponsavel, tvQtdPerguntas, etData, etPergunta1, etResposta1, etPergunta2, etResposta2, etPergunta3, etResposta3, etPergunta4, etResposta4, etPergunta5, etResposta5, etPergunta6, etResposta6, etPergunta7, etResposta7, etPergunta8, etResposta8, etPergunta9, etResposta9, etPergunta10, etResposta10;
    private ListView listView;

    public static List<PerguntasQuestionario> listQuestionarios;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    public static PerguntasQuestionario perguntasQuestionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geranciar_perguntas_mais);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingButton();

        listView = findViewById(R.id.list_view_questionario);

        //System.out.println("inicializarComponentes()");
        inicializarComponentes();

        // System.out.println("inicializarFirebase()");
        inicializarFirebase();

        //System.out.println("iniciarFirebase()");
        iniciarFirebase();

        inicializarEventosLista();

        eventoClick();

    }

    private void eventoClick() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(ListarQuestionariosActivity.this, "Click longo id lista = " + position, Toast.LENGTH_LONG).show();

                System.out.println("clicou");
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarQuestionariosActivity.this);

                builder.setTitle("ATENÇÃO");
                builder.setMessage("(CUIDADO) TODOS OS DISPOSITIVOS QUE UTILIZAM ESTE QUESTIONÁRIO SERÃO AFETADOS" + "\n" + "\n" +
                        "Nome do Questionário: " + listQuestionarios.get(position).getNomeQuestionario() + "\n" + "\n" +
                        "Administrador Responsável: " + listQuestionarios.get(position).getAdministradorResponsavel() + "\n" + "\n" +
                        "Data da Ultima Atualização: " + listQuestionarios.get(position).getHora() + "\n" + "\n" +
                        "Quantidade de Perguntas: " + listQuestionarios.get(position).getContPerguntas() + "\n" + "\n" +
                        "Id do Questionário: " + listQuestionarios.get(position).getKey() + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" +

                        "Tem certeza que deseja EXCLUIR definitivamente este Qqestionário?");

                builder.setPositiveButton("APAGAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(ListarQuestionariosActivity.this, "Questionário (" + listQuestionarios.get(position).getNomeQuestionario() + ")Apagado. ", Toast.LENGTH_LONG).show();
                        System.out.println();
                        reference.child(listQuestionarios.get(position).getKey()).removeValue();
                        //reference = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");

                    }
                });

                builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ListarQuestionariosActivity.this, "Questionário (" + listQuestionarios.get(position).getNomeQuestionario() + "Não foi Apagado. ", Toast.LENGTH_LONG).show();


                    }
                });

                builder.create();
                builder.show();

                return true;
            }
        });
    }


    private void inicializarComponentes() {
        try {
            tvIdDispositivo = findViewById(R.id.et_idDispositivo);
            tvNomeQuestionario = findViewById(R.id.et_nomeQuestionario);
            tvAdministradorResponsavel = findViewById(R.id.et_administradorResponsavel);

            listView = findViewById(R.id.list_view_questionario);

            listQuestionarios = new ArrayList<>();

        } catch (Exception e) {
            System.out.println("Erro ao inicializar componentes" + e);
        }

    }

    private void inicializarFirebase() {
        try {
            FirebaseApp.initializeApp(this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            reference = firebaseDatabase.getReference();
        } catch (Exception e) {
            System.out.println("Erro inicializarFirebase() " + e);
        }

    }

    private void iniciarFirebase() {

        reference = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listQuestionarios.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        PerguntasQuestionario perg = data.getValue(PerguntasQuestionario.class);
                        perg.setKey(data.getKey());
                        listQuestionarios.add(perg);
                        //System.out.println(data);
                    } catch (Exception e) {
                        System.out.println("Catch");
                    }
                }
                try {
                    //System.out.println("Lista completa = " + listQuestionarios.toString());

                    listView.setAdapter(new PerguntasResumidasBaseAdapter(ListarQuestionariosActivity.this, listQuestionarios));
                } catch (Exception e) {
                    System.out.println("Erro listView.setAdapter(new PerguntasResumidasBaseAdapter(ListarPerguntasResumidasActivity.this, listQuestionarios)); ");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListarQuestionariosActivity.this, "Erro no iniciarFirebase", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializarEventosLista() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Variável estatica que vai ser usada no atualizar Perguntas
                perguntasQuestionario = listQuestionarios.get(position);

                chamarTelaAtualizarPerguntas();
            }
        });
    }

    private void floatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(ListarQuestionariosActivity.this, NovoQuestionarioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void chamarTelaAtualizarPerguntas() {
        Intent intent = new Intent(ListarQuestionariosActivity.this, AtualizarPerguntasActivity.class);
        startActivity(intent);
    }


}
