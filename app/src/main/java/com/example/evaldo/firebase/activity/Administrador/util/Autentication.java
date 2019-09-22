package com.example.evaldo.firebase.activity.Administrador.util;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Autentication {

    private FirebaseAuth mAuth;
    private static Autentication instance;
    private Activity context;

    private Autentication(){
        mAuth = FirebaseAuth.getInstance();
    }


    public static Autentication getInstance(Activity context) {
        if (instance == null) instance = new Autentication();
        instance.context = context;
        return instance;
    }


    public FirebaseUser createUser(final String email, final String password){

        final FirebaseUser[] user = {null};
        final Boolean[] success = {null};

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            user[0] = mAuth.getCurrentUser();
                            success[0] = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            success[0] = false;
                        }

                        // ...
                    }
                });

        while (success[0] == null){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return user[0];
    }



    public FirebaseUser login(String email, String password){

        final FirebaseUser[] user = {null};
        final Boolean[] success = {null};

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            user[0] = mAuth.getCurrentUser();
                            success[0] = true;
                        } else {
                            success[0] = false;
                        }

                        // ...
                    }
                });

        while (success[0] == null){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return user[0];
    }
}
