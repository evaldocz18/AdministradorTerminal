package com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirImagens.DefinirImagemTerminaisPesquisaActivity;
import com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasTerminalActivity;

public class TerminaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminais);
    }

    public void click_kiosque_definir(View view) {
        Intent intent = new Intent(this, ListarQuestionarioResumidasTerminalActivity.class);
        startActivity(intent);
    }

    public void click_kiosque_cadastrar(View view) {

        Toast.makeText(this, "Apenas o desenvolverdor pode Cadastrar novos dispositivos \n" +"Esta função será implementada na proxima versão do app" , Toast.LENGTH_LONG).show();

    }

    public void click_kiosq0ue_editar(View view) {
        Toast.makeText(this, "Apenas o desenvolverdor pode Editar os dispositivos \n" +"Esta função será implementada na proxima versão do app" , Toast.LENGTH_LONG).show();

    }

    public void click_kiosque_verifica_online(View view) {
        Toast.makeText(this, "Esta função será implementada na proxima versão do app" , Toast.LENGTH_LONG).show();

    }

    public void click_kiosque_definir_imagem(View view) {
        Intent intent = new Intent(this, DefinirImagemTerminaisPesquisaActivity.class);
        startActivity(intent);
    }
}
