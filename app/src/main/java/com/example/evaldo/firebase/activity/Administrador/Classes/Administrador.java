package com.example.evaldo.firebase.activity.Administrador.Classes;

public class Administrador {

    String UID, criado, conectado, email, nome, telefone, setoresResponsvel[], status;

    public Administrador(String UID, String criado, String conectado, String email, String nome, String telefone, String[] setoresResponsvel, String status) {
        this.UID = UID;
        this.criado = criado;
        this.conectado = conectado;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.setoresResponsvel = setoresResponsvel;
        this.status = status;
    }

    public Administrador(){};

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getCriado() {
        return criado;
    }

    public void setCriado(String criado) {
        this.criado = criado;
    }

    public String getConectado() {
        return conectado;
    }

    public void setConectado(String conectado) {
        this.conectado = conectado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "UID='" + UID + '\'' +
                ", criado='" + criado + '\'' +
                ", conectado='" + conectado + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
