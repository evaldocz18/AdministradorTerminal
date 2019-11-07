package com.example.evaldo.firebase.activity.Administrador.Classes;

public class Usuario {

    private String uid;
    private String email;
    private String name;



    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(Usuario data){
        email = data.email;
        name = data.name;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "uid='" + uid + "'\n" +
                ", email='" + email +"'\n" +
                ", name='" + name + "'\n" +
                '}';
    }
}
