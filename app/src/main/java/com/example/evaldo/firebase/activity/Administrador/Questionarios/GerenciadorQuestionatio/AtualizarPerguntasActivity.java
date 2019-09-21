package com.example.evaldo.firebase.activity.Administrador.Questionarios.GerenciadorQuestionatio;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.MainActivity;
import com.example.evaldo.firebase.activity.Administrador.util.DialogAlerta;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.evaldo.firebase.activity.Administrador.Questionarios.GerenciadorQuestionatio.ListarQuestionariosActivity.perguntasQuestionario;


public class AtualizarPerguntasActivity extends AppCompatActivity {

    private TextView tvKey, tvIdDispositivo, tvNomeQuestionario, tvQtdPerguntas, tvData;

    private TextView tvResposta1, tvResposta2, tvResposta3, tvResposta4, tvResposta5, tvResposta6, tvResposta7, tvResposta8, tvResposta9, tvResposta10;

    private TextInputLayout etdAdministradorResponsavel, etdPergunta1, etdPergunta2, etdPergunta3, etdPergunta4, etdPergunta5, etdPergunta6, etdPergunta7, etdPergunta8, etdPergunta9, etdPergunta10;

    private MultiAutoCompleteTextView mtAdministradorResponsavel, mtPergunta1, mtPergunta2, mtPergunta3, mtPergunta4, mtPergunta5, mtPergunta6, mtPergunta7, mtPergunta8, mtPergunta9, mtPergunta10;
    private Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10;

    private EditText etAdministradorResponsavel;

    String idPergunta = perguntasQuestionario.getKey();

    ArrayList<String> perguntas = new ArrayList();
    ArrayList<String> respostas = new ArrayList();


    private boolean teste = true;
    private String penultimaRespostaSpinnerBranco = null, penultimaPerguntaContextoBranco = null, penultimaPerguntaHintBranco = null;
    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference PerguntasQuestionarioReferencia = databaseReferencia.child("Banco Perguntas Questionario");

