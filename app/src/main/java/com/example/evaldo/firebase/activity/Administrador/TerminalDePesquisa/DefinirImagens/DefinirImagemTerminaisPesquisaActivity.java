package com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.DefinirImagens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.evaldo.firebase.R;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;

public class DefinirImagemTerminaisPesquisaActivity extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definir_imagem_terminais_pesquisa);

        downloadImagem();
        uplodImagem();
        removerImagem();
    }

    private void removerImagem() {
    }

    private void uplodImagem() {
    }

    private void downloadImagem() {

      /*  AsyncTask.execute(new Runnable() {
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
        });*/
    }
}
