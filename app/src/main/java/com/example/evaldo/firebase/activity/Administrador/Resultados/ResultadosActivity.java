package com.example.evaldo.firebase.activity.Administrador.Resultados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosOuvidoria.ResultadosOuvidoriaQuantitativos;
import com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosQuestionarios.ListarQuestionariosResultadosActivity;

public class ResultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
    }

    public void click_resultados_questionarios(View view) {
        Intent intent = new Intent(this, ListarQuestionariosResultadosActivity.class);
        startActivity(intent);
    }

    public void click_resultados_ouvidoria(View view) {

        Intent intent = new Intent(this, ResultadosOuvidoriaQuantitativos.class);
        startActivity(intent);
    }
}
