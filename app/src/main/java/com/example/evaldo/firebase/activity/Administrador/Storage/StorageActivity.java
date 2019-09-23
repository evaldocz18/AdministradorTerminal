package com.example.evaldo.firebase.activity.Administrador.Storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.PrincipalActivity;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
    }

    public void click_ir_storageDownload(View view) {
        Intent intent = new Intent(this, StorageDownloadActivity.class);
        startActivity(intent);
    }

    public void click_ir_storageUpload(View view) {
    }

    public void click_ir_storageDatabase(View view) {
    }

    public void click_ir_stotageCRUD(View view) {
    }

    public void click_ir_storageEmpresas(View view) {
    }
}
