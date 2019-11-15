package com.example.evaldo.firebase.activity.Administrador;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Login.LoginAdministradorctivity;
import com.example.evaldo.firebase.activity.Administrador.Manutencao.PrincipalManutencaoActivity;
import com.example.evaldo.firebase.activity.Administrador.Resultados.ResultadosActivity;
import com.example.evaldo.firebase.activity.Administrador.Storage.StorageActivity;
import com.example.evaldo.firebase.activity.Administrador.TerminalDePesquisa.TerminaisActivity;
import com.example.evaldo.firebase.activity.Administrador.auth.Auth;


public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


    }

    /////////////////////////MENU INFLANDO///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return super.onCreateOptionsMenu(menu);

        //teste comit
    }

    //////////////////////////MENU CARREGANDO//////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_configuracoes:
                Intent intent = new Intent(this, PrincipalManutencaoActivity.class);
                startActivity(intent);
                Toast.makeText(this, "SOMENTE O DESENVOLVEDOR PODE TER ACESSO", Toast.LENGTH_LONG).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void click_resultados(View view) {
        Intent intent = new Intent(this, ResultadosActivity.class);
        startActivity(intent);
    }

    public void click_kiosques(View view) {
        Intent intent = new Intent(this, TerminaisActivity.class);
        startActivity(intent);
    }

    public void click_questionarios(View view) {
        Intent intent = new Intent(this, QuestionariosActivity.class);
        startActivity(intent);
    }

    /*public void click_principal_logar(View view) {
        Intent intent = new Intent(this, LoginAdministradorActivity.class);
        startActivity(intent);
    }*/

    public void click_principal_storage(View view) {
        Intent intent = new Intent(this, StorageActivity.class);
        startActivity(intent);
    }

    public void click_principal_logar(View view) {
        Auth.getInstance().logout();
        finish();
        Intent intent = new Intent(this, LoginAdministradorctivity.class);
        startActivity(intent);
    }
}
