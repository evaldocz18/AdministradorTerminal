package com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosOuvidoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosOuvidoria;
import com.example.evaldo.firebase.activity.Administrador.MainActivity;
import com.example.evaldo.firebase.activity.Administrador.Adaptadores.ResultadosDetalhadosOuvidoriaAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.DialogAlerta;
import com.google.firebase.database.DatabaseReference;
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


public class ResultadosOuvidoriaDetalhados extends AppCompatActivity {

    ResultadosDetalhadosOuvidoriaAdapter ouvidoriaAdapter;

    public static ArrayList<ResultadosOuvidoria> listOuvidoria = new ArrayList<>();

    RecyclerView recyclerViewOuvidoria;

    private DatabaseReference  referenceKiosque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_ouvidoria_detalhados);

        System.out.println("listOuvidoria.toString() =" + listOuvidoria.toString());
        iniciarRecyclerView();

    }


    private void iniciarRecyclerView() {
        recyclerViewOuvidoria = findViewById(R.id.recycler_view_mostrar_resultados_detalhados_ouvidoria);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOuvidoria.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewOuvidoria.addItemDecoration(itemDecoration);

        ResultadosDetalhadosOuvidoriaAdapter resultadosDetalhadosOuvidoriaAdapter = new ResultadosDetalhadosOuvidoriaAdapter(listOuvidoria);


        recyclerViewOuvidoria.setAdapter(resultadosDetalhadosOuvidoriaAdapter);

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
        if (listOuvidoria != null) {

            try {
                gerarPDF();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        } else {
            DialogAlerta alerta = new DialogAlerta("Erro ao Gerar PDF", "Não é possível gerar PDF em branco");
            alerta.show(getSupportFragmentManager(), "1");
        }
    }

    /////////////////// PDF////////////////////////////////////////////////////////////////////////////
    private void gerarPDF() throws FileNotFoundException, DocumentException {

        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        Toast.makeText(this, "Criar PDF", Toast.LENGTH_LONG).show();
        System.out.println("for = " + listOuvidoria);

        String nomeArquivo = diretorio.getPath() + "/" + "Coleta de dados" + System.currentTimeMillis() + ".pdf";
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

        Paragraph paragraph = new Paragraph("COLETA DE DADOS DA OUVIDORIA" + " \n " + "ARQUIVO PDF GERADO EM " + pegandoHora() + " \n", fontGrande);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        ////////Prenchendo o PDF
        ListItem item = new ListItem();
        Paragraph paragraph2;
        Paragraph paragraph3;


        ///Verificando e preenchendo o paragrafo com dos dados da coleta
        for (int i = 0; i < listOuvidoria.size(); i++) {
            System.out.println("for = " + listOuvidoria);
            paragraph3 = new Paragraph(String.valueOf("    "), fontMedia);
            item.add(paragraph3);
            paragraph2 = new Paragraph(String.valueOf(listOuvidoria.get(i).getHora()), fontMedia);
            item.add(paragraph2);
            if (listOuvidoria.get(i).getResposta() != null) {
                paragraph3 = new Paragraph(String.valueOf(listOuvidoria.get(i).getResposta()), fontMedia);
                item.add(paragraph3);
                paragraph3 = new Paragraph(String.valueOf(listOuvidoria.get(i).getAdministradorResponsavel()), fontMedia);
                item.add(paragraph3);
                /*paragraph3 = new Paragraph(String.valueOf(listOuvidoria.get(i).getIdDispositivo()), fontMedia);
                item.add(paragraph3);*/
            }

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