    private int contadorPerguntasMetodoVerificar = perguntasQuestionario.getContPerguntas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perguntas);

        inicializandoTextView();

        inicalizandoDadosAtuzalizar();

        inicializandoTipoRespostaSpinner();

    }

    private void inicializandoTextView() {
        try {
            tvNomeQuestionario = findViewById(R.id.tv_atualizarPerguntas_nomeQuestionario);
            tvData = findViewById(R.id.tv_atualizarPerguntas_dataCriacao);
            tvQtdPerguntas = findViewById(R.id.tv_atualizarPerguntas_quantidadeDePerguntasCadastradas);

            tvNomeQuestionario.setText("Questionário: " + perguntasQuestionario.getNomeQuestionario());
            tvData.setText("Atualizado em: " + perguntasQuestionario.getHora());
            tvQtdPerguntas.setText("Quantidade de Perguntas: " + perguntasQuestionario.getContPerguntas());
        } catch (Exception e) {
            System.out.println("Erro inicializandoTextView() = " + e);
        }
    }

    private void inicalizandoDadosAtuzalizar() {
        try {
            ///////////////////////Iniciando variaveis para receber os EditText///////////////////////////
            etdAdministradorResponsavel = findViewById(R.id.etd_atuzalizarPerguntas_administradorResponsavel);


            etdPergunta1 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta1);
            etdPergunta2 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta2);
            etdPergunta3 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta3);
            etdPergunta4 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta4);
            etdPergunta5 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta5);
            etdPergunta6 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta6);
            etdPergunta7 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta7);
            etdPergunta8 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta8);
            etdPergunta9 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta9);
            etdPergunta10 = findViewById(R.id.etd_atuzalizarPerguntas_pergunta10);

            mtPergunta1 = findViewById(R.id.multiAutoCompleteTextView1);
            mtPergunta2 = findViewById(R.id.multiAutoCompleteTextView2);
            mtPergunta3 = findViewById(R.id.multiAutoCompleteTextView3);
            mtPergunta4 = findViewById(R.id.multiAutoCompleteTextView4);
            mtPergunta5 = findViewById(R.id.multiAutoCompleteTextView5);
            mtPergunta6 = findViewById(R.id.multiAutoCompleteTextView6);
            mtPergunta7 = findViewById(R.id.multiAutoCompleteTextView7);
            mtPergunta8 = findViewById(R.id.multiAutoCompleteTextView8);
            mtPergunta9 = findViewById(R.id.multiAutoCompleteTextView9);
            mtPergunta10 = findViewById(R.id.multiAutoCompleteTextView10);

            tvResposta1 = findViewById(R.id.tv_atualizarPerguntas_resposta1);
            tvResposta2 = findViewById(R.id.tv_atualizarPerguntas_resposta2);
            tvResposta3 = findViewById(R.id.tv_atualizarPerguntas_resposta3);
            tvResposta4 = findViewById(R.id.tv_atualizarPerguntas_resposta4);
            tvResposta5 = findViewById(R.id.tv_atualizarPerguntas_resposta5);
            tvResposta6 = findViewById(R.id.tv_atualizarPerguntas_resposta6);
            tvResposta7 = findViewById(R.id.tv_atualizarPerguntas_resposta7);
            tvResposta8 = findViewById(R.id.tv_atualizarPerguntas_resposta8);
            tvResposta9 = findViewById(R.id.tv_atualizarPerguntas_resposta9);
            tvResposta10 = findViewById(R.id.tv_atualizarPerguntas_resposta10);


            ///////////////////////Carregando perguntas das variaveis para EditText ///////////////////////////
            etdAdministradorResponsavel.getEditText().setText(perguntasQuestionario.getAdministradorResponsavel());

            etdPergunta1.getEditText().setText(perguntasQuestionario.getPergunta1());
            etdPergunta2.getEditText().setText(perguntasQuestionario.getPergunta2());
            etdPergunta3.getEditText().setText(perguntasQuestionario.getPergunta3());
            etdPergunta4.getEditText().setText(perguntasQuestionario.getPergunta4());
            etdPergunta5.getEditText().setText(perguntasQuestionario.getPergunta5());
            etdPergunta6.getEditText().setText(perguntasQuestionario.getPergunta6());
            etdPergunta7.getEditText().setText(perguntasQuestionario.getPergunta7());
            etdPergunta8.getEditText().setText(perguntasQuestionario.getPergunta8());
            etdPergunta9.getEditText().setText(perguntasQuestionario.getPergunta9());
            etdPergunta10.getEditText().setText(perguntasQuestionario.getPergunta10());


        } catch (Exception e) {
            System.out.println("Erro inicalizandoDadosAtuzalizar() = " + e);
        }
    }

    private void inicializandoTipoRespostaSpinner() {
        try {

            String tipoResposta;

            //Respostas 1
            spinner1 = findViewById(R.id.spin_atualizarPerguntas1);
            ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas_primaira_tela, android.R.layout.simple_spinner_item);
            spinner1.setAdapter(adapter1);

            spinner1.setSelection(verificarNullRetornarPosicaoSpinnerPrimeiraTela(perguntasQuestionario.getResposta1()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner1 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta1(atualizarTipoResposta(spinner1.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner1.setOnItemSelectedListener(escolhaSpinner1);


            //Respostas 2
            spinner2 = findViewById(R.id.spin_atualizarPerguntas2);
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter2);

            //Recebe o tipo de resposta não tratada, verificar se é null trata a reposta para o spinner e retorna a posição correta
            //que o spinner deve assumir

            spinner2.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta2()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner2 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta2(atualizarTipoResposta(spinner2.getSelectedItem().toString()));
                    //System.out.println(perguntasQuestionario.getResposta2());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };

            spinner2.setOnItemSelectedListener(escolhaSpinner2);

            //Respostas 3
            spinner3 = findViewById(R.id.spin_atualizarPerguntas3);
            ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner3.setAdapter(adapter3);

            spinner3.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta3()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner3 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta3(atualizarTipoResposta(spinner3.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner3.setOnItemSelectedListener(escolhaSpinner3);

            //Respostas 4
            spinner4 = findViewById(R.id.spin_atualizarPerguntas4);
            ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner4.setAdapter(adapter4);

            spinner4.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta4()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner4 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta4(atualizarTipoResposta(spinner4.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner4.setOnItemSelectedListener(escolhaSpinner4);

            //Respostas 5
            spinner5 = findViewById(R.id.spin_atualizarPerguntas5);
            ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner5.setAdapter(adapter5);

            spinner5.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta5()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner5 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta5(atualizarTipoResposta(spinner5.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner5.setOnItemSelectedListener(escolhaSpinner5);

            //Respostas 6
            spinner6 = findViewById(R.id.spin_atualizarPerguntas6);
            ArrayAdapter adapter6 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner6.setAdapter(adapter6);

            spinner6.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta6()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner6 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta6(atualizarTipoResposta(spinner6.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner6.setOnItemSelectedListener(escolhaSpinner6);

            //Respostas 7
            spinner7 = findViewById(R.id.spin_atualizarPerguntas7);
            ArrayAdapter adapter7 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner7.setAdapter(adapter7);

            spinner7.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta7()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner7 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta7(atualizarTipoResposta(spinner7.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner7.setOnItemSelectedListener(escolhaSpinner7);

            //Respostas 8
            spinner8 = findViewById(R.id.spin_atualizarPerguntas8);
            ArrayAdapter adapter8 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner8.setAdapter(adapter8);

            spinner8.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta8()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner8 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta8(atualizarTipoResposta(spinner8.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner8.setOnItemSelectedListener(escolhaSpinner8);

            //Respostas 9
            spinner9 = findViewById(R.id.spin_atualizarPerguntas9);
            ArrayAdapter adapter9 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner9.setAdapter(adapter9);

            spinner9.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta9()));


            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner9 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta9(atualizarTipoResposta(spinner9.getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner9.setOnItemSelectedListener(escolhaSpinner9);

            //Respostas 10
            spinner10 = findViewById(R.id.spin_atualizarPerguntas10);
            ArrayAdapter adapter10 = ArrayAdapter.createFromResource(this, R.array.lista_de_tipos_de_respostas, android.R.layout.simple_spinner_item);
            spinner10.setAdapter(adapter10);

            spinner10.setSelection(verificarNullRetornarPosicaoSpinner(perguntasQuestionario.getResposta10()));

            //////Inicializando o Spinner para guadar a escolha na variável
            AdapterView.OnItemSelectedListener escolhaSpinner10 = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    perguntasQuestionario.setResposta10(atualizarTipoResposta(spinner10.getSelectedItem().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
            spinner10.setOnItemSelectedListener(escolhaSpinner10);

            System.out.println(perguntasQuestionario.toString());

        } catch (Exception e) {
            System.out.println("Erro inicializandoTipoRespostaSpinner() =" + e);
        }
    }

    private int verificarNullRetornarPosicaoSpinner(String tipoResposta) {

        int SpinnerPosition = 0;

        if (tipoResposta == null) {

            SpinnerPosition = 0;

        } else {

            if (tipoResposta.equals("6 Emotions")) {
                SpinnerPosition = 1;
            } else if (tipoResposta.equals("(Sim) ou (Não)")) {
                SpinnerPosition = 2;
            } else if (tipoResposta.equals("Telefone e Email")) {
                SpinnerPosition = 3;
            } else if (tipoResposta.equals("Resposta Aberta")) {
                SpinnerPosition = 4;
            }
        }

        return SpinnerPosition;
    }

    private int verificarNullRetornarPosicaoSpinnerPrimeiraTela(String tipoResposta) {

        int SpinnerPosition = 0;

        if (tipoResposta == null) {

            SpinnerPosition = 0;

        } else {

            if (tipoResposta.equals("Princial 3 Emotions e Ouvidoria")) {
                SpinnerPosition = 0;

            }


        }
        return SpinnerPosition;

    }

    private void verificar1EnviandoPerguntasERespostas() {
        try {

            teste = true;


            //Verificar se o administrador resposável está em branco
            if (etdAdministradorResponsavel.getEditText().getText().toString().equals("")) {
                // System.out.println("Defina o Administrador Resposável");
                Toast.makeText(this, "Defina o Administrador Responsável.", Toast.LENGTH_LONG).show();
                teste = false;
            }


            if (teste) {

                perguntas.add("Lista de Perguntas");
                respostas.add("Lista de Respostas");

                //Pergunta Principal
                verificar2PerguntasERespostasPrimeiraTela(mtPergunta1.getText().toString(), perguntasQuestionario.getResposta1());

                //Pergunta 2
                verificar2PerguntasERespostas(mtPergunta2.getText().toString(), perguntasQuestionario.getResposta2(), etdPergunta2.getHint().toString(), "pergunta2", "resposta2");

                //Pergunta 3
                verificar2PerguntasERespostas(mtPergunta3.getText().toString(), perguntasQuestionario.getResposta3(), etdPergunta3.getHint().toString(), "pergunta3", "resposta3");

                //Pergunta 4
                verificar2PerguntasERespostas(mtPergunta4.getText().toString(), perguntasQuestionario.getResposta4(), mtPergunta4.getText().toString(), "pergunta4", "resposta4");

                //Pergunta 5
                verificar2PerguntasERespostas(mtPergunta5.getText().toString(), perguntasQuestionario.getResposta5(), mtPergunta5.getText().toString(), "pergunta5", "resposta5");

                //Pergunta 6
                verificar2PerguntasERespostas(mtPergunta6.getText().toString(), perguntasQuestionario.getResposta6(), mtPergunta6.getText().toString(), "pergunta6", "resposta6");

                //Pergunta 7
                verificar2PerguntasERespostas(mtPergunta7.getText().toString(), perguntasQuestionario.getResposta7(), mtPergunta7.getText().toString(), "pergunta7", "resposta7");

                //Pergunta 8
                verificar2PerguntasERespostas(mtPergunta8.getText().toString(), perguntasQuestionario.getResposta8(), mtPergunta8.getText().toString(), "pergunta8", "resposta8");

                //Pergunta 9
                verificar2PerguntasERespostas(mtPergunta9.getText().toString(), perguntasQuestionario.getResposta9(), mtPergunta9.getText().toString(), "pergunta9", "resposta9");

                //Pergunta 10
                verificar2PerguntasERespostas(mtPergunta10.getText().toString(), perguntasQuestionario.getResposta10(), mtPergunta10.getText().toString(), "pergunta10", "resposta10");

                System.out.println("Lista de perguntas =" + perguntas.toString());
                System.out.println("Lista de respostas =" + respostas.toString());

                System.out.println("Fim método verificar1 = " + teste);
                if (!teste){
                    perguntas.clear();
                    respostas.clear();
                    System.out.println("Teste retornou = " + teste);
                    System.out.println("Zerou as lista de perguntas e respostas verifique verificar2EnviandoPerguntasERespostas() ");
                }
            } else {

                perguntas.clear();
                respostas.clear();
                System.out.println("Teste retornou = " + teste);
                System.out.println("Zerou as lista de perguntas e respostas verificque verificar1EnviandoPerguntasERespostas() ");

            }

        } catch (Exception e) {
            System.out.println("Erro verificar1EnviandoPerguntasERespostas() = " + e);
        }
    }

    private void salvarFirebaseQuestionarioAtualizado() {

        //Toast.makeText(this, " Perguntas Atualizadas e Salvas no Firebase com sucesso!", Toast.LENGTH_SHORT).show();
        System.out.println("--------------------------SALVANDO NO FIREBASE---------------------------------------------------------------");

        for (int i = 1; i <= 10; i++){

            PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("pergunta" + i).removeValue();
            PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("resposta" + i).removeValue();

        }

        for (int i = 1; i < perguntas.size(); i++) {

            contadorPerguntasMetodoVerificar = i;

            PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("hora").setValue(pegandoHora());
            PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("pergunta" + contadorPerguntasMetodoVerificar).setValue(perguntas.get(i));
            PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("resposta" + contadorPerguntasMetodoVerificar).setValue(respostas.get(i));


        }

        //System.out.println("contadorPerguntasMetodoVerificar = " + contadorPerguntasMetodoVerificar);

        PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("administradorResponsavel").setValue(etdAdministradorResponsavel.getEditText().getText().toString());
        PerguntasQuestionarioReferencia.child("id").child(idPergunta).child("contPerguntas").setValue(contadorPerguntasMetodoVerificar);


    }

    private void verificar2PerguntasERespostasPrimeiraTela(String pergunta, String resposta) {
        System.out.println("Pergunta 1 = " + pergunta);
        System.out.println("Resposta 1 = " + resposta);
        //Verifica se a pergunta 1 está em branco ou incompleta (sempre deve conter dados)
        if (pergunta.equals("") || resposta == null) {

            Toast.makeText(this, "Por favor, elabore a (Pergunta 1).", Toast.LENGTH_LONG).show();

            teste = false;

        } else if (!pergunta.equals("") && resposta != null) {

            perguntas.add(pergunta);
            respostas.add(resposta);

            System.out.println("Pergunta (Pergunta 1 ) preenchida corretamente");

        }
    }

    private void verificar2PerguntasERespostas(String pergunta, String resposta, String perguntaHint, String perguntaIdFirebase, String respostaIdFirebase) {

        if (teste) {

            //System.out.println("Ultima pergunta = " + penultimaRespostaSpinnerBranco + " Pergunta = " + pergunta + " Pergunta Contexto = " + pergunta + " Resposta = " + resposta);

            if (!pergunta.equals("") && resposta != null) {

                System.out.println("Pergunta (" + perguntaHint + ") preenchida corretamente");

                perguntas.add(pergunta);
                respostas.add(resposta);

            }

            if (pergunta.equals("") || resposta == null) {

                if (pergunta.equals("") && resposta != null) {

                    // System.out.println("Por favor, elabore a, (" + perguntaHint + ")");
                    Toast.makeText(this, "Por favor, elabore a (" + perguntaHint + ")", Toast.LENGTH_LONG).show();

                    teste = false;
                    System.out.println("Teste = " + teste);

                } else if (resposta == null && !pergunta.equals("")) {

                    //System.out.println("Por favor, escolha a resposta da (" + perguntaHint + ") ");
                    Toast.makeText(this, "Por favor, escolha a resposta da (" + perguntaHint + ") ", Toast.LENGTH_LONG).show();

                    teste = false;
                    System.out.println("Teste = " + teste);

                }

            }

            if (pergunta.equals("") && resposta == null) {

                //System.out.println("Questão (" + perguntaHint + ") atual em branco");

                penultimaPerguntaHintBranco = perguntaHint;
                penultimaRespostaSpinnerBranco = resposta;
                penultimaPerguntaContextoBranco = pergunta;

                /*if (penultimaPerguntaContextoBranco == null && penultimaRespostaSpinnerBranco != null) {
                    deleteFirebase(perguntaIdFirebase, respostaIdFirebase);
                }*/

            }

            if (!pergunta.equals("") || resposta != null) {

                if (penultimaRespostaSpinnerBranco != null || penultimaPerguntaContextoBranco != null) {

                    //System.out.println("Por favor elabore a, (" + penultimaPerguntaHintBranco+")");
                    Toast.makeText(this, "Por favor, preencha os espaços em branco", Toast.LENGTH_LONG).show();

                    if (penultimaRespostaSpinnerBranco != null && penultimaPerguntaContextoBranco == null) {

                        Toast.makeText(this, "Verifique a (" + penultimaPerguntaHintBranco + ") ", Toast.LENGTH_LONG).show();
                        teste = false;

                    } else if (penultimaRespostaSpinnerBranco == null && penultimaPerguntaContextoBranco != null) {

                        Toast.makeText(this, "Verifique a resposta da (" + penultimaPerguntaHintBranco + ")", Toast.LENGTH_LONG).show();
                        teste = false;

                    }

                    penultimaPerguntaHintBranco = null;
                    penultimaRespostaSpinnerBranco = null;
                    penultimaPerguntaContextoBranco = null;

                }
            }


        }

    }

    private String atualizarTipoResposta(String spinnerResp) {

        String tipoResp = null;

        if (!spinnerResp.equals("Tela não selecionada (vazio)")) {

            tipoResp = spinnerResp;


        }
        //System.out.println("tipoResp = " +tipoResp);
        return tipoResp;
    }


    /////////////////////////////////////////MENU INFLANDO///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listar_resultados, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //////////////////////////MENU CARREGANDO//////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_compartilhar:
                Toast.makeText(this, "Compartilhar", Toast.LENGTH_LONG).show();
                return true;

            case R.id.item_criar_pdf:

                alertGerarPDF();

                return true;
            case R.id.item_voltar:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


            case R.id.item_SalvarAlterecao:

                //Toast.makeText(this, "item_SalvarAlterecao", Toast.LENGTH_LONG).show();
                verificar1EnviandoPerguntasERespostas();


                if (teste) {

                    alertAtualizar();

                }

        }

        return super.onOptionsItemSelected(item);
    }

////////////////////////////////// PDF////////////////////////////////////////////////////////////////////////

    ////////////////////////////PDF GERAR////////////////////////////////////////////////////////////////////////

    private void item_gerarPDF() {
        if (perguntasQuestionario != null) {

            try {
                gerarPDF();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        } else {
            DialogAlerta alerta = new DialogAlerta("Erro ao Gerar PDF", "Não existe nenhuma imagem para gerar PDF");
            alerta.show(getSupportFragmentManager(), "1");
        }
    }

    /////////////////// PDF////////////////////////////////////////////////////////////////////////////
    private void gerarPDF() throws FileNotFoundException, DocumentException {

        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        Toast.makeText(this, "Criar PDF", Toast.LENGTH_LONG).show();

        String nome_Arquivo = diretorio.getPath() + "/" + "Questionario" + System.currentTimeMillis() + ".pdf";
        System.out.println("String" + nome_Arquivo);

        File pdf = new File(nome_Arquivo);

        OutputStream outputStream = new FileOutputStream(pdf);

        com.itextpdf.text.Document document = new Document();
        com.itextpdf.text.pdf.PdfWriter whiter = PdfWriter.getInstance(document, outputStream);
        whiter.setBoxSize("iniciarFirebase", new Rectangle(36, 54, 559, 788));

        document.open();

        Font fontGrande = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font fontMedia = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font fontPequena = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);

        Paragraph paragraph = new Paragraph(
                "Questionário : " + perguntasQuestionario.getNomeQuestionario() + "  \n " +
                "ARQUIVO PDF GERADO EM: " + pegandoHora() + " \n" +
                "ADMINISTRADOR RESPONSÁVEL: " +etdAdministradorResponsavel.getEditText().getText().toString() +  " \n", fontGrande);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        ////////Prenchendo o PDF
        ListItem item = new ListItem();
        Paragraph paragraph2;
        Paragraph paragraph3;
        ///Verificando e preenchendo o paragrafo com dos dados da coleta

        paragraph2 = new Paragraph(" \n" + "Ultima atualização: " + String.valueOf(perguntasQuestionario.getHora()) + " \n", fontMedia);
        item.add(paragraph2);

        if (!mtPergunta1.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(" \n" + mtPergunta1.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta1() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta1()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta2.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta2.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta2() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta2()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta3.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta3.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta3() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta3()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta4.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta4.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta4() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta4()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta5.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta5.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta5() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta5()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta6.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta6.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta6() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta6()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta7.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta7.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta7() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta7()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta8.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta8.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta8() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta8()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta9.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta9.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta9() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta9()) + ")", fontPequena);
            item.add(paragraph3);
        }
        if (!mtPergunta10.getText().toString().equals("")) {
            paragraph3 = new Paragraph(String.valueOf(mtPergunta10.getText().toString()), fontPequena);
            item.add(paragraph3);
        }
        if (perguntasQuestionario.getResposta10() != null) {
            paragraph3 = new Paragraph(String.valueOf("(" + perguntasQuestionario.getResposta10()) + ")", fontPequena);
            item.add(paragraph3);
        }

        document.add(item);

        document.close();

        visualizarPDF(pdf);

    }

    ///////////////////PDF VISUALIZAR////////////////////////////////////////////////////////////////

    private void visualizarPDF(File pdf) {

        PackageManager packageManager = getPackageManager();
        Intent itent = new Intent(Intent.ACTION_VIEW);

        itent.setType("application/pdf");

        List<ResolveInfo> lista = packageManager.queryIntentActivities(itent, PackageManager.MATCH_DEFAULT_ONLY);

        if (lista.size() > 0) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Uri uri = FileProvider.getUriForFile(getBaseContext(), "com.exemplo.firebasecursods", pdf);

            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        } else {
            DialogAlerta dialogAlerta = new DialogAlerta("Erro ao Abrir PDF", "Não foi detectado nenhum leitor de PDF no seu DispositivoAdministrador");
            dialogAlerta.show(getSupportFragmentManager(), "3");
        }
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada;
        //System.out.println("Data " + dataFormatada + " Hora " + horaFormatada);

        return dataEHora;
    }

    private void alertAtualizar(){
        System.out.println("clicou");
        AlertDialog.Builder builder = new AlertDialog.Builder(AtualizarPerguntasActivity.this);

        builder.setTitle("ATENÇÃO");
        builder.setMessage("(CUIDADO) TODOS OS DISPOSITIVOS QUE UTILIZAM ESTE QUESTIONÁRIO SERÃO AFETADOS" + "\n" + "\n" +

                "Tem certeza que deseja atualizar definitivamente este questionário?");

        builder.setPositiveButton("ATUALIZAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(AtualizarPerguntasActivity.this, "Questionário (" + tvNomeQuestionario.getText().toString() + ") Atualizado. ", Toast.LENGTH_LONG).show();

                salvarFirebaseQuestionarioAtualizado();

                Intent intent2 = new Intent(AtualizarPerguntasActivity.this, ListarQuestionariosActivity.class);
                startActivity(intent2);
            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AtualizarPerguntasActivity.this, "Questionário (" + tvNomeQuestionario.getText().toString() + "Não foi Atualizado. ", Toast.LENGTH_LONG).show();

                teste = false;
            }
        });

        builder.create();
        builder.show();
    }

    private void alertGerarPDF(){
        System.out.println("clicou");
        AlertDialog.Builder builder = new AlertDialog.Builder(AtualizarPerguntasActivity.this);

        builder.setTitle("ATENÇÃO");
        builder.setMessage("O PDF com suas perguntas e respostas será gerado" + "\n" + "\n" +

                "Tem certeza que deseja gerar o PDF sem antes salvar suas alterações?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                item_gerarPDF();

            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                teste = false;
            }
        });

        builder.create();
        builder.show();
    }

}
