package com.example.evaldo.firebase.activity.Administrador.Manutencao;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.evaldo.firebase.R;

public class PrincipalManutencaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_manutencao);
    }

    public void clicouCadastrarDispositivoAdministrador(View view) {
        Intent intent = new Intent(this, CadastrarDispositivoAdministradorActivity.class);
        startActivity(intent);
    }

    public void clicouCadastrarDIspositivoKiosque(View view) {
        Intent intent = new Intent(this, CadastrarDispostivoKiosqueActivity.class);
        startActivity(intent);
    }
}
