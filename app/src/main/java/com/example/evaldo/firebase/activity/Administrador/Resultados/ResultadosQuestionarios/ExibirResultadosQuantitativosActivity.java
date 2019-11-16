package com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosQuestionarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.MainActivity;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.ResultadosQuantitativosAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.DadoQuantitativo;
import com.example.evaldo.firebase.activity.Administrador.util.DialogAlerta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExibirResultadosQuantitativosActivity extends AppCompatActivity {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Banco Respostas Questionário").child("id");
    private HashMap<String, HashMap<String, Integer>> perguntas = new HashMap<>();
    private ResultadosQuantitativosAdapter adapter;
    private DataSnapshot questInfo;
    private List<DadoQuantitativo> dados;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_resultatos_quantitativos2);

        iniciarRecyclerView();

        iniciarFirebase();

    }

    private void iniciarFirebase() {
        final String selectedName = getIntent().getStringExtra("questionario");

        DatabaseReference perguntasQuestionario = FirebaseDatabase.getInstance().getReference("Banco Perguntas Questionario").child("id");

        perguntasQuestionario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // System.out.println("child" + child );
                    if (child.child("nomeQuestionario").getValue().equals(selectedName)) {
                        questInfo = child;

                        root.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    if (!child.child("nomeQuestionario").getValue().equals(selectedName))
                                        continue;
                                    for (DataSnapshot childChild : child.getChildren())
                                        if (childChild.getKey().contains("resposta")) {


                                            String resposta = childChild.getKey();
                                            String pergunta = child.child("pergunta" + resposta.replace("resposta", "")).getValue(String.class);
                                            String resp = (String) childChild.getValue();


                                            String tipoPergunta = questInfo.child(resposta).getValue(String.class);

                                            if (tipoPergunta.equals("Telefone e Email")) {
                                                if (resp.equals("(Não se identificou)")) {
                                                    count(pergunta, "(Não responderam) ");
                                                } else {
                                                    count(pergunta, "(Deixaram emailFuncionario ou telefone)");
                                                }

                                            } else if (tipoPergunta.equals("Resposta Aberta")) {
                                                if (resp.equals("(Não deixou comentario)")) {
                                                    count(pergunta, "(Não responderam) ");
                                                } else {
                                                    count(pergunta, "(Deixaram comentário)");
                                                }
                                            } else {

                                                count(pergunta, resp);
                                            }
                                        }


                                }

                                adapter.update(perguntas);

                                dados = new ArrayList<>();

                                for (String pergunta : perguntas.keySet()) {

                                    HashMap<String, Integer> map = perguntas.get(pergunta);
                                    List<String> respostas = new ArrayList<>();

                                    for (String s : map.keySet()) {
                                        respostas.add(s + " " + map.get(s));
                                    }

                                    dados.add(new DadoQuantitativo(pergunta, respostas));


                                }

                                Collections.sort(dados, new Comparator<DadoQuantitativo>() {
                                    @Override
                                    public int compare(DadoQuantitativo o1, DadoQuantitativo o2) {
                                        int i1 = Integer.parseInt(o1.getPergunta().split("\\.")[0]);
                                        int i2 = Integer.parseInt(o2.getPergunta().split("\\.")[0]);

                                        if (i1 < i2) return -1;
                                        if (i1 == i2) return 0;
                                        return 1;

                                    }
                                });

                                System.out.println("dados =" + dados.toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void iniciarRecyclerView() {
        adapter = new ResultadosQuantitativosAdapter();

        RecyclerView recycler = findViewById(R.id.recyclerViewResultados);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    private void count(String pergunta, String resposta) {


        if (resposta == null) resposta = "vazio";

        if (!perguntas.containsKey(pergunta))
            perguntas.put(pergunta, new HashMap<String, Integer>());

        if (!perguntas.get(pergunta).containsKey(resposta))
            perguntas.get(pergunta).put(resposta, 0);

        HashMap<String, Integer> respostas = perguntas.get(pergunta);

        respostas.put(resposta, respostas.get(resposta) + 1);
    }

    /////////////////////////MENU INFLANDO///////////////////////////////////////////////////////////////////////////////////////////
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

                item_gerarPDF();
                return true;
            case R.id.item_voltar:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                //item_voltar();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
////////////////////////////////// PDF////////////////////////////////////////////////////////////////////////

    ////////////////////////////PDF GERAR////////////////////////////////////////////////////////////////////////
    private void item_gerarPDF() {

        if (dados != null) {

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

    private void gerarPDF() throws FileNotFoundException, DocumentException {

        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        Toast.makeText(this, "Criar PDF", Toast.LENGTH_LONG).show();
        System.out.println("for = " + dados);

        String nomeArquivo = diretorio.getPath() + "/" + "Coleta de Dados Quantitavos" + System.currentTimeMillis() + ".pdf";
        System.out.println("String" + nomeArquivo);

        File pdf = new File(nomeArquivo);

        OutputStream outputStream = new FileOutputStream(pdf);

        com.itextpdf.text.Document document = new Document();
        com.itextpdf.text.pdf.PdfWriter whiter = PdfWriter.getInstance(document, outputStream);
        whiter.setBoxSize("iniciarFirebase", new Rectangle(36, 54, 559, 788));

        document.open();

        Font fontGrande = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font fontMedia = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font fontPequena = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);

        Paragraph paragraph = new Paragraph("COLETA DE DADOS QUANTITATIVOS" + " \n " + "ARQUIVO PDF GERADO EM " + pegandoHora() + " \n", fontGrande);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        ////////Prenchendo o PDF
        ListItem item = new ListItem();
        Paragraph paragraph2;
        Paragraph paragraph3;


        ///Verificando e preenchendo o paragrafo com dos dados da coleta
        for (int i = 0; i < dados.size(); i++) {
            // System.out.println("for = " + dados);
            // paragraph2 = new Paragraph(String.valueOf(pegandoHora()), fontMedia);
            // item.add(paragraph2);
            if (dados.get(i).getPergunta() != null) {
                paragraph3 = new Paragraph(String.valueOf(dados.get(i).getPergunta()) + "\n", fontMedia);
                item.add(paragraph3);
            }
            if (dados.get(i).getRespostas() != null) {
                for (String respostas : dados.get(i).getRespostas()) {
                    paragraph3 = new Paragraph(String.valueOf(respostas) + "\n", fontMedia);
                    item.add(paragraph3);
                }
            }


        }
        document.add(item);


        document.close();

        visualizarPDF(pdf);

    }

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
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada;
        //System.out.println("Data " + dataFormatada + " Hora " + horaFormatada);

        return dataEHora;
    }


}
