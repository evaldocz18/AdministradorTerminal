package com.example.evaldo.firebase.activity.Administrador.auth;

import com.example.evaldo.firebase.activity.Administrador.Classes.Usuario;

public interface OnAuth {

    void authOK(Usuario user);
    void authFail(Usuario user);
}
