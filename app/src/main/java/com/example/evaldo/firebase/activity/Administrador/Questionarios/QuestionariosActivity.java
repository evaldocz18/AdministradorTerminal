package com.example.evaldo.firebase.activity.Administrador.Questionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Questionarios.GerenciadorQuestionatio.ListarQuestionariosActivity;
import com.example.evaldo.firebase.activity.Administrador.Questionarios.GerenciadorQuestionatio.NovoQuestionarioActivity;
import com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosQuestionarios.ResultadosQuestionariosActivity;

public class QuestionariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionarios);


    }

    public void click_questionarios_criar(View view) {
        Intent intent = new Intent(this, NovoQuestionarioActivity.class);
        startActivity(intent);
    }

    public void click_questionarios_exibir_editar(View view) {
        Intent intent = new Intent(this, ListarQuestionariosActivity.class);
        startActivity(intent);
    }
}
