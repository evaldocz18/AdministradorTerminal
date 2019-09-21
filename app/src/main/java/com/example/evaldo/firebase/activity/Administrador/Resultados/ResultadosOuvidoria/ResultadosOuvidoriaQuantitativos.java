package com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosOuvidoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosOuvidoria;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosOuvidoria.ResultadosOuvidoriaDetalhados.listOuvidoria;

public class ResultadosOuvidoriaQuantitativos extends AppCompatActivity {

    private DatabaseReference referenceOuvidoria;
    private Button btElogios, btSugestoes, btReclamacoes;

    public static ArrayList<ResultadosOuvidoria> listElogios = new ArrayList<>();
    public static ArrayList<ResultadosOuvidoria> listSugestoes = new ArrayList<>();
    public static ArrayList<ResultadosOuvidoria> listReclamacoes = new ArrayList<>();


    public static ArrayList<ResultadosOuvidoria> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_ouvidoria_quantitativos);

        btElogios = findViewById(R.id.bt_exibir_resultados_detalhados_elogios);
        btSugestoes = findViewById(R.id.bt_exibir_resultados_detalhados_sugestoes);
        btReclamacoes = findViewById(R.id.bt_exibir_resultados_detalhados_reclamacoes);

        iniciarFirebaseOuvidoriaElogios();
        iniciarFirebaseOuvidoriaSubestoes();
        iniciarFirebaseOuvidoriaReclamacoes();

    }

    private void iniciarFirebaseOuvidoriaElogios() {

        referenceOuvidoria = FirebaseDatabase.getInstance().getReference().child("Banco Ouvidoria").child("Elogios").child("id");
        referenceOuvidoria.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listElogios.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        ResultadosOuvidoria ouvidoria = data.getValue(ResultadosOuvidoria.class);
                        ouvidoria.setResposta("Elogio: (" + ouvidoria.getResposta() + ")");
                        ouvidoria.setAdministradorResponsavel("Administrador Responsável: (" + ouvidoria.getAdministradorResponsavel() + ")");
                        ouvidoria.setHora("(" + ouvidoria.getHora() + ")");
                        ouvidoria.setIdDispositivo("Id do dispositivo: (" + ouvidoria.getIdDispositivo() + ")" );
                        listElogios.add(ouvidoria);
                        btElogios.setText("Total de Elogios = " + listElogios.size());

                    } catch (Exception e) {
                        System.out.println("Cath Firebase Elogios");
                    }

                }
                System.out.println("listElogios.toString()" + listElogios.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ResultadosOuvidoriaQuantitativos.this, "Erro no iniciarFirebaseOuvidoriaElogios", Toast.LENGTH_LONG).show();
            }
        });




    }

    private void iniciarFirebaseOuvidoriaSubestoes() {

        referenceOuvidoria = FirebaseDatabase.getInstance().getReference().child("Banco Ouvidoria").child("Sugestoes").child("id");
        referenceOuvidoria.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listSugestoes.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        ResultadosOuvidoria ouvidoria = data.getValue(ResultadosOuvidoria.class);
                        ouvidoria.setResposta("Sugestões: (" + ouvidoria.getResposta() + ")");
                        ouvidoria.setAdministradorResponsavel("Administrador Responsável: (" + ouvidoria.getAdministradorResponsavel() + ")");
                        listSugestoes.add(ouvidoria);
                        btSugestoes.setText("Total de Sugestões = " + listSugestoes.size());

                    } catch (Exception e) {
                        System.out.println("Cath Firebase Sugestões");
                    }
                }
                System.out.println("listSugestoes.toString() =" + listSugestoes.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ResultadosOuvidoriaQuantitativos.this, "Erro no iniciarFirebaseOuvidoriaSubestoes", Toast.LENGTH_LONG).show();
            }
        });
      //  System.out.println("Iniciou = iniciarFirebaseOuvidoria ");


    }

    private void iniciarFirebaseOuvidoriaReclamacoes() {

        referenceOuvidoria = FirebaseDatabase.getInstance().getReference().child("Banco Ouvidoria").child("Reclamacoes").child("id");
        referenceOuvidoria.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listReclamacoes.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        ResultadosOuvidoria ouvidoria = data.getValue(ResultadosOuvidoria.class);
                        ouvidoria.setResposta("Reclamações: (" + ouvidoria.getResposta() + ")");
                        ouvidoria.setAdministradorResponsavel("Administrador Responsável: (" + ouvidoria.getAdministradorResponsavel() + ")");
                        listReclamacoes.add(ouvidoria);
                        btReclamacoes.setText("Total de Reclamações = " + listReclamacoes.size());

                    } catch (Exception e) {
                        System.out.println("Cath Firebase Reclamações");
                    }


                }
                System.out.println("listReclamacoes.toString() = " + listReclamacoes.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ResultadosOuvidoriaQuantitativos.this, "Erro no iniciarFirebaseOuvidoriaReclamacoes", Toast.LENGTH_LONG).show();
            }
        });
      //  System.out.println("Iniciou = iniciarFirebaseOuvidoria ");



    }

    public void clicouExibirElogiosDetalhados(View view) {
        if (listSugestoes.isEmpty()) {
            Toast.makeText(this, "Por favor aguarde carregar os dados",Toast.LENGTH_LONG).show();
        }else {
            listOuvidoria = listElogios;
            Intent intent = new Intent(this, ResultadosOuvidoriaDetalhados.class);
            startActivity(intent);
        }
    }

    public void clicouExibirSugestoesDetalhadas(View view) {
        if (listSugestoes.isEmpty()) {
            Toast.makeText(this, "Por favor aguarde carregar os dados",Toast.LENGTH_LONG).show();
        }else {
            listOuvidoria = listSugestoes;
            Intent intent = new Intent(this, ResultadosOuvidoriaDetalhados.class);
            startActivity(intent);
        }
    }

    public void clicouExibirRecclamacoesDetalhadas(View view) {
        if (listSugestoes.isEmpty()) {
            Toast.makeText(this, "Por favor aguarde carregar os dados",Toast.LENGTH_LONG).show();
        }else {
            listOuvidoria = listReclamacoes;
            Intent intent = new Intent(this, ResultadosOuvidoriaDetalhados.class);
            startActivity(intent);
        }
    }



}
