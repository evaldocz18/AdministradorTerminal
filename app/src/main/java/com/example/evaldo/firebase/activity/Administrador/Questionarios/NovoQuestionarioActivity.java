package com.example.evaldo.firebase.activity.Administrador.Questionarios;

import android.content.Intent;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosQuestionario;
import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Questionarios.GerenciadorQuestionatio.ListarQuestionariosActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NovoQuestionarioActivity extends AppCompatActivity {

    private MultiAutoCompleteTextView mtPerguntaCliente;
    private ImageView ivRespostaSimOuNao, ivResposta3emotionDivulgacao, ivResposta6emotion, ivRespostaAberta, ivRepostaTelefoneEEmail;
    private int qtdPerguntas = 0;
    private Button finalizarCadastroPerguntas;
    private boolean primeiraPergunta = false;
    private TextView tvPerguntasCadastradas;
    private EditText etTituloQuestionario;

    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference PerguntasQuestionarioReferencia = databaseReferencia.child("Banco Perguntas Questionario");
    ResultadosQuestionario resultadosQuestionario;

    String postId = UUID.randomUUID().toString();
    String dataCadastro = pegandoHora();

    //String Pergunta,Titulo,TipoDeResposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_perguntas);

        mtPerguntaCliente = findViewById(R.id.mt_pergunta_cliente);

        ivResposta3emotionDivulgacao = findViewById(R.id.iv_3emotionDivulgacao);
        ivResposta6emotion = findViewById(R.id.iv_6emotion);
        ivRespostaSimOuNao = findViewById(R.id.iv_simounao);
        ivRespostaAberta = findViewById(R.id.iv_aberta);
        ivRepostaTelefoneEEmail = findViewById(R.id.iv_telefoneEEmail);

        finalizarCadastroPerguntas = findViewById(R.id.bt_perguntas_finalizar);
        tvPerguntasCadastradas = findViewById(R.id.tv_perguntas_cadastradas);
        etTituloQuestionario = findViewById(R.id.et_questionario_titulo);


        resultadosQuestionario = new ResultadosQuestionario();


        //Colocar um ouvinte para capturar o administrador no metodo
    }

    private void salvarPergunta(String tipoDeResposta) {

        if (primeiraPergunta) {

           // primeiraPergunta = false;

            if (etTituloQuestionario.getText().toString().equals("")) {
                Toast.makeText(this, "Por favor insira o título do seu questionário ", Toast.LENGTH_LONG).show();
            } else if (mtPerguntaCliente.getText().toString().equals("")) {
                Toast.makeText(this, "Por favor insira a pergunta do seu questionário", Toast.LENGTH_LONG).show();
            } else {

                if (qtdPerguntas < 10) {
                    String pergunta = mtPerguntaCliente.getText().toString();
                    String titulo = etTituloQuestionario.getText().toString();

                    qtdPerguntas += 1;
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("nomeFuncionario").setValue("Evaldo");
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("nomeQuestionario").setValue(titulo);
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("hora").setValue(dataCadastro);
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("idDispositivo").setValue(pegarIDDispositivo());
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("contPerguntas").setValue(qtdPerguntas);
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("pergunta" + qtdPerguntas).setValue(pergunta);
                    PerguntasQuestionarioReferencia.child("id").child(postId).child("resposta" + qtdPerguntas).setValue(tipoDeResposta);
                    mtPerguntaCliente.setText("");
                    tvPerguntasCadastradas.setText("Perguntas Cadastradas = " + qtdPerguntas);
                    notificacao("Pergunta (" + pergunta + ") com tipo de resposta ("+ tipoDeResposta+ ") cadastrada com sucesso!");
                } else {
                    notificacao("Você já ultrapassou o limite maximo de perguntas para este questionário!");
                }
            }
        }else{
            Toast.makeText(this, qtdPerguntas + " A primeira pergunta sempre deve ser a (Princial 3 Emotions e Ouvidoria) ", Toast.LENGTH_SHORT).show();

        }
    }


    public void click3emotionDivulgacao(View view) {
        primeiraPergunta = true;
        salvarPergunta("Princial 3 Emotions e Ouvidoria");

    }

    public void click6emotion(View view) {
        salvarPergunta("6 Emotions");
    }

    public void clickSimOuNao(View view) {
        salvarPergunta("(Sim) ou (Não)");
    }

    public void clickAberta(View view) {
        salvarPergunta("Resposta Aberta");
    }

    public void clickTelefoneEEmail(View view) {
        salvarPergunta("Telefone e Email");
    }

    private void notificacao(String s) {
        Toast.makeText(this,
                s, Toast.LENGTH_LONG).show();
    }

    public void btFinalizarCadastro(View view) {
        Toast.makeText(this, qtdPerguntas + " Perguntas Foram cadastradas", Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(this, ListarQuestionariosActivity.class);
        startActivity(intent);
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada;
        System.out.println("Data " + dataFormatada + " Hora " + horaFormatada);

        return dataEHora;
    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }
}
