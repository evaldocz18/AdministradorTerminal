package com.example.evaldo.firebase.activity.Administrador.util;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Storage {

    private StorageReference mStorageRef;
    private static Storage reference;

    private Storage() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public static Storage getInstance() {
        if (reference == null) reference = new Storage();
        return reference;
    }

    public boolean upload(String filePath, String storagePath) {
        Uri file = Uri.fromFile(new File(filePath));
        StorageReference riversRef = mStorageRef.child(storagePath);
        final Boolean[] success = {null};

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        success[0] = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        success[0] = false;
                    }
                });

        while (success[0] == null) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return success[0];
    }

    public boolean download(String filePath, String storagePath) {
        File localFile = new File(filePath);
        StorageReference fileRef = mStorageRef.child(storagePath);
        final Boolean[] success = {null};

        fileRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                        success[0] = true;
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
                success[0] = false;
            }
        });

        while (success[0] == null) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return success[0];
    }

}

