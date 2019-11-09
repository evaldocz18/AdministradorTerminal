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

    public void clicouAtivarTerminalDePesquisa(View view) {
        Intent intent = new Intent(this, CadastrarTerminalDePesquisa.class);
        startActivity(intent);
    }
}
