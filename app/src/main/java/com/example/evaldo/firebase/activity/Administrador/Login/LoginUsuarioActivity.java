package com.example.evaldo.firebase.activity.Administrador.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.Usuario;
import com.example.evaldo.firebase.activity.Administrador.PrincipalActivity;
import com.example.evaldo.firebase.activity.Administrador.auth.Auth;
import com.example.evaldo.firebase.activity.Administrador.auth.OnAuth;
import com.google.android.material.textfield.TextInputLayout;

public class LoginUsuarioActivity extends AppCompatActivity implements OnAuth {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar_usuario);

        Auth.getInstance().getCurrentUser(this, new OnAuth() {
            @Override
            public void authOK(Usuario user) {
                finish();
                Intent intent = new Intent(LoginUsuarioActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }

            @Override
            public void authFail(Usuario user) {

            }
        });

        /*Para deslogar
        Auth.getInstance().logout();*/
    }

    @Override
    public void authOK(Usuario user) {
        finish();
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);

    }

    @Override
    public void authFail(Usuario user) {

        Toast.makeText(this, "Falha na Autenticação", Toast.LENGTH_LONG).show();
    }

    public void entrar(View view) {

        Auth auth = Auth.getInstance();

        TextInputLayout emailField = findViewById(R.id.main_emailField);
        TextInputLayout passwordField = findViewById(R.id.main_passwordField);

        String email = emailField.getEditText().getText().toString();
        String password = passwordField.getEditText().getText().toString();

        auth.login(this, email, password, this);

    }

    public void crirConta(View view) {

        Intent intent = new Intent(this, CadastrarUsuarioActivity.class);

        startActivity(intent);
    }

    public void confirmarNovoCadastro(View view) {


    }

    public void esqueciSenha(View view) {
        Intent intent = new Intent(this, RedefinirSenhaUsuarioActivity.class);
        startActivity(intent);
    }
}
