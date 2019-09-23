package com.example.evaldo.firebase.activity.Administrador.Storage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.evaldo.firebase.R;

public class StorageDownloadActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private Button button_download, button_remover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_download_activity);

        imageView = (ImageView) findViewById(R.id.imagemView_StorageDownload);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_StorageDownload);

        button_download = (Button) findViewById(R.id.button_StorageDownload);
        button_remover = (Button) findViewById(R.id.button_StorageRemover);


    }

    public void click_storageDownoad(View view) {

        download_imagem_1();

    }

    private void download_imagem_1() {

        String url = "https://firebasestorage.googleapis.com/v0/b/projeto-teste-d960c.appspot.com/o/imagem%2Fexpo_negocios.png?alt=media&token=a9859075-dbe0-477c-91cc-17323243dc02";

    }

    public void click_storageRemover(View view) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_storage_download,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_compartilhar:

                Toast.makeText(this, "Compartilhar" , Toast.LENGTH_LONG).show();

                return true;

            case R.id.item_criar_pdf:

                Toast.makeText(this, "Criar PDF" , Toast.LENGTH_LONG).show();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
