package com.example.evaldo.firebase.activity.Administrador.Storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.evaldo.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class StorageDownloadActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private Button button_download, button_remover;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_download_activity);

        imageView = (ImageView) findViewById(R.id.imagemView_StorageDownload);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_StorageDownload);

        button_download = (Button) findViewById(R.id.button_StorageDownload);
        button_remover = (Button) findViewById(R.id.button_StorageRemover);

        ////Inicializando o storage
        storage = FirebaseStorage.getInstance();
        progressBar.setVisibility(View.GONE);

        url = "https://firebasestorage.googleapis.com/v0/b/projeto-teste-d960c.appspot.com/o/imagem%2Fgif_emotion.gif?alt=media&token=a036e5f2-c34e-42da-9af4-a4b6d721f547";


    }

    public void click_storageDownoad(View view) {

        // download_forma_1();

        // download_forma_2();

        download_gif_url(url);

        //download_gif_nome();

    }

    public void click_storageRemover(View view) {

        //remover_gif_url();

        remover_gif_nome();


    }

    private void remover_gif_nome() {

        String nome = "cartaz_vestibular_fafic.jpg";

        StorageReference reference = storage.getReference().child("imagem").child(nome);

        reference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(getBaseContext(), "Imagem Removida com Sucesso", Toast.LENGTH_LONG).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getBaseContext(), "Erro ao remover imagem", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void remover_gif_url() {
        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(url);
        reference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    imageView.setImageDrawable(null);

                    Toast.makeText(getBaseContext(), "Imagem Removida com Sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Erro ao remover imagem", Toast.LENGTH_LONG).show();

                }


            }

        });
    }

    private void download_gif_nome() {

        progressBar.setVisibility(View.VISIBLE);

        StorageReference reference = storage.getReference().child("imagem").child("gif_arvore.gif");

        reference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()) {

                    String url = task.getResult().toString();

                    download_gif_url(url);

                }
            }
        });
    }

    private void download_gif_url(String url) {


        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);

                return false;
            }
        }).into(imageView);
    }

    private void download_forma_1() {
        progressBar.setVisibility(View.VISIBLE);

        String url = "https://firebasestorage.googleapis.com/v0/b/projeto-teste-d960c.appspot.com/o/imagem%2Fcartaz_vestibular_fafic.jpg?alt=media&token=874c5374-4fce-4915-a757-1c6a008d07b2";

        Picasso.with(getBaseContext()).load(url).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Download efetuado com sucesso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError() {
                Toast.makeText(getBaseContext(), "Não foi possível baixar imagem", Toast.LENGTH_LONG).show();


            }
        });


    }

    private void download_forma_2() {

        String url = "https://firebasestorage.googleapis.com/v0/b/projeto-teste-d960c.appspot.com/o/imagem%2Fgif_emotion.gif?alt=media&token=a036e5f2-c34e-42da-9af4-a4b6d721f547";

        Glide.with(getBaseContext()).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(), "Não foi possível baixar imagem", Toast.LENGTH_LONG).show();


                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(), "Download efetuado com sucesso", Toast.LENGTH_LONG).show();


                return false;
            }
        }).into(imageView);
    }

    /////////////////MENU//////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_storage_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_compartilhar:

                Toast.makeText(this, "Compartilhar", Toast.LENGTH_LONG).show();

                return true;

            case R.id.item_criar_pdf:

                Toast.makeText(this, "Criar PDF", Toast.LENGTH_LONG).show();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
