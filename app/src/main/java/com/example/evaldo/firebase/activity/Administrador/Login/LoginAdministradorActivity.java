package com.example.evaldo.firebase.activity.Administrador.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.util.Autentication;
import com.example.evaldo.firebase.activity.Administrador.util.Storage;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class LoginAdministradorActivity extends AppCompatActivity {

    private EditText EtNome, EtSenha;
    public Context contextLoginAdministrador;
    Autentication autentication = Autentication.getInstance(LoginAdministradorActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);

        EtNome = findViewById(R.id.et_nome_administrador);
        EtSenha = findViewById(R.id.et_senha_administrador);

    }

    public void clickLoginAdministrador(View view) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Autentication autentication = Autentication.getInstance(LoginAdministradorActivity.this);
                FirebaseUser user = autentication.login(EtNome.getText().toString(), EtSenha.getText().toString());

                if (user != null) {

                    Storage storage = Storage.getInstance();
                    String filePath = "";
                    String storagePath = "";

                    if (storage.download(filePath, storagePath)) {
                        System.out.println("Arquivo baixado com sucesso");
                    }
                }

            }
        });
    }

    public void clickCadastrarAdministrador(View view) {

        String email = EtNome.getText().toString();
        String senha = EtSenha.getText().toString();

        if (senha.equals("") || email.equals("")) {

            Toast.makeText(this, "Preencha os campos de email e senha", Toast.LENGTH_LONG).show();
        } else {

            autentication.createUser(EtNome.getText().toString(), EtSenha.getText().toString(), LoginAdministradorActivity.this);
        }

    }


}
