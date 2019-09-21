package com.example.evaldo.firebase.activity.Administrador.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Manutencao.PrincipalManutencaoActivity;

public class LoginDesenvolvedorActivity extends AppCompatActivity {


    private EditText nome, senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_desenvolvedor);

        nome = findViewById(R.id.et_nome_supervisor);
        senha = findViewById(R.id.et_senha_supervisor);

    }

    public void clickLoginSupervisor(View view) {
        if (nome.getText().toString().equals("Evaldo") || senha.getText().toString().equals("1111")){

            Intent intent = new Intent(this, PrincipalManutencaoActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "SENHA OU NOME INCORRETOS", Toast.LENGTH_LONG).show();
        }

    }



}
