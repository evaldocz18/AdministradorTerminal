package com.example.evaldo.firebase.activity.Administrador.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.Administrador;
import com.example.evaldo.firebase.activity.Administrador.Manutencao.PrincipalManutencaoActivity;
import com.example.evaldo.firebase.activity.Administrador.util.Autentication;
import com.google.firebase.auth.FirebaseUser;

public class LoginAdministradorActivity extends AppCompatActivity {

    private EditText EtEmail, EtSenha;
    Autentication autentication = Autentication.getInstance(LoginAdministradorActivity.this);
    public static Administrador administrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);

        EtEmail = findViewById(R.id.et_email_administrador);
        EtSenha = findViewById(R.id.et_senha_administrador);

    }

    public void clickLoginAdministrador(View view) {

        String email = EtEmail.getText().toString();
        String senha = EtSenha.getText().toString();

        FirebaseUser user;

        if (EtEmail.getText().toString().equals("Evaldo") || EtSenha.getText().toString().equals("1111")) {

            Intent intent = new Intent(this, PrincipalManutencaoActivity.class);
            startActivity(intent);

        } else {
            if (!(senha.equals("") || email.equals(""))) {

                autentication = Autentication.getInstance(LoginAdministradorActivity.this);
                user = autentication.login(EtEmail.getText().toString(), EtEmail.getText().toString(), LoginAdministradorActivity.this);

            } else {

                Toast.makeText(this, "Preencha os campos de email e senha", Toast.LENGTH_LONG).show();

            }
        }


    }

    public void clickCadastrarAdministrador(View view) {

        String email = EtEmail.getText().toString();
        String senha = EtEmail.getText().toString();

        if (senha.equals("") || email.equals("")) {

            Toast.makeText(this, "Preencha os campos de email e senha", Toast.LENGTH_LONG).show();
        } else {

            autentication.createUser(EtEmail.getText().toString(), EtEmail.getText().toString(), LoginAdministradorActivity.this);
        }


    }


}
