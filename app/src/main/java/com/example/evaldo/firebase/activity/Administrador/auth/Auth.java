package com.example.evaldo.firebase.activity.Administrador.auth;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.evaldo.firebase.activity.Administrador.Classes.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Auth {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private static Auth auth;
    private DatabaseReference usersReference;

    private Auth(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersReference = database.getReference("users");
    }

    public static Auth getInstance(){
        if(auth == null) auth = new Auth();
        return auth;
    }

    public void sign(Activity context, final Usuario usuario, String password, final OnAuth callback){

        mAuth.createUserWithEmailAndPassword(usuario.getEmail(), password).addOnCompleteListener(context,
                new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CADASTRO", "OK");
                            FirebaseUser userAuth = mAuth.getCurrentUser();
                            //userAuth.getDisplayName();
                            DatabaseReference user = usersReference.child(userAuth.getUid());
                            //set user data in database
                            user.child("name").setValue(usuario.getName());
                            Usuario newUser = new Usuario(userAuth.getUid(), usuario.getEmail());
                            newUser.setData(usuario);
                            callback.authOK(newUser);
                        }else{
                            callback.authFail(usuario);
                        }
                    }
                }

        );

    }


    public void login(Activity context, String email, String password, final OnAuth callback){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser userAuth = mAuth.getCurrentUser();

                        final Usuario usuario = new Usuario(userAuth.getUid(), userAuth.getEmail());

                        usersReference.child(userAuth.getUid()).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String name = dataSnapshot.child("name").getValue(String.class);

                                    usuario.setName(name);

                                    callback.authOK(usuario);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    callback.authFail(null);
                                }
                            }
                        );
                    } else {
                        callback.authFail(null);
                    }

                    // ...
                }
            });
    }


    public void getCurrentUser(Activity context, final OnAuth callback){
        if (mAuth.getCurrentUser() != null) {
            usersReference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    FirebaseUser userAuth = mAuth.getCurrentUser();

                    final Usuario usuario = new Usuario(userAuth.getUid(), userAuth.getEmail());
                    String name = dataSnapshot.child("name").getValue(String.class);

                    usuario.setName(name);

                    callback.authOK(usuario);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    callback.authFail(null);
                }
            });
        }else {
            callback.authFail(null);
        }
    }


    public void logout(){
        if (mAuth.getCurrentUser() != null) mAuth.signOut();

    }


}
