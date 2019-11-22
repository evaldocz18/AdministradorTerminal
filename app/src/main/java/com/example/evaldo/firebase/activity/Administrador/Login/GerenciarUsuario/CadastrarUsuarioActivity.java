package com.example.evaldo.firebase.activity.Administrador.Login.GerenciarUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.PrincipalActivity;
import com.example.evaldo.firebase.activity.Administrador.auth.Auth;
import com.example.evaldo.firebase.activity.Administrador.auth.OnAuth;
import com.example.evaldo.firebase.activity.Administrador.Classes.Usuario;
import com.google.android.material.textfield.TextInputLayout;

public class CadastrarUsuarioActivity extends AppCompatActivity implements OnAuth {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

    }

    public void confirmarNovoCadastro(View view){
        TextInputLayout campoEmail = findViewById(R.id.emailField);
        TextInputLayout campoSenha = findViewById(R.id.passwordField);
        TextInputLayout campoNome = findViewById(R.id.nameField);

        String email = campoEmail.getEditText().getText().toString();
        String senha = campoSenha.getEditText().getText().toString();
        String nome = campoNome.getEditText().getText().toString();

        Usuario usuario = new Usuario(email);
        usuario.setName(nome);

        Auth.getInstance().sign(this, usuario, senha, this);
        //lembrar de colocar uma progress bar


    }

    @Override
    public void authOK(Usuario user) {
        //se der certo
        finish();
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void authFail(Usuario user) {

        Toast.makeText(this, "Falha no Cadastro", Toast.LENGTH_LONG).show();

    }
}
