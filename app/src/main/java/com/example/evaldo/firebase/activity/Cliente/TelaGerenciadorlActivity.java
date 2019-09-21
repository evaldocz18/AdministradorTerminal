package com.example.evaldo.firebase.activity.Cliente;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.PrincipalActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TelaGerenciadorlActivity extends AppCompatActivity {

    PerguntasQuestionario perguntasQuestionario;

    private DatabaseReference reference;

    public static int contPerguntas;

    public static String  idDispositivo, idRespostaQuestionario, administradorResponsavel, pergunta1, pergunta2, tipoResposta1, tipoResposta2;

    public static String perguntaAtual = "Pergunta atual não recebeu dado";

    //Quem deve setar o questionario atual é o administrador AINDA FALTA FAZER!
    public static String questionarioAtual = "BCC";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telas_principal);

        idDispositivo = pegarIDDispositivo();



        firebaseBuscarPerguntas();

        salvarDadosPerguntaQuestionarioEmVariaveisStaticas();

      //  exibirDadosRecebidosDoFibaseArmazenadosNoSharePreferences();




    }

    private void exibirDadosRecebidosDoFibaseArmazenadosNoSharePreferences() {
        System.out.println("receberContPerguntasPerguntasDoQuestionario() = " + contPerguntas);
        System.out.println("receberAdministradorResponsavelPerguntasDoQuestionario() = " + administradorResponsavel);
        System.out.println("receberTipoResposta1PerguntasDoQuestionario() = " + tipoResposta1);
        System.out.println("receberTipoResposta2PerguntasDoQuestionario() = " + tipoResposta2);
        System.out.println("receberPergunta1Questionario()" + pergunta1);
        System.out.println("receberPergunta2Questionario()" + pergunta2);
    }

    private void salvarDadosPerguntaQuestionarioEmVariaveisStaticas(){

        contPerguntas = receberContPerguntasPerguntasDoQuestionario();
        administradorResponsavel = receberAdministradorResponsavelPerguntasDoQuestionario();
        tipoResposta1 = receberTipoResposta1PerguntasDoQuestionario();
        tipoResposta2 = receberTipoResposta2PerguntasDoQuestionario();
        pergunta1 = receberPergunta1Questionario();
        pergunta2 = receberPergunta2Questionario();
    }

    public void salvarDadosPerguntasDoQuestionarioNoSharePreferences(String tipoResposta1, String tipoResposta2, int contPerguntas, String perg1, String perg2, String adminResponsavel) {

        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = myPreferences.edit();
        mEditor.putInt("ContadorPerguntas", contPerguntas);
        mEditor.putString("AdministradorResponsavel", adminResponsavel);
        mEditor.putString("TipoResposta1", tipoResposta1);
        mEditor.putString("TipoResposta2", tipoResposta2);
        mEditor.putString("Pergunta1", perg1);
        mEditor.putString("Pergunta2", perg2);
        mEditor.commit();

    }



    private void chamarPrincipal() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        finish();
        startActivity(intent);
    }

    private void firebaseBuscarPerguntas() {
        reference = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.child("nomeQuestionario").getValue().equals(questionarioAtual)) {
                        // System.out.println("Questionario Localizado");
                        perguntasQuestionario = data.getValue(PerguntasQuestionario.class);
                        //System.out.println(perguntasQuestionario);
                        salvarDadosPerguntasDoQuestionarioNoSharePreferences(perguntasQuestionario.getResposta1(), perguntasQuestionario.getResposta2(),
                                                                perguntasQuestionario.getContPerguntas(),
                                                                perguntasQuestionario.getPergunta1(), perguntasQuestionario.getPergunta2(),perguntasQuestionario.getAdministradorResponsavel());

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TelaGerenciadorlActivity.this, "Erro no firebase", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String receberAdministradorResponsavelPerguntasDoQuestionario() {
        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        String adminResp = myPreferences.getString("AdministradorResponsavel", "Administrador Responsavel Respostas questionario não carregador, verifique receberAdministradorResponsavelPerguntasDoQuestionario() ");
        return adminResp;
    }
    private int receberContPerguntasPerguntasDoQuestionario() {
        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        int contPerg = myPreferences.getInt("ContadorPerguntas", -1);
        return contPerg;
    }
    public String receberTipoResposta1PerguntasDoQuestionario() {
        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        String textoReceber1 = myPreferences.getString("TipoResposta1", "texto1");
        return textoReceber1;
    }
    public String receberTipoResposta2PerguntasDoQuestionario() {
        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        String textoReceber2 = myPreferences.getString("TipoResposta2", "texto2");
        return textoReceber2;
    }
    public String receberPergunta1Questionario(){
        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        String perguntaReceber1 = myPreferences.getString("Pergunta1", "pergunta 1 não Carregada verificar receberPergunta1Questionario()");
        return perguntaReceber1;
    }
    public String receberPergunta2Questionario(){
        SharedPreferences myPreferences = getSharedPreferences("Arquivo", MODE_PRIVATE);
        String perguntaReceber2 = myPreferences.getString("Pergunta2", "pergunta 2 não Carregada verificar receberPergunta1Questionario()");
        return perguntaReceber2;
    }



    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

}
